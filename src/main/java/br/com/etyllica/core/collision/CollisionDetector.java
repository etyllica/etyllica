package br.com.etyllica.core.collision;

import br.com.etyllica.core.linear.Point2D;
import br.com.etyllica.layer.GeometricLayer;
import br.com.etyllica.layer.Layer;
import br.com.etyllica.linear.Ellipse;
import br.com.etyllica.util.EtyllicaMath;

public class CollisionDetector {

	public static boolean colideCirclePoint(float cx, float cy, float radius, int px, int py) {

		float dx = cx - px;
		float dy = cy - py;

		if ( ( dx * dx )  + ( dy * dy ) < radius * radius )	{
			return true;
		}

		return false;
	}
	
	/**
	 * Found at: http://stackoverflow.com/a/402010
	 * @param circle - the circular layer
	 * @param rect - the rectangular layer
	 * @return collision detected
	 */
	public static boolean colideCircleRect(Layer circle, Layer rect) {
		int centerX = circle.getX()+circle.getW()/2;
		int centerY = circle.getY()+circle.getH()/2;
		int radius = circle.getW();
		
		double circleDistanceX = Math.abs(centerX - rect.getX());
		double circleDistanceY = Math.abs(centerY - rect.getY());

		if (circleDistanceX > (rect.getW()/2 + radius)) { return false; }
		if (circleDistanceY > (rect.getH()/2 + radius)) { return false; }

		if (circleDistanceX <= (rect.getW()/2)) { return true; } 
		if (circleDistanceY <= (rect.getH()/2)) { return true; }

		double cornerDistance = Math.pow((circleDistanceX - rect.getW()/2),2) +
				Math.pow((circleDistanceY - rect.getH()/2),2);

		return (cornerDistance <= (radius*radius));
	}

	public static boolean colideIsometricPoint(Layer cam, int px, int py) {

		float mx = cam.utilWidth()/2;
		float my = cam.utilHeight()/2;

		float x = px-cam.getX();
		float y = py-cam.getY();

		if(y > my) {
			y = my-(y-my);
		}

		if ((x > mx+1+(2*y))||(x < mx-1-(2*y))) {
			return false;
		}

		return true;
	}

	//Checks collision with a vertical hexagon
	public static boolean colideHexagonPoint(Layer cam, int px, int py) {

		float mx = cam.utilWidth()/4;
		float my = cam.utilHeight()/2;

		float x = px-cam.getX();
		float y = py-cam.getY();

		if(x > mx*3) {
			x = mx-(x-mx*3);
		} else if(x > mx) {
			return py >= cam.getY() && py <= cam.getY()+cam.utilHeight();
		}

		if ((y > my + 1 + (2*x)) || (y < my - 1 - (2*x))) {
			return false;
		}

		return true;
	}

	public static boolean colideRectPoint(GeometricLayer layer, double  px, double py) {

		int rectWidth = layer.utilWidth();
		int rectHeight = layer.utilHeight();
		int rectCenterX = layer.getX()+rectWidth/2;
		int rectCenterY = layer.getY()+rectHeight/2;

		return Math.abs(rectCenterX-px) <= rectWidth/2 && Math.abs(rectCenterY-py) <= rectHeight/2;
	}

	/**
	 * Code found at: http://stackoverflow.com/questions/5650032/collision-detection-with-rotated-rectangles
	 */
	public static boolean colideRectPoint(Layer layer, double  px, double py) {

		int rectWidth = layer.utilWidth();
		int rectHeight = layer.utilHeight();
		int offsetX = 0;
		int offsetY = 0;

		if (layer.getScaleX() != 1) {
			rectWidth *= layer.getScaleX();
			offsetX = (int)(layer.utilWidth()*(1-layer.getScaleX()))/2;
		}

		if (layer.getScaleY() != 1) {
			rectHeight *= layer.getScaleY();
			offsetY = (int)(layer.utilHeight()*(1-layer.getScaleY()))/2;
		}

		int rectCenterX = layer.getX()+offsetX+rectWidth/2;
		int rectCenterY = layer.getY()+offsetY+rectHeight/2;

		double rectRotation = Math.toRadians(-layer.getAngle());

		return testRectangleToPoint(rectWidth, rectHeight, rectRotation, rectCenterX, rectCenterY, px, py);
	}

	public static boolean colideRectPoint(Layer layer, double  px, double py, double scaleX, double scaleY) {

		int rectWidth = layer.utilWidth();
		int rectHeight = layer.utilHeight();
		int offsetX = 0;
		int offsetY = 0;

		if(layer.getScaleX()!=1) {
			rectWidth *= layer.getScaleX();
			offsetX = (int)(layer.utilWidth()*(1-layer.getScaleX()))/2;
		}

		if(layer.getScaleY()!=1) {
			rectHeight *= layer.getScaleY();
			offsetY = (int)(layer.utilHeight()*(1-layer.getScaleY()))/2;
		}

		int rectCenterX = layer.getX()+offsetX+rectWidth/2;
		int rectCenterY = layer.getY()+offsetY+rectHeight/2;

		double rectRotation = Math.toRadians(-layer.getAngle());

		return testRectangleToPoint(rectWidth, rectHeight, rectRotation, rectCenterX, rectCenterY, px, py);
	}

	public static boolean colideRectPoint(Layer layer, Point2D point) {

		int rectCenterX = layer.getX()+layer.utilWidth()/2;
		int rectCenterY = layer.getY()+layer.utilHeight()/2;
		int rectWidth = layer.utilWidth();
		int rectHeight = layer.utilHeight();

		double rectRotation = Math.toRadians(-layer.getAngle());

		return testRectangleToPoint(rectWidth, rectHeight, rectRotation, rectCenterX, rectCenterY, point.getX(), point.getY());
	}

	/** Rectangle To Point. */
	private static boolean testRectangleToPoint(double rectWidth, double rectHeight, double rectRotation, double rectCenterX, double rectCenterY, double pointX, double pointY) {

		if(rectRotation == 0)   //High speed for rectangles without rotation
			return Math.abs(rectCenterX-pointX) < rectWidth/2 && Math.abs(rectCenterY-pointY) < rectHeight/2;

		final double cos = Math.cos(rectRotation);
		final double sin = Math.cos(rectRotation);

		double tx = cos*pointX - sin*pointY;
		double ty = cos*pointY + sin*pointX;

		double cx = cos*rectCenterX - sin*rectCenterY;
		double cy = cos*rectCenterY + sin*rectCenterX;

		return Math.abs(cx-tx) < rectWidth/2 && Math.abs(cy-ty) < rectHeight/2;
	}

	/** Circle To Segment. */
	private static boolean testCircleToSegment(double circleCenterX, double circleCenterY, double circleRadius, double lineAX, double lineAY, double lineBX, double lineBY) {
		double lineSize = Math.sqrt(Math.pow(lineAX-lineBX, 2) + Math.pow(lineAY-lineBY, 2));
		double distance;

		if (lineSize == 0) {
			distance = Math.sqrt(Math.pow(circleCenterX-lineAX, 2) + Math.pow(circleCenterY-lineAY, 2));
			return distance < circleRadius;
		}

		double u = ((circleCenterX - lineAX) * (lineBX - lineAX) + (circleCenterY - lineAY) * (lineBY - lineAY)) / (lineSize * lineSize);

		if (u < 0) {
			distance = Math.sqrt(Math.pow(circleCenterX-lineAX, 2) + Math.pow(circleCenterY-lineAY, 2));
		} else if (u > 1) {
			distance = Math.sqrt(Math.pow(circleCenterX-lineBX, 2) + Math.pow(circleCenterY-lineBY, 2));
		} else {
			double ix = lineAX + u * (lineBX - lineAX);
			double iy = lineAY + u * (lineBY - lineAY);
			distance = Math.sqrt(Math.pow(circleCenterX-ix, 2) + Math.pow(circleCenterY-iy, 2));
		}

		return distance < circleRadius;
	}

	/** Rectangle To Circle. */
	public static boolean testRectangleToCircle(double rectWidth, double rectHeight, double rectRotation, double rectCenterX, double rectCenterY, double circleCenterX, double circleCenterY, double circleRadius) {
		double tx, ty, cx, cy;

		if(rectRotation == 0) { //High speed for rectangles without rotation
			tx = circleCenterX;
			ty = circleCenterY;

			cx = rectCenterX;
			cy = rectCenterY;
		} else {
			tx = Math.cos(rectRotation)*circleCenterX - Math.sin(rectRotation)*circleCenterY;
			ty = Math.cos(rectRotation)*circleCenterY + Math.sin(rectRotation)*circleCenterX;

			cx = Math.cos(rectRotation)*rectCenterX - Math.sin(rectRotation)*rectCenterY;
			cy = Math.cos(rectRotation)*rectCenterY + Math.sin(rectRotation)*rectCenterX;
		}

		return testRectangleToPoint(rectWidth, rectHeight, rectRotation, rectCenterX, rectCenterY, circleCenterX, circleCenterY) ||
				testCircleToSegment(tx, ty, circleRadius, cx-rectWidth/2, cy+rectHeight/2, cx+rectWidth/2, cy+rectHeight/2) ||
				testCircleToSegment(tx, ty, circleRadius, cx+rectWidth/2, cy+rectHeight/2, cx+rectWidth/2, cy-rectHeight/2) ||
				testCircleToSegment(tx, ty, circleRadius, cx+rectWidth/2, cy-rectHeight/2, cx-rectWidth/2, cy-rectHeight/2) ||
				testCircleToSegment(tx, ty, circleRadius, cx-rectWidth/2, cy-rectHeight/2, cx-rectWidth/2, cy+rectHeight/2);
	}

	public static boolean colideRectRect(Layer a, Layer b) {
		if(a.getAngle() == 0 && b.getAngle() == 0) {
			return ((a.getX() + a.utilWidth()/2 - b.getX()+b.utilWidth()/2) * 2 < (a.utilWidth() + b.utilWidth())) &&
					((a.getY() + a.utilHeight()/2 - b.getY()+b.utilHeight()/2) * 2 < (a.utilHeight() + b.utilHeight()));
		} else {
			return colidePolygon(a, b);
		}
	}

	/**
	 * Code found at: http://stackoverflow.com/a/21647567
	 */
	public static boolean colidePolygon(Layer a, Layer b) {
		Point2D[] pointsA = getBounds(a);
		Point2D[] pointsB = getBounds(b);

		return colidePoints(pointsA, pointsB);
	}

	public static Point2D[] getBounds(Layer layer) {
		Point2D[] points = new Point2D[4];

		points[0] = new Point2D(layer.getX(), layer.getY());
		points[1] = new Point2D(layer.getX()+layer.utilWidth(), layer.getY());
		points[2] = new Point2D(layer.getX()+layer.utilWidth(), layer.getY()+layer.utilHeight());
		points[3] = new Point2D(layer.getX(), layer.getY()+layer.utilHeight());

		if(layer.getAngle() != 0) {
			for(Point2D point: points) {
				point.rotate(layer.getX()+layer.utilWidth()/2, layer.getY()+layer.utilHeight()/2, layer.getAngle());
			}
		}

		return points;
	}

	private static  double[] e2p(double x, double y) {
		return new double[] { x, y, 1 };
	}

	// standard vector maths
	private static double[] getCrossProduct(double[] u, double[] v) {
		return new double[] { u[1] * v[2] - u[2] * v[1],
				u[2] * v[0] - u[0] * v[2], u[0] * v[1] - u[1] * v[0] };
	}

	private static double getDotProduct(double[] u, double[] v) {
		return u[0] * v[0] + u[1] * v[1] + u[2] * v[2];
	}

	// collision check
	public static boolean colidePoints(Point2D[] pointsA, Point2D[] pointsB) {
		return !(isSeparate(pointsA, pointsB) || isSeparate(pointsB, pointsA));
	}

	// the following implementation expects the convex polygon's vertices to be in counter clockwise order
	private static boolean isSeparate(Point2D[] coordsA, Point2D[] coordsB) {

		edges: for (int i = 0; i < coordsA.length; i++) {

			double[] u = e2p(coordsA[i].getX(), coordsA[i].getY());
			int ni = i + 1 < coordsA.length ? i + 1 : 0;
			double[] v = e2p(coordsA[ni].getX(), coordsA[ni].getY());
			double[] pedge = getCrossProduct(u, v);

			for (Point2D p : coordsB) {
				double d = getDotProduct(pedge, e2p(p.getX(), p.getY()));
				if (d > -0.001) {
					continue edges;
				}
			}
			return true;
		}

	return false;
	}

	public static boolean colideEllipsePoint(Ellipse ellipse, double px, double py) {
		double cx = ellipse.getCenter().getX();
		double cy = ellipse.getCenter().getY();
		double angle = Math.toRadians(ellipse.getAngle());
		double a = ellipse.getW();
		double b = ellipse.getH();

		return colideEllipsePoint(cx, cy, angle, a, b, px, py);
	}

	public static boolean colideEllipsePoint(double cx, double cy, double angle, double a, double b, double px, double py) {

		final double cos = Math.cos(angle);
		final double sin = Math.sin(angle);

		double p = EtyllicaMath.sqr(cos*(px-cx)+sin*(py-cy))/(a*a);
		double q = EtyllicaMath.sqr(sin*(px-cx)-cos*(py-cy))/(b*b);

		return (p + q <= 1);
	}

}
