package br.com.etyllica.core.math;

import br.com.etyllica.core.linear.Point3D;

/**
 * PointLineTest - simple geometry to make things easy!
 * Forked from jdt Triangulation: https://code.google.com/archive/p/jdt/
 */
public class PointLineTest {
		
	/**
	 * tests the relation between this point (as a 2D [x,y] point) and a 2D
	 * segment a,b (the Z values are ignored), returns one of the following:
	 * LEFT, RIGHT, INFRONT_OF_A, BEHIND_B, ON_SEGMENT
	 * 
	 * @param a
	 *            the first point of the segment.
	 * @param b
	 *            the second point of the segment.
	 * @return the value (flag) of the relation between this point and the a,b
	 *         line-segment.
	 */
	public static PointLinePosition pointLineTest(Point3D a, Point3D b, Point3D c) {

		double dx = b.getX() - a.getX();
		double dy = b.getY() - a.getY();
		double res = dy * (c.getX() - a.getX()) - dx * (c.getY() - a.getY());

		if (res < 0)
			return PointLinePosition.LEFT;
		if (res > 0)
			return PointLinePosition.RIGHT;

		if (dx > 0) {
			if (c.getX() < a.getX())
				return PointLinePosition.INFRONT_OF_A;
			if (b.getX() < c.getX())
				return PointLinePosition.BEHIND_B;
			return PointLinePosition.ON_SEGMENT;
		}
		if (dx < 0) {
			if (c.getX() > a.getX())
				return PointLinePosition.INFRONT_OF_A;
			if (b.getX() > c.getX())
				return PointLinePosition.BEHIND_B;
			return PointLinePosition.ON_SEGMENT;
		}
		if (dy > 0) {
			if (c.getY() < a.getY())
				return PointLinePosition.INFRONT_OF_A;
			if (b.getY() < c.getY())
				return PointLinePosition.BEHIND_B;
			return PointLinePosition.ON_SEGMENT;
		}
		if (dy < 0) {
			if (c.getY() > a.getY())
				return PointLinePosition.INFRONT_OF_A;
			if (b.getY() > c.getY())
				return PointLinePosition.BEHIND_B;
			return PointLinePosition.ON_SEGMENT;
		}
		System.out.println("Error, pointLineTest with a=b");
		return PointLinePosition.ERROR;
	}
	
}

