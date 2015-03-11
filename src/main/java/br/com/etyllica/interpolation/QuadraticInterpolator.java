package br.com.etyllica.interpolation;

public class QuadraticInterpolator implements Interpolator {

	public double factor(double startValue, double endValue, double value) {
		double factor = startValue+((value*value)/endValue);
		return factor;
	}

}
