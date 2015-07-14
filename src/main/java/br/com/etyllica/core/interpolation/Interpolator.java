package br.com.etyllica.core.interpolation;

public interface Interpolator {
	double factor(double startValue, double endValue, double x);
}
