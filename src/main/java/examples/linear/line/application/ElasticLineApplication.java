package examples.linear.line.application;

import java.awt.Color;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.context.UpdateIntervalListener;
import br.com.etyllica.core.event.MouseButton;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.core.linear.Point2D;
import br.com.etyllica.util.PointUtils;

public class ElasticLineApplication extends Application implements UpdateIntervalListener {

	private Point2D p;
	private Point2D q;

	private boolean pressed = false;
	private boolean back = false;
	
	private int currentArea = 0;
	private int divisions = 6;

	public ElasticLineApplication(int w, int h) {
		super(w,h);
	}

	public void load() {

		p = new Point2D(100, 200);
		q = new Point2D(200, 300);

		updateAtFixedRate(100, this);
		loading = 100;
	}

	@Override
	public void timeUpdate(long now) {

		if(pressed) {
			if(!back) {
				currentArea++;	
			} else {
				currentArea--;
				if(currentArea < 0) {
					pressed = false;
				}
			}
		}

		if(currentArea >= divisions) {
			back = true;
		}
	}

	public void draw(Graphics g) {
		
		if(pressed) {
			g.setColor(Color.BLUE);
			double distance = p.distance(q);
			double index = distance/divisions*currentArea;
			
			Point2D distantPoint = PointUtils.distantPoint(p, q, index);
			g.drawLine(p, distantPoint);
		} else {
			g.setColor(Color.BLACK);
			g.drawLine(p, q);	
		}
	}

	@Override
	public void updateMouse(PointerEvent event) {
		q.setLocation(event.getX(), event.getY());

		if(event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {
			pressed = true;
			back = false;
			currentArea = 0;
		}
	}
}

