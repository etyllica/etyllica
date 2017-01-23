package br.com.etyllica.core.interpolation;

public class LinearInterpolator implements Interpolator {

	public double interpolate(double startValue, double endValue, double factor) {
		double value = (endValue-startValue)*factor;
		value += startValue;
		return value;
	}

}
