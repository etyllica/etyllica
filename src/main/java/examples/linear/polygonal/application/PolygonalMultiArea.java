package examples.linear.polygonal.application;

import java.awt.Color;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.linear.Point2D;

public class PolygonalMultiArea extends Application {

	private Point2D p;
	private Point2D q;
	
	public PolygonalMultiArea(int w, int h) {
		super(w,h);
	}

	public void load() {

		p = new Point2D(100, 200);
		q = new Point2D(200, 200);
		
		loading = 99;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		
		int areas = 5;
		
		double distance = p.distance(q);
		
		Point2D[] pPoints = generatePoints(p,q,distance/areas);
		
		Point2D p1 = pPoints[0];
		Point2D p2 = pPoints[1];
		
		g.drawLine(p, q);
		drawPoint(g, p);
		drawPoint(g, q);
				
		drawPoint(g, p1);
		drawPoint(g, p2);
		g.drawLine(p, p1);
		g.drawLine(p, p2);
		
		double interval = distance/areas;
		
		Point2D c = p.distantPoint(q, interval);
		Point2D nq = q;
		
		for (int i = 0; i < areas; i++) {
			double subdistance = interval*(i+1);
			nq = p.distantPoint(q, subdistance+interval);
			
			Point2D[] cPoints = generatePoints(c,nq,subdistance);
			
			Point2D c1 = cPoints[0];
			Point2D c2 = cPoints[1];
			
			g.drawLine(c, c1);
			g.drawLine(c, c2);
			g.drawLine(p1, c1);
			g.drawLine(p2, c2);
			
			drawPoint(g, c1);
			drawPoint(g, c2);
			
			p1 = c1;
			p2 = c2;
			
			c = nq;
		}
			
	}
	
	private Point2D[] generatePoints(Point2D p, Point2D q, double d) {
		
		Point2D[] points = new Point2D[2];
		
		Point2D p1 = p.distantPoint(q, 50);
		p1.rotate(p, -90);
		Point2D p2 = p.distantPoint(q, 50);
		p2.rotate(p, +90);
		
		points[0] = p1;
		points[1] = p2;
		
		return points;
	}
	
	private void drawPoint(Graphics g, Point2D point) {
		g.fillCircle(point, 5);
	}
	
	@Override
	public void updateMouse(PointerEvent event) {
		//dynamic position
		q.setLocation(event.getX(), event.getY());
	}
	
}

