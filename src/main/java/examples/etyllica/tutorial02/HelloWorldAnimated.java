package examples.etyllica.tutorial02;

import br.com.etyllica.animation.Animation;
import br.com.etyllica.core.animation.LayerAnimation;
import br.com.etyllica.core.animation.script.RotateAnimation;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.interpolation.Interpolator;
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
				
		LayerAnimation scaleAnimation = Animation.animate(hello).scale(10000).from(0.1).to(1).interpolator(Interpolator.LINEAR_INTERPOLATOR);
		scaleAnimation.start();
		
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
