package examples.etyllica.tutorial05;

import java.awt.Color;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
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
		
		text.setColor(Color.WHITE);
		
		loading = 100;
	}
	
	@Override
	public void updateMouse(PointerEvent event) {
		text.setCoordinates(event.getX(), event.getY());
	}
	
	@Override
	public void draw(Graphics g) {
		//Drawing background
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x,y,w,h);
		
		//Drawing text		
		text.draw(g);
	}

	
}
