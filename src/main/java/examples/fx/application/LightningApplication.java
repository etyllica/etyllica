package examples.fx.application;

import java.awt.Color;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.effect.Effect;
import br.com.etyllica.commons.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphics;

public class LightningApplication extends Application{

	private Effect lightning;
	
	public LightningApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		loading = 10;
		
		lightning = new Effect(300, 110, 120, 370, "lightning.png");
		lightning.setFrames(10);
		lightning.setSpeed(100);
				
		loading = 100;
	}

	@Override
	public void draw(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.drawStringX("Press Space to see the magic", 100);
		
		lightning.draw(g);
	}

	@Override
	public void updateKeyboard(KeyEvent event) {
		if(event.isKeyUp(KeyEvent.VK_SPACE)) {
			lightning.startEffect();
		}
	}
}
