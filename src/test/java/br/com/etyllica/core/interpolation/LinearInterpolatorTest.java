package br.com.etyllica.core.interpolation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinearInterpolatorTest {

	Interpolator interpolator;
	
	private static final int START = 100;
	private static final int END = 200;
	
	@Before
	public void setUp() {
		interpolator = new LinearInterpolator();
	}
	
	@Test
	public void testStart() {
		double value = interpolator.factor(START, END, 0);
		Assert.assertEquals(START, value, 0);
	}
	
	@Test
	public void testMiddle() {
		double value = interpolator.factor(START, END, 0.5);
		Assert.assertEquals(START+50, value, 0);
	}
	
	@Test
	public void testEnd() {
		double value = interpolator.factor(START, END, 1);
		Assert.assertEquals(END, value, 0);
	}
	
}
