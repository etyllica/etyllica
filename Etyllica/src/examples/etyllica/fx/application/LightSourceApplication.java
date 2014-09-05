package examples.etyllica.fx.application;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.effects.light.LightSource;
import br.com.etyllica.layer.ImageLayer;

public class LightSourceApplication extends Application{

	private BufferedImage shadowLayer;
	
	private LightSource source;
	private LightSource anotherSource;
	
	private Graphics2D graphics;
	
	private ImageLayer background;

	public LightSourceApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		loading = 10;
		
		background = new ImageLayer("scene.png");
		
		shadowLayer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		graphics = (Graphics2D)shadowLayer.getGraphics();
		graphics.setColor(Color.BLUE);
		
		source = new LightSource(w/2-100, h/2-100, 200);
		anotherSource = new LightSource(w/2-100, h/2-100, 200);
						
		loading = 100;
	}

	@Override
	public void draw(Graphic g) {
		
		background.draw(g);
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, w, h);
		
		g.setImage(shadowLayer);
		
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 1.0f));
		
		source.drawLight(g);
		anotherSource.drawLight(g);
		
		g.resetImage();
		
		g.drawImage(shadowLayer, 0, 0);
	}
	
	public GUIEvent updateMouse(PointerEvent event) {
		
		source.setCoordinates(event.getX()-source.getW()/2, event.getY()-source.getH()/2);
		
		return null;
	}

}
