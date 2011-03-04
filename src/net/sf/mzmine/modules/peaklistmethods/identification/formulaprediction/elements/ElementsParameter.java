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

package net.sf.mzmine.modules.peaklistmethods.identification.formulaprediction.elements;

import net.sf.mzmine.parameters.UserParameter;

import org.w3c.dom.Element;

/**
 * Simple Parameter implementation
 * 
 * 
 */
public class ElementsParameter implements
		UserParameter<String, ElementsTableComponent> {

	private String name, description;
	private String value;

	public ElementsParameter(String name, String description) {
		this.name = name;
		this.description = description;
	}

	/**
	 * @see net.sf.mzmine.data.Parameter#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @see net.sf.mzmine.data.Parameter#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public ElementsTableComponent createEditingComponent() {
		ElementsTableComponent editor = new ElementsTableComponent();
		editor.setElementsFromString(value);
		return editor;
	}

	@Override
	public ElementsParameter clone() {
		ElementsParameter copy = new ElementsParameter(name, description);
		copy.value = value;
		return copy;
	}

	@Override
	public void setValueFromComponent(ElementsTableComponent component) {
		value = component.getElementsAsString();
	}

	@Override
	public void setValueToComponent(ElementsTableComponent component,
			String newValue) {
		component.setElementsFromString(newValue);
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String newValue) {
		this.value = newValue;
	}

	@Override
	public void loadValueFromXML(Element xmlElement) {
		value = xmlElement.getTextContent();
	}

	@Override
	public void saveValueToXML(Element xmlElement) {
		if (value == null)
			return;
		xmlElement.setTextContent(value);
	}

}
