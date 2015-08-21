package br.com.etyllica.util;

public class EtyllicaMath {

	public static double diffMod(double a, double b) {
		
		double diff = a-b;
		
		if(diff<0) {
			diff = -diff;
		}
		
		return diff;
	}
	
	public static double sqr(double a) {
		return a*a;
	}
	
}
