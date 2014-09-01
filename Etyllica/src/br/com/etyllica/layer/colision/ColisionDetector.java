package br.com.etyllica.layer.colision;

import br.com.etyllica.layer.Layer;
import br.com.etyllica.linear.Point2D;

public class ColisionDetector {

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

	    double tx = Math.cos(rectRotation)*pointX - Math.sin(rectRotation)*pointY;
	    double ty = Math.cos(rectRotation)*pointY + Math.sin(rectRotation)*pointX;

	    double cx = Math.cos(rectRotation)*rectCenterX - Math.sin(rectRotation)*rectCenterY;
	    double cy = Math.cos(rectRotation)*rectCenterY + Math.sin(rectRotation)*rectCenterX;

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
	
	/**
	 * Code found at http://forums.coronalabs.com/topic/39094-code-for-rotated-rectangle-collision-detection/
	 */
	public static boolean colideRectRect(Layer obj, Layer obj2) {

		int x10 = obj.getX();
		int y10 = obj.getY();

		int height1 = obj.getH()/2;
		int width1 = obj.getW()/2;

		double radrot1 = Math.toRadians(obj.getAngle());

		int x20 = obj2.getX();
		int y20 = obj2.getY();

		int height2 = obj2.getH()/2;
		int width2 = obj2.getW()/2;

		double radrot2 = Math.toRadians(obj2.getAngle());

		double radius1 = Math.sqrt( height1*height1 + width1*width1 );
		double radius2 = Math.sqrt( height2*height2 + width2*width2 );

		double angle1 = Math.asin( height1 / radius1 );
		double angle2 = Math.asin( height2 / radius2 );

		double[] x1 = new double[4];
		double[] y1 = new double[4];
		double[] x2 = new double[4]; 
		double[] y2 = new double[4];

		x1[0] = x10 + radius1 * Math.cos(radrot1 - angle1); y1[0] = y10 + radius1 * Math.sin(radrot1 - angle1);
		x1[1] = x10 + radius1 * Math.cos(radrot1 + angle1); y1[1] = y10 + radius1 * Math.sin(radrot1 + angle1);
		x1[2] = x10 + radius1 * Math.cos(radrot1 + Math.PI - angle1); y1[2] = y10 + radius1 * Math.sin(radrot1 + Math.PI - angle1);
		x1[3] = x10 + radius1 * Math.cos(radrot1 + Math.PI + angle1); y1[3] = y10 + radius1 * Math.sin(radrot1 + Math.PI + angle1);

		x2[0] = x20 + radius2 * Math.cos(radrot2 - angle2); y2[0] = y20 + radius2 * Math.sin(radrot2 - angle2);
		x2[1] = x20 + radius2 * Math.cos(radrot2 + angle2); y2[1] = y20 + radius2 * Math.sin(radrot2 + angle2);
		x2[2] = x20 + radius2 * Math.cos(radrot2 + Math.PI - angle2); y2[2] = y20 + radius2 * Math.sin(radrot2 + Math.PI - angle2);
		x2[3] = x20 + radius2 * Math.cos(radrot2 + Math.PI + angle2); y2[3] = y20 + radius2 * Math.sin(radrot2 + Math.PI + angle2);

		double[] axisx = new double[4];
		double[] axisy = new double[4];

		axisx[0] = x1[0] - x1[1]; axisy[0] = y1[0] - y1[1];
		axisx[1] = x1[2] - x1[1]; axisy[1] = y1[2] - y1[1];

		axisx[2] = x2[0] - x2[1]; axisy[2] = y2[0] - y2[1];
		axisx[3] = x2[2] - x2[1]; axisy[3] = y2[2] - y2[1];

		for(int k = 0; k<4; k++) {

			double proj = x1[0] * axisx[k] + y1[0] * axisy[k];

			double minProj1 = proj;
			double maxProj1 = proj;

			for(int i = 1; i < 4; i++) {
				proj = x1[i] * axisx[k] + y1[i] * axisy[k];

				if(proj < minProj1) {
					minProj1 = proj;
				}else if(proj > maxProj1) {
					maxProj1 = proj;
				}
			}

			proj = x2[0] * axisx[k] + y2[0] * axisy[k];

			double minProj2 = proj;
			double maxProj2 = proj;

			for(int j = 1; j < 4; j++) {
				proj = x2[j] * axisx[k] + y2[j] * axisy[k];

				if(proj < minProj2){
					minProj2 = proj;
				}else if(proj > maxProj2) {
					maxProj2 = proj;
				}

				if(maxProj2 < minProj1 || maxProj1 < minProj2) {
					return false;
				}
			}
		}
		
		return true;
	}

}
