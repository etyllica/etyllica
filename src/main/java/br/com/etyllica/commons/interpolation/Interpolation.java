package br.com.etyllica.commons.interpolation;

public class Interpolation {
	
	public static float factor(long now, long start, long duration) {
		return (now - start) / (float)duration;
	}
	
}
