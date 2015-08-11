package br.com.etyllica.core.interpolation;

public class ReverseQuadraticInterpolator implements Interpolator {

	public double factor(double startValue, double endValue, double value) {
		double factor = ((endValue-startValue)*(1-value)*(1-value));
		factor += startValue;
		return factor;
	}

}
