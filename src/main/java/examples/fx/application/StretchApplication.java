package examples.fx.application;

import java.awt.Color;

import br.com.etyllica.animation.AnimationHandler;
import br.com.etyllica.animation.scripts.complex.StretchAndBackVertical;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class StretchApplication extends Application {

	private StretchAndBackVertical animation;
	
	private int surfaceY = 327;
	private ImageLayer layer;
	
	public StretchApplication(int w, int h) {
		super(w,h);
	}
	
	@Override
	public void load() {
		layer = new ImageLayer(300,surfaceY-27,"blue_ninja.png");
		
		animation = new StretchAndBackVertical(layer, 1200);
		animation.setInterval(1, 1.15);
		
		AnimationHandler.getInstance().add(animation);
	}

	@Override
	public void draw(Graphic g) {
		layer.draw(g);
		
		g.setColor(Color.BLACK);
		g.drawLine(300, surfaceY, 300+layer.getW(), surfaceY);
	}

}
