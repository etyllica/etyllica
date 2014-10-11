package br.com.etyllica.collision;

import br.com.etyllica.layer.GeometricLayer;
import br.com.etyllica.layer.Layer;
import br.com.etyllica.linear.Point2D;

public class CollisionDetector {

	public static boolean colideCirclePoint(float cx, float cy, float radius, int px, int py) {

		float dx = cx - px;
		float dy = cy - py;

		if ( ( dx * dx )  + ( dy * dy ) < radius * radius )	{
			return true;
		}

		return false;
	}

	public static boolean colideIsometricPoint(Layer cam, int px, int py) {

		float my = cam.getH()/2;
		float mx = cam.getW()/2;

		float x = px-cam.getX();
		float y = py-cam.getY();

		if(y>+my)
			y=my-(y-my);

		if((x>mx+1+(2*y))||(x<mx-1-(2*y)))
			return false;

		return true;
	}

	public static boolean colideHexagonPoint(Layer cam, int px, int py) {

		float my = cam.getH()/2;
		float mx = cam.getW()/4;

		float x = px-cam.getX();
		float y = py-cam.getY();

		if(x > mx*3) {
			x = mx-(x-mx*3);
		} else if(x > mx) {
			return py >= cam.getY() && py <= cam.getY()+cam.getH();
		}

		if((y > my + 1 + (2*x))||(y<my - 1 - (2*x)))
			return false;

		return true;
	}

	public static boolean colideRectPoint(GeometricLayer layer, double  px, double py) {
		int rectCenterX = layer.getX()+layer.getW()/2;
		int rectCenterY = layer.getY()+layer.getH()/2;
		int rectWidth = layer.getW();
		int rectHeight = layer.getH();

		return Math.abs(rectCenterX-px) < rectWidth/2 && Math.abs(rectCenterY-py) < rectHeight/2;
	}
	
	/**
	 * Code found at: http://stackoverflow.com/questions/5650032/collision-detection-with-rotated-rectangles
	 */
	public static boolean colideRectPoint(Layer layer, double  px, double py) {
		int rectCenterX = layer.getX()+layer.getW()/2;
		int rectCenterY = layer.getY()+layer.getH()/2;
		int rectWidth = layer.getW();
		int rectHeight = layer.getH();

		double rectRotation = Math.toRadians(-layer.getAngle());

		return testRectangleToPoint(rectWidth, rectHeight, rectRotation, rectCenterX, rectCenterY, px, py);
	}
	
	public static boolean colideRectPoint(Layer layer, double  px, double py, double scaleX, double scaleY) {
		
		int w = (int)(layer.getW()*scaleX);
		int h = (int)(layer.getH()*scaleY);
		
		int rectCenterX = layer.getX()+w/2-(int)(layer.getW()/scaleX);
		int rectCenterY = layer.getY()+h/2-(int)(layer.getH()/scaleY);
		int rectWidth = w;
		int rectHeight = h;

		double rectRotation = Math.toRadians(-layer.getAngle());

		return testRectangleToPoint(rectWidth, rectHeight, rectRotation, rectCenterX, rectCenterY, px, py);
	}

	public static boolean colideRectPoint(Layer layer, Point2D point) {

		int rectCenterX = layer.getX()+layer.getW()/2;
		int rectCenterY = layer.getY()+layer.getH()/2;
		int rectWidth = layer.getW();
		int rectHeight = layer.getH();

		double rectRotation = Math.toRadians(-layer.getAngle());

		return testRectangleToPoint(rectWidth, rectHeight, rectRotation, rectCenterX, rectCenterY, point.getX(), point.getY());
	}

	/** Rectangle To Point. */
	private static boolean testRectangleToPoint(double rectWidth, double rectHeight, double rectRotation, double rectCenterX, double rectCenterY, double pointX, double pointY) {

		if(rectRotation == 0)   // Higher Efficiency for Rectangles with 0 rotation.
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

		if(rectRotation == 0) { // Higher Efficiency for Rectangles with 0 rotation.
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

			if(b.getX() + b.getW() < a.getX())	return false;
			if(b.getX() > a.getX() + a.getW())		return false;

			if(b.getY() + b.getH() < a.getY())	return false;
			if(b.getY() > a.getY() + a.getH())		return false;

			return true;

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
		points[1] = new Point2D(layer.getX()+layer.getW(), layer.getY());
		points[2] = new Point2D(layer.getX()+layer.getW(), layer.getY()+layer.getH());
		points[3] = new Point2D(layer.getX(), layer.getY()+layer.getH());

		if(layer.getAngle() != 0) {
			for(Point2D point: points) {
				point.rotate(layer.getX()+layer.getW()/2, layer.getY()+layer.getH()/2, layer.getAngle());
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

}
