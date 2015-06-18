package examples.sound.application;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.dyn4j.PhysicsApplication;
import org.dyn4j.RigidBody;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Mass;

import sound.model.Sound;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.PointerState;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.graphics.SVGColor;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.Layer;

public class PlaySoundApplication extends PhysicsApplication {

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
	public GUIEvent updateMouse(PointerEvent event) {

		if(event.getState() == PointerState.CLICK) {
			if(layer.colideRectPoint(event.getX(), event.getY())) {
				sound.play();
			}
		}


		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void draw(Graphic g) {
		g.setColor(SVGColor.KHAKI);
		g.fillRect(layer);
		
		g.setColor(Color.BLACK);
		g.drawRect(layer);		
	}

}
