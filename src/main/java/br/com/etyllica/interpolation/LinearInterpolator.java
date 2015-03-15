package br.com.etyllica.interpolation;

public class LinearInterpolator implements Interpolator {

	public double factor(double startValue, double endValue, double value) {
		double factor = (endValue-startValue)*value;
		factor += startValue;
		return factor;
	}

}
