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

package net.sf.mzmine.modules.peaklistmethods.isotopes.isotopeprediction;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import net.sf.mzmine.data.Polarity;
import net.sf.mzmine.parameters.UserParameter;
import net.sf.mzmine.parameters.SimpleParameterSet;
import net.sf.mzmine.parameters.parametertypes.ComboParameter;
import net.sf.mzmine.parameters.parametertypes.NumberParameter;
import net.sf.mzmine.parameters.parametertypes.StringParameter;

public class IsotopePatternCalculatorParameters extends SimpleParameterSet {

	public static final StringParameter formula = new StringParameter(
			"Chemical formula", "empirical formula of a chemical compound");

	public static final NumberParameter charge = new NumberParameter("Charge",
			"Charge of the molecule", NumberFormat.getIntegerInstance());

	public static final ComboParameter<Polarity> polarity = new ComboParameter<Polarity>(
			"Polarity", "Set positive or negative the charge of the molecule ",
			Polarity.values());

	// We do not use NumberFormat.getPercentInstance() for this parameter,
	// because it only shows integers
	public static final NumberParameter minAbundance = new NumberParameter(
			"Minimum abundance", "Minimum abundance of the detected isotope",
			new DecimalFormat("0.00%"), 0.001);

	public IsotopePatternCalculatorParameters() {
		super(new UserParameter[] { formula, charge, polarity, minAbundance });
	}

}
