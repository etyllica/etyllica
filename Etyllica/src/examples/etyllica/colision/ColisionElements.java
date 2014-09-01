package examples.etyllica.colision;

import java.awt.Color;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.Layer;
import br.com.etyllica.linear.Point2D;

public class ColisionElements extends Application {

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

		rectangle1 = new Layer(20, 100, 200, 50);
		rectangle1.setAngle(20);

		rectangle2 = new Layer(200, 200, 200, 50);

		updateAtFixedRate(100);

		loading = 100;
	}

	public void timeUpdate(long now) {

		/*if(!ColisionDetector.colideRectRect(rectangle1, rectangle2)) {
			color = Color.BLUE;
		} else {			
			color = Color.YELLOW;
		}*/
		
		if(rectangle1.onMouse(mx, my)) {
			color = Color.YELLOW;
		} else {
			color = Color.BLUE;
		}

		rectangle2.setOffsetAngle(10);
	}

	@Override
	public void draw(Graphic g) {

		drawGhost(rectangle2, g);
		
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
	
	private void drawGhost(Layer layer, Graphic g) {
		
		int centerX = layer.getX()+layer.getW()/2;
		int centerY = layer.getY()+layer.getH()/2;
				
		Point2D a = new Point2D(layer.getX(), layer.getY());
		a.rotate(centerX, centerY, layer.getAngle());
		
		Point2D b = new Point2D(layer.getX()+layer.getW(), layer.getY());
		b.rotate(centerX, centerY, layer.getAngle());
		
		Point2D c = new Point2D(layer.getX(), layer.getY()+layer.getH());
		c.rotate(centerX, centerY, layer.getAngle());
		
		Point2D d = new Point2D(layer.getX()+layer.getW(), layer.getY()+layer.getH());
		d.rotate(centerX, centerY, layer.getAngle());
		
		g.setColor(Color.GREEN);
		g.drawLine(a, b);
		g.drawLine(a, c);
		g.drawLine(b, d);
		g.drawLine(c, d);
	}

	public GUIEvent updateMouse(PointerEvent event) {

		mx = event.getX();
		my = event.getY();		
		
		return GUIEvent.NONE;
	}

}
