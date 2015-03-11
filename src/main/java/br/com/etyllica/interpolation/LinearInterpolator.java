package br.com.etyllica.interpolation;

public class LinearInterpolator implements Interpolator {

	public double factor(double startValue, double endValue, double value) {
		double factor = startValue+(endValue-startValue)*value;
		return factor;
	}

}
