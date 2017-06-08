package br.com.etyllica.commons.interpolation;

public class ReverseQuadraticInterpolator implements Interpolator {

	public double interpolate(double startValue, double endValue, double factor) {
		double value = endValue-startValue-((endValue-startValue)*((1-factor)*(1-factor)));
		value += startValue;
		return value;
	}

}
