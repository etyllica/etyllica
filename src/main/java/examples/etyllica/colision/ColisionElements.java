package examples.etyllica.colision;

import java.awt.Color;

import br.com.etyllica.awt.SVGColor;
import br.com.etyllica.commons.collision.CollisionDetector;
import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.context.UpdateIntervalListener;
import br.com.etyllica.commons.event.MouseEvent;
import br.com.etyllica.commons.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.GeometricLayer;
import br.com.etyllica.layer.Layer;

public class ColisionElements extends Application implements UpdateIntervalListener {

	private Color color = Color.BLUE;

	private Layer rectangle1;
	private Layer rectangle2;
	
	private GeometricLayer greenRectangle;
	private GeometricLayer orangeRectangle;
	
	private boolean colideGreenOrange = false;
	
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
		
		greenRectangle = new Layer(480, 280, 200, 50);
		orangeRectangle = new Layer(520, 300, 200, 50);

		updateAtFixedRate(100, this);

		loading = 100;
	}

	public void timeUpdate(long now) {

		if(!rectangle1.colideRectRect(rectangle2)) {
			color = Color.BLUE;
		} else {			
			color = Color.YELLOW;
		}
		
		rectangle2.setOffsetAngle(10);
		
		if(orangeRectangle.colideRectRect(greenRectangle)) {
			colideGreenOrange = true;
		} else {
			colideGreenOrange = false;
		}
	}

	@Override
	public void draw(Graphics g) {
		
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
		
		//Draw fixed rectangles
		g.setColor(SVGColor.GREEN);
		g.fillRect(greenRectangle);
		
		g.setColor(SVGColor.ORANGE);
		g.fillRect(orangeRectangle);
		
		if(colideGreenOrange) {
			g.setColor(SVGColor.RED);
			g.drawRect(greenRectangle);
			g.drawRect(orangeRectangle);
		}
	}
	
	public void updateMouse(PointerEvent event) {

		mx = event.getX();
		my = event.getY();
		
		if(event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {
			rectangle1.setCoordinates(mx, my);
		}
		
		if(event.isButtonDown(MouseEvent.MOUSE_BUTTON_RIGHT)) {
			orangeRectangle.setCoordinates(mx, my);
		}
	}

}
