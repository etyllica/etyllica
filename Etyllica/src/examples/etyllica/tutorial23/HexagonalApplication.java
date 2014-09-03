package examples.etyllica.tutorial23;

import br.com.etyllica.collision.ColisionDetector;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.graphics.SVGColor;
import br.com.etyllica.layer.BufferedLayer;

public class HexagonalApplication extends Application {
	
	private BufferedLayer hexagon;

	private int mx = 0;
	private int my = 0;
		
	public HexagonalApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		hexagon = new BufferedLayer(150, 10, "tiles/hexagon.png");
		
		updateAtFixedRate(10);
	}
	
	@Override
	public void timeUpdate(long now) {
		
		if(ColisionDetector.colideHexagonPoint(hexagon, mx, my)) {
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
