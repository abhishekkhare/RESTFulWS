package com.edu.abhi.rest.providers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.ext.Provider;

import com.edu.abhi.rest.services.CarResourceMatrixAndPathParam.Color;
import com.edu.abhi.rest.util.ColorConverter;
import com.edu.abhi.rest.util.ParamConverter;

@Provider
public class ColorConverterProvider {
	private final ColorConverter converter = new ColorConverter();

	public <T> ParamConverter<Color> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		if (!rawType.equals(Color.class))
			return null;
		return converter;
	}
}
