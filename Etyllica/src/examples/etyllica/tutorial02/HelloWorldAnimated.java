package examples.etyllica.tutorial02;

import br.com.etyllica.animation.scripts.RotateAnimation;
import br.com.etyllica.animation.scripts.ScaleAnimation;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class HelloWorldAnimated extends Application{

	public HelloWorldAnimated(int w, int h) {
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
	
	private ImageLayer hello;

	@Override
	public void load() {
		
		loadingPhrase = "Loading images...";
		
		hello = new ImageLayer("hello.png");
		hello.centralizeX(0,w);
		hello.centralizeY(0,h);
				
		loadingPhrase = "Loading animations...";
		loading = 50;
		
		ScaleAnimation scaleAnimation = new ScaleAnimation(0,10000);
		scaleAnimation.setTarget(hello);
		scaleAnimation.setInterval(0.1, 1);
		scene.addAnimation(scaleAnimation);
		
		RotateAnimation rotateAnimation = new RotateAnimation(0,10000);
		rotateAnimation.setTarget(hello);
		rotateAnimation.setInterval(0, 360);
		scene.addAnimation(rotateAnimation);
		
	}

	@Override
	public void draw(Graphic g) {
		hello.draw(g);		
	}

}
