package examples.sound.application;

import java.awt.Color;

import sound.model.Sound;
import br.com.etyllica.awt.SVGColor;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.PointerState;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.Layer;

public class PlaySoundApplication extends Application {

	private Layer layer;
	private Sound sound;

	public PlaySoundApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		layer = new Layer(30, 100, 80, 70);
		sound = new Sound("magic1.wav");

		loading = 100;
	}

	@Override
	public void updateMouse(PointerEvent event) {
		if(event.getState() == PointerState.CLICK) {
			if(layer.colideRectPoint(event.getX(), event.getY())) {
				sound.play();
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(SVGColor.KHAKI);
		g.fillRect(layer);
		
		g.setColor(Color.BLACK);
		g.drawRect(layer);		
	}

}
