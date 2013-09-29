package examples.etyllica.tutorial04.application;

import java.awt.Color;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.BufferedLayer;

public class ProceduralColorChange extends Application{

	private BufferedLayer cursor;
	
	public ProceduralColorChange(int w, int h){
		super(w, h);
	}

	@Override
	public void load(){
		
		cursor = new BufferedLayer(50,50,"cursor.png");
		updateAtFixedRate(20);
		
		loading = 100;
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event){
		
		
		return GUIEvent.NONE;
		
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event){
		
		int ex = event.getX();
		int ey = event.getY();
		cursor.setCoordinates(ex, ey);
			
		
		int red = (ex*255)/640;
		int green = (ey*255)/480;
		int blue = 0;
				
		cursor.offsetRGB(red, green, blue);		
		
		
		return GUIEvent	.NONE;
		
	}
	
	@Override
	public void draw(Graphic g){
		
		//Drawing background
		
		g.setColor(Color.WHITE);
		g.fillRect(x,y,w,h);
		
		cursor.draw(g);
	}
	
}
