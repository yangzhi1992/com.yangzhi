package com.yz.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class ClassSetter {
	/**
	 * 设置默认值
	 * @param object
	 */
	public static <T> void setDefaultValueForObject(T object) {
		Field[] fieldArray = object.getClass().getDeclaredFields();
		for (Field field : fieldArray) {
			field.setAccessible(true);
			try {
				if (null == field.get(object)) {
					if (String.class.equals(field.getType())) {
						field.set(object, "");
					}
					if (Integer.class.equals(field.getType())) {
						field.set(object, new Integer(0));
					}
					if (BigDecimal.class.equals(field.getType())) {
						field.set(object, new BigDecimal(0));
					}
					if (Double.class.equals(field.getType())) {
						field.set(object, new Double(0));
					}
				}
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * 
	 */
	public static<T> void doForClassFields(T object,String field){
		
	}
}