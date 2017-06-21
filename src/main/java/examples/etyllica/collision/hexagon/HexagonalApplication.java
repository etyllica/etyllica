package examples.etyllica.collision.hexagon;

import br.com.etyllica.awt.SVGColor;
import br.com.etyllica.commons.collision.CollisionDetector;
import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.context.UpdateIntervalListener;
import br.com.etyllica.commons.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
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
		
		if(CollisionDetector.colideHexagonPoint(hexagon.getX(), hexagon.getY(), hexagon.utilWidth(), hexagon.utilHeight(), mx, my)) {
			hexagon.offsetNegativeBlue(0x130);
		} else {
			hexagon.resetImage();
		}				
	}
	
	public void updateMouse(PointerEvent event) {
		mx = event.getX();
		my = event.getY();
	}

	@Override
	public void draw(Graphics g) {

		g.setColor(SVGColor.SKY_BLUE);
		g.fillRect(0, 0, w, h);
		
		hexagon.draw(g);
	}

}