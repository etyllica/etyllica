package examples.etyllica.fx.application;

import java.awt.Color;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.effects.Effect;

public class LightningApplication extends Application{

	public LightningApplication(int w, int h) {
		super(w, h);
	}

	private Effect lightning;

	@Override
	public void load() {
		
		loading = 10;
		
		lightning = new Effect(300, 110, 148, 403, "lightning.png");
		lightning.setFrames(10);
		lightning.setSpeed(100);
				
		loading = 100;

	}

	@Override
	public void draw(Graphic g) {
		
		g.setColor(Color.BLACK);
		g.writeX(100, "Press Space to see Lightnings");
		
		lightning.draw(g);
	}
	
	@Override
	public void update(long now) {
		
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return null;
	}
		
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		if(event.isKeyUp(KeyEvent.TSK_SPACE)) {
			lightning.startEffect();			
		}
				
		// TODO Auto-generated method stub
		return null;
	}
	

}
