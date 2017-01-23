package br.com.etyllica.core.interpolation;

public interface Interpolator {
	
	public static final Interpolator LINEAR = new LinearInterpolator();
	public static final Interpolator QUADRATIC = new QuadraticInterpolator();
	public static final Interpolator REVERSE_QUADRATIC = new ReverseQuadraticInterpolator();
	
	double interpolate(double startValue, double endValue, double factor);
}
