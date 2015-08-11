package br.com.etyllica.core.interpolation;

public class QuadraticInterpolator implements Interpolator {

	public double factor(double startValue, double endValue, double value) {
		double factor = (endValue-startValue)*value*value;
		factor += startValue;
		return factor;
	}

}
