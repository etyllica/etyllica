package examples.linear.arrow.application;

import java.awt.Color;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.linear.Point2D;

public class ArrowApplication extends Application {

	private Point2D p;
	private Point2D q;
	
	public ArrowApplication(int w, int h) {
		super(w,h);
	}

	public void load() {

		p = new Point2D(100, 200);
		q = new Point2D(200, 300);
		
		loading = 100;
	}
	
	public void draw(Graphic g) {
		g.setColor(Color.BLACK);
		g.drawArrow(p, q);
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		//dynamic position
		q.setLocation(event.getX(), event.getY());
		
		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		return GUIEvent.NONE;
	}

}

