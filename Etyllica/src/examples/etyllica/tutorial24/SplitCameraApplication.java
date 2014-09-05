package examples.etyllica.tutorial24;

import java.awt.Color;

import br.com.etyllica.cinematics.Camera;
import br.com.etyllica.cinematics.ViewPort;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphic;

public class SplitCameraApplication extends Application {
	
	private Camera camera1;
	
	private Camera camera2;
		
	public SplitCameraApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		camera1 = new Camera(x, y, w/2, h);
		camera2 = new Camera(x+w/2, y, w/2, h);
		camera2.setZoom(1.5f);
		
		updateAtFixedRate(10);
	}
	
	private boolean pressed = false;
	private int aimY = 0;
	
	public void timeUpdate(long now) {
		
		if(pressed) {
			aimY--;
			camera1.setAimY(aimY);
			camera2.setAimY(aimY);			
		}
	}
		
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		if(event.isKeyDown(KeyEvent.TSK_UP_ARROW)) {
			pressed = true;
		}
		
		if(event.isKeyUp(KeyEvent.TSK_UP_ARROW)) {
			pressed = false;
		}
		
		return GUIEvent.NONE;
	}

	@Override
	public void draw(Graphic g) {
		
		drawCamera(g, camera1);
				
		drawCamera(g, camera2);		
	}
	
	private void drawCamera(Graphic g, ViewPort camera) {
		camera.resetImage();
		g.setImage(camera.getBuffer());
		g.setTransform(camera.getTransform());
		
		g.setColor(Color.PINK);
		g.drawRect(20, 20, 100, 20);
		g.resetImage();
		
		g.drawImage(camera.getBuffer(), camera.getX(), camera.getY());
	}

}
