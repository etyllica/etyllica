package br.com.etyllica.util.math;

public class EtyllicaMath {

	public static double diffMod(double a, double b) {
		double diff = a-b;
		return mod(diff);
	}
	
	public static double sqr(double a) {
		return a*a;
	}

	public static double mod(double a) {
		if (a < 0) {
			return -a;
		}
		return a;
	}
	
	public static int max(int a, int b) {
		return a>b?a:b;
	}
	
	public static int max(int a, int b, int c) {
		return max(a, max(b,c));
	}
	
	public static int min(int a, int b) {
		return a<b?a:b;
	}
	
	public static int min(int a, int b, int c) {
		return min(a, min(b,c));
	}
	
}
