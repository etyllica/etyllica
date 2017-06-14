package examples.linear.polygonal.application;

import java.awt.Color;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.linear.Point2D;

public class PolygonalArea extends Application {

	private Point2D p;
	private Point2D q;
	
	public PolygonalArea(int w, int h) {
		super(w,h);
	}

	public void load() {

		p = new Point2D(100, 200);
		q = new Point2D(200, 300);
		
		loading = 99;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		
		g.drawLine(p, q);
		drawPoint(g, p);
		drawPoint(g, q);
		
		Point2D p1 = p.distantPoint(q, 50);
		p1.rotate(p, -90);
		Point2D p2 = p.distantPoint(q, 50);
		p2.rotate(p, +90);
		
		Point2D q1 = p.distantPoint(q, p.distance(q)+50);
		q1.rotate(q, -90);
		Point2D q2 = p.distantPoint(q, p.distance(q)+50);
		q2.rotate(q, +90);
		
		g.drawLine(p, p1);
		g.drawLine(p, p2);		
		g.drawLine(q, q1);
		g.drawLine(q, q2);
		
		g.drawLine(p1, q1);
		g.drawLine(p2, q2);
		
		drawPoint(g, p1);
		drawPoint(g, p2);
		drawPoint(g, q1);
		drawPoint(g, q2);		
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

