package examples.etyllica.tutorial22;

import br.com.etyllica.awt.SVGColor;
import br.com.etyllica.cinematics.Camera;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.context.UpdateIntervalListener;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.GeometricLayer;

public class SplitCameraApplication extends Application implements UpdateIntervalListener {
	
	private Camera camera1;
	
	private Camera camera2;
	
	private GeometricLayer square;
	
	private GeometricLayer rectangle;

	private boolean pressed = false;
	private int aimY = 0;
	
		
	public SplitCameraApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		camera1 = new Camera(x, y, w/2, h);
		camera2 = new Camera(x+w/2, y, w/2, h);
		camera2.setZoom(1.5f);
		
		square = new GeometricLayer(20, 20, 40, 40);
		
		rectangle = new GeometricLayer(20, 220, 100, 40);
		
		updateAtFixedRate(10, this);
	}
	
	public void timeUpdate(long now) {
		
		if(pressed) {
			aimY--;
			camera1.setAimY(aimY);
			camera2.setAimY(aimY);
		}
	}
		
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		if(event.isKeyDown(KeyEvent.VK_UP_ARROW)) {
			pressed = true;
		}
		
		if(event.isKeyUp(KeyEvent.VK_UP_ARROW)) {
			pressed = false;
		}
		
		return GUIEvent.NONE;
	}

	@Override
	public void draw(Graphic g) {
		
		g.setCamera(camera1);		
		drawScene(g);
		g.resetCamera(camera1);

		g.setCamera(camera2);
		drawScene(g);
		g.resetCamera(camera2);
	}
	
	private void drawScene(Graphic g) {
		g.setColor(SVGColor.PURPLE);
		g.drawRect(square);
		
		g.setColor(SVGColor.ORANGE);
		g.drawRect(rectangle);
	}

}
