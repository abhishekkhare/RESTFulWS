package com.edu.abhi.rest.util;

import com.edu.abhi.rest.services.CarResourceMatrixAndPathParam.Color;
/**
 * 
 * @author abhishekkhare
 *
 */
public class ColorConverter implements ParamConverter<Color> {
	public Color fromString(String value) {
		if (value.equalsIgnoreCase(Color.black.toString()))
			return Color.black;
		else if (value.equalsIgnoreCase(Color.blue.toString()))
			return Color.blue;
		else if (value.equalsIgnoreCase(Color.red.toString()))
			return Color.red;
		else if (value.equalsIgnoreCase(Color.white.toString()))
			return Color.white;
		else if (value.equalsIgnoreCase(Color.grey.toString()))
			return Color.grey;
		throw new IllegalArgumentException("Invalid color: " + value);
	}

	public String toString(Color value) {
		return value.toString();
	}
}
