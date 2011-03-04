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
 * MZmine 2; if not, write to the Free Software Foundation, Inc., 51 Franklin St,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */

package net.sf.mzmine.modules.peaklistmethods.alignment.join;

import java.text.NumberFormat;

import net.sf.mzmine.modules.peaklistmethods.isotopes.isotopepatternscore.IsotopePatternScoreParameters;
import net.sf.mzmine.parameters.UserParameter;
import net.sf.mzmine.parameters.SimpleParameterSet;
import net.sf.mzmine.parameters.parametertypes.BooleanParameter;
import net.sf.mzmine.parameters.parametertypes.MZToleranceParameter;
import net.sf.mzmine.parameters.parametertypes.NumberParameter;
import net.sf.mzmine.parameters.parametertypes.OptionalModuleParameter;
import net.sf.mzmine.parameters.parametertypes.RTToleranceParameter;
import net.sf.mzmine.parameters.parametertypes.StringParameter;

public class JoinAlignerParameters extends SimpleParameterSet {

	public static final StringParameter peakListName = new StringParameter(
			"Peak list name", "Peak list name", "Aligned peak list");

	public static final MZToleranceParameter MZTolerance = new MZToleranceParameter();

	public static final NumberParameter MZWeight = new NumberParameter(
			"Weight for m/z", "Score for perfectly matching m/z values",
			NumberFormat.getNumberInstance());

	public static final RTToleranceParameter RTTolerance = new RTToleranceParameter();

	public static final NumberParameter RTWeight = new NumberParameter(
			"Weight for RT", "Score for perfectly matching RT values",
			NumberFormat.getNumberInstance());

	public static final BooleanParameter SameChargeRequired = new BooleanParameter(
			"Require same charge state",
			"If checked, only rows having same charge state can be aligned");

	public static final BooleanParameter SameIDRequired = new BooleanParameter(
			"Require same ID",
			"If checked, only rows having same compound identities (or no identities) can be aligned");

	public static final OptionalModuleParameter compareIsotopePattern = new OptionalModuleParameter(
			"Compare isotope pattern",
			"If both peaks represent an isotope pattern, add isotope pattern score to match score",
			new IsotopePatternScoreParameters());

	public JoinAlignerParameters() {
		super(new UserParameter[] { peakListName, MZTolerance, MZWeight,
				RTTolerance, RTWeight, SameChargeRequired, SameIDRequired,
				compareIsotopePattern });
	}

}
