package examples.etyllica.tutorial03;

import br.com.etyllica.animation.scripts.OrbitAnimation;
import br.com.etyllica.animation.scripts.RotateAnimation;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class StriderAnimation extends Application{

	public StriderAnimation(int w, int h) {
		super(w, h);
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private ImageLayer background;
	
	private ImageLayer machine;

	@Override
	public void load() {
		
		loadingPhrase = "Loading images...";
		
		background = new ImageLayer("scene.png");
		machine = new ImageLayer(180,270,"machine.png");
				
		loading = 50;
		loadingPhrase = "Loading animations...";		
		
		OrbitAnimation orbitAnimation = new OrbitAnimation(0,5000);
		orbitAnimation.setTarget(machine);
		orbitAnimation.setCenter(machine.getX(), machine.getY()+70);
		orbitAnimation.setInterval(0, 360);
		orbitAnimation.setEndless(true);
		this.animation.add(orbitAnimation);
		
		RotateAnimation rotateAnimation = new RotateAnimation(0,5000);
		rotateAnimation.setTarget(machine);
		rotateAnimation.setInterval(0, 360);
		rotateAnimation.setEndless(true);
		this.animation.add(rotateAnimation);
		
	}

	@Override
	public void draw(Graphic g) {
		background.draw(g);
		machine.draw(g);
	}

}
