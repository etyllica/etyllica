package br.com.etyllica.util;

public class ReflectionUtils {

	public static Class<?> getArrayClass(Class<?> clazz) {
		Class<?> arrayClass = clazz;
		
		try {
			arrayClass = Class.forName("[L" + arrayClass.getName() + ";");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return arrayClass;
	}
	
}
