package br.com.etyllica.core.interpolation;

public class ReverseQuadraticInterpolator implements Interpolator {

	public double interpolate(double startValue, double endValue, double fator) {
		double value = endValue-startValue-((endValue-startValue)*((1-fator)*(1-fator)));
		value += startValue;
		return value;
	}

}
