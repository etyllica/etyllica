package examples.etyllica.tutorial02;

import br.com.etyllica.animation.scripts.RotateAnimation;
import br.com.etyllica.animation.scripts.ScaleUniformAnimation;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class HelloWorldAnimated extends Application {

	public HelloWorldAnimated(int w, int h) {
		super(w, h);
	}
	
	private ImageLayer hello;

	@Override
	public void load() {
		
		loadingInfo = "Loading images...";
		
		hello = new ImageLayer("hello.png");
		hello.centralizeX(0,w);
		hello.centralizeY(0,h);
				
		loadingInfo = "Loading animations...";
		loading = 50;
		
		ScaleUniformAnimation scaleAnimation = new ScaleUniformAnimation(0,10000);
		scaleAnimation.setTarget(hello);
		scaleAnimation.setInterval(0.1, 1);
		scene.addAnimation(scaleAnimation);
		
		RotateAnimation rotateAnimation = new RotateAnimation(0,5000);
		rotateAnimation.setTarget(hello);
		rotateAnimation.setInterval(0, 360);
		rotateAnimation.setRepeat(2);
		scene.addAnimation(rotateAnimation);
		
	}

	@Override
	public void draw(Graphic g) {
		hello.draw(g);		
	}

}
