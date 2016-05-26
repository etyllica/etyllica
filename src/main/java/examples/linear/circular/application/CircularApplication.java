package examples.linear.circular.application;

import java.awt.Color;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.core.linear.Point2D;

public class CircularApplication extends Application {

	private static final int NUM_POINTS = 20;
	private static final int RADIUS = 50;
	private Point2D center;
	
	public CircularApplication(int w, int h) {
		super(w,h);
	}

	public void load() {
		center = new Point2D(100, 200);
		
		loading = 99;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		
		drawPoint(g, center);
		
		for(int i = 0; i < NUM_POINTS; i++) {
			Point2D point = new Point2D(center);
			point.setOffsetX(RADIUS);
			point.rotate(center, (double)(360/NUM_POINTS)*i);
			drawPoint(g, point);
		}
	}
	
	private void drawPoint(Graphics g, Point2D point) {
		g.fillCircle(point, 5);
	}
	
	@Override
	public void updateMouse(PointerEvent event) {
		//dynamic position
		center.setLocation(event.getX(), event.getY());
	}

}

