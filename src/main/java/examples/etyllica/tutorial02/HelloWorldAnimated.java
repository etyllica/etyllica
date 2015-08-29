package examples.etyllica.tutorial02;

import br.com.etyllica.core.animation.Animation;
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
				
		Animation.animate(hello).scale().during(10000).from(0.1).to(1)
		.interpolate(Interpolator.REVERSE_QUADRATIC)
		.and().rotate().during(5000).from(0).to(360).twice().start();
	}

	@Override
	public void draw(Graphic g) {
		hello.draw(g);		
	}

}
