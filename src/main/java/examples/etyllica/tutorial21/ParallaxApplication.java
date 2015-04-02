package examples.etyllica.tutorial21;

import br.com.etyllica.cinematics.parallax.ImageParallax;
import br.com.etyllica.context.Application;
import br.com.etyllica.context.IntervalUpdate;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.graphics.SVGColor;

public class ParallaxApplication extends Application implements IntervalUpdate {
	
	private ImageParallax parallax;

	private int offset = 0;
	
	public ParallaxApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		parallax = new ImageParallax("clouds.png");
		parallax.setProximity(2);
		
		updateAtFixedRate(10, this);
	}
	
	@Override
	public void timeUpdate(long now) {
		offset++;
		parallax.setOffset(offset);
	}

	@Override
	public void draw(Graphic g) {

		g.setColor(SVGColor.SKY_BLUE);
		g.fillRect(0, 0, w, h);
		
		parallax.draw(g);
	}

}
