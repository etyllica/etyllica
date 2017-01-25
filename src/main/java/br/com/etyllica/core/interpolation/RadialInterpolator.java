package br.com.etyllica.core.interpolation;

public class RadialInterpolator implements Interpolator {

	public double interpolate(double startValue, double endValue, double factor) {
		
		double radial = Math.sin(Math.toRadians(180-90*factor));
		double value = (endValue-startValue)*radial;
		
		value += startValue;
		return value;
	}

}
