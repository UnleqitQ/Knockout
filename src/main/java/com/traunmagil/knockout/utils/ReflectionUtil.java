package com.traunmagil.knockout.utils;

import java.lang.reflect.Field;

public class ReflectionUtil {

	
    /**
     * Use reflection to change value of any instance field.
     * @param classInstance An Object instance.
     * @param fieldName The name of a field in the class instantiated by classInstancee
     * @param newValue The value you want the field to be set to.
     * @throws SecurityException .
     * @throws NoSuchFieldException .
     * @throws ClassNotFoundException .
     * @throws IllegalArgumentException .
     * @throws IllegalAccessException .
     */
    public static void setInstanceValue(final Object classInstance, final String fieldName, final Object newValue) throws SecurityException,
	    NoSuchFieldException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
	// Get the private field
	final Field field = classInstance.getClass().getDeclaredField(fieldName);
	// Allow modification on the field
	field.setAccessible(true);
	// Sets the field to the new value for this instance
	field.set(classInstance, newValue);
 
    }
	
}
