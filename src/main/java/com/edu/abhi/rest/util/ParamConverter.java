package com.edu.abhi.rest.util;
/**
 * 
 * @author abhishekkhare
 *
 * @param <T>
 */
public interface ParamConverter<T> {
	public T fromString(String value);

	public String toString(T value);
}
