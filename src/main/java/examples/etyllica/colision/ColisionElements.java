package examples.etyllica.colision;

import java.awt.Color;

import br.com.etyllica.collision.CollisionDetector;
import br.com.etyllica.context.Application;
import br.com.etyllica.context.IntervalUpdate;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.layer.Layer;

public class ColisionElements extends Application implements IntervalUpdate {

	private Color color = Color.BLUE;

	private Layer rectangle1;

	private Layer rectangle2;
	
	private int mx = 0;
	
	private int my = 0;	

	public ColisionElements(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		rectangle1 = new Layer(80, 100, 200, 50);
		rectangle1.setAngle(20);

		rectangle2 = new Layer(200, 200, 200, 50);

		updateAtFixedRate(100, this);

		loading = 100;
	}

	public void timeUpdate(long now) {

		if(!CollisionDetector.colidePolygon(rectangle1, rectangle2)) {
			color = Color.BLUE;
		} else {			
			color = Color.YELLOW;
		}
		
		rectangle2.setOffsetAngle(10);
	}

	@Override
	public void draw(Graphic g) {
		
		if(rectangle2.getTransform()!=null)
			g.setTransform(rectangle2.getTransform());

		g.setColor(Color.RED);
		g.fillRect(rectangle2);

		g.resetTransform();
		
		if(rectangle1.getTransform()!=null)
			g.setTransform(rectangle1.getTransform());

		g.setColor(color);
		g.fillRect(rectangle1);

		g.resetTransform();				
	}
	
	public GUIEvent updateMouse(PointerEvent event) {

		mx = event.getX();
		my = event.getY();
		
		if(event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {
			rectangle1.setCoordinates(mx, my);
		}
		
		return GUIEvent.NONE;
	}

}
