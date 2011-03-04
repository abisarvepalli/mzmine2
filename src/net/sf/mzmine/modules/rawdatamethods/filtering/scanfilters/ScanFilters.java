/*
 * Copyright 2006-2011 The MZmine 2 Development Team
 * 
 * This file is part of MZmine 2.
 * 
 * MZmine 2 is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * MZmine 2 is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * MZmine 2; if not, write to the Free Software Foundation, Inc., 51 Franklin
 * St, Fifth Floor, Boston, MA 02110-1301 USA
 */
package net.sf.mzmine.modules.rawdatamethods.filtering.scanfilters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import net.sf.mzmine.data.PeakList;
import net.sf.mzmine.data.RawDataFile;
import net.sf.mzmine.desktop.Desktop;
import net.sf.mzmine.desktop.MZmineMenu;
import net.sf.mzmine.main.MZmineCore;
import net.sf.mzmine.modules.batchmode.BatchStep;
import net.sf.mzmine.modules.batchmode.BatchStepCategory;
import net.sf.mzmine.parameters.ParameterSet;
import net.sf.mzmine.taskcontrol.Task;
import net.sf.mzmine.util.GUIUtils;
import net.sf.mzmine.util.dialogs.ExitCode;

public class ScanFilters implements BatchStep, ActionListener {

	final String helpID = GUIUtils.generateHelpID(this);

	public static final String MODULE_NAME = "Scan by scan filtering";

	private ScanFiltersParameters parameters;
	private Desktop desktop;

	public ScanFilters() {

		this.desktop = MZmineCore.getDesktop();

		parameters = new ScanFiltersParameters();

		desktop.addMenuItem(MZmineMenu.RAWDATAFILTERING, MODULE_NAME,
				"Filters applied to individual scans", KeyEvent.VK_F, true,
				this, null);
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {

		RawDataFile[] dataFiles = desktop.getSelectedDataFiles();
		if (dataFiles.length == 0) {
			desktop.displayErrorMessage("Please select at least one data file");
			return;
		}

		ExitCode exitCode = parameters.showSetupDialog();

		if (exitCode != ExitCode.OK) {
			return;
		}

		runModule(dataFiles, null, parameters.clone());

	}

	/**
	 * @see net.sf.mzmine.modules.BatchStep#toString()
	 */
	public String toString() {
		return MODULE_NAME;
	}

	/**
	 * @see net.sf.mzmine.modules.BatchStep#setupParameters(net.sf.mzmine.data.ParameterSet)
	 */

	/**
	 * @see net.sf.mzmine.modules.MZmineModule#getParameterSet()
	 */
	public ParameterSet getParameterSet() {
		return parameters;
	}

	/**
	 * @see 
	 *      net.sf.mzmine.modules.BatchStep#runModule(net.sf.mzmine.data.RawDataFile
	 *      [], net.sf.mzmine.data.AlignmentResult[],
	 *      net.sf.mzmine.data.ParameterSet,
	 *      net.sf.mzmine.taskcontrol.Task[]Listener)
	 */
	public Task[] runModule(RawDataFile[] dataFiles, PeakList[] peakLists,
			ParameterSet parameters) {
		// check data files
		if ((dataFiles == null) || (dataFiles.length == 0)) {
			desktop.displayErrorMessage("Please select data files for peak picking");
			return null;
		}

		// prepare a new group of tasks
		Task tasks[] = new ScanFilteringTask[dataFiles.length];
		for (int i = 0; i < dataFiles.length; i++) {
			tasks[i] = new ScanFilteringTask(dataFiles[i],
					(ScanFiltersParameters) parameters);
		}

		MZmineCore.getTaskController().addTasks(tasks);

		return tasks;
	}

	public BatchStepCategory getBatchStepCategory() {
		return BatchStepCategory.RAWDATAPROCESSING;
	}
}
