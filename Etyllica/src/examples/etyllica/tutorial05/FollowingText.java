package examples.etyllica.tutorial05;

import java.awt.Color;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.TextLayer;

public class FollowingText extends Application{

	public FollowingText(int w, int h) {
		super(w, h);
	}
	
	private TextLayer text = new TextLayer("Text!");

	@Override
	public void load() {
		
		text.setBorder(true);
		text.setBorderColor(Color.BLACK);
		text.setBorderWidth(3f);
		
		loading = 100;
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
				
		text.setCoordinates(event.getX(), event.getY());
		
		return GUIEvent.NONE;
	}
	
	@Override
	public void draw(Graphic g) {
				
		//Drawing background		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x,y,w,h);
		
		//Drawing text		
		text.draw(g);
	}

	
}
