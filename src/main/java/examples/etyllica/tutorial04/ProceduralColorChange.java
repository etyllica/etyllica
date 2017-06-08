package examples.etyllica.tutorial04;

import java.awt.Color;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.PointerEvent;
import br.com.etyllica.commons.graphics.Graphics;
import br.com.etyllica.layer.BufferedLayer;

public class ProceduralColorChange extends Application {

	private BufferedLayer cursor;
	
	public ProceduralColorChange(int w, int h) {
		super(w, h);
	}

	@Override
	public void load(){
		cursor = new BufferedLayer(50,50,"cursor.png");
		
		loading = 100;
	}
		
	@Override
	public void updateMouse(PointerEvent event) {
		
		int ex = event.getX();
		int ey = event.getY();
		cursor.setCoordinates(ex, ey);
			
		
		int red = (int)((ex*255)/640);
		int green = (int)((ey*255)/480);
		int blue = 0;
				
		cursor.offsetRGB(red, green, blue);		
	}
	
	@Override
	public void draw(Graphics g) {
		//Drawing background
		g.setColor(Color.WHITE);
		g.fillRect(x,y,w,h);
		
		cursor.draw(g);
	}
	
}
