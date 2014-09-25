package examples.etyllica.camera.application;

import java.awt.Color;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class CameraExample extends Application {
	
	private ImageLayer layer;
	
	public CameraExample(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		layer = new ImageLayer("particle.png");
		layer.centralize(this);
		
		camera.setZoom(2);
		camera.setAngle(45);
	}

	@Override
	public void draw(Graphic g) {
		
		g.setCamera(camera);
		//Draw background
		g.setColor(Color.CYAN);
		g.fillRect(this);
		//Draw layer
		layer.draw(g);
		
		g.resetCamera(camera);
		camera.draw(g);
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		
		camera.setAimX((int)(event.getX()*camera.getZoomX()-w/2));
		camera.setAimY((int)(event.getY()*camera.getZoomY()-h/2));
		
		return GUIEvent.NONE;
	}
	
}
