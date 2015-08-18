package br.com.etyllica.core.interpolation;

public interface Interpolator {
	
	public static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
	public static final Interpolator QUADRATIC_INTERPOLATOR = new QuadraticInterpolator();
	public static final Interpolator REVERSE_QUADRATIC_INTERPOLATOR = new ReverseQuadraticInterpolator();
	
	double factor(double startValue, double endValue, double x);
}
