package examples.etyllica.tutorial19;

import br.com.etyllica.awt.SVGColor;
import br.com.etyllica.core.collision.CollisionDetector;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.context.UpdateIntervalListener;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.BufferedLayer;

public class HexagonalApplication extends Application implements UpdateIntervalListener {
	
	private BufferedLayer hexagon;

	private int mx = 0;
	private int my = 0;
		
	public HexagonalApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		hexagon = new BufferedLayer("tiles/hexagon.png");
		hexagon.setCoordinates(w/2-hexagon.getW()/2, h/2-hexagon.getH()/2);
		
		updateAtFixedRate(10, this);
	}
	
	@Override
	public void timeUpdate(long now) {
		
		if(CollisionDetector.colideHexagonPoint(hexagon, mx, my)) {
			hexagon.offsetNegativeBlue(0x130);
		} else {
			hexagon.resetImage();
		}				
	}
	
	public GUIEvent updateMouse(PointerEvent event) {
		mx = event.getX();
		my = event.getY();
		
		return GUIEvent.NONE;
	}

	@Override
	public void draw(Graphic g) {

		g.setColor(SVGColor.SKY_BLUE);
		g.fillRect(0, 0, w, h);
		
		hexagon.draw(g);
	}

}
