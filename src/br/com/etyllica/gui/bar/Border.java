package br.com.etyllica.gui.bar;

import java.awt.Polygon;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.GUIComponent;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Border extends GUIComponent{

	private int borderSize = 12;
	private Polygon polygon;
	
	public Border(int x, int y, int w, int h){
		
		polygon = new Polygon();
		
		polygon.addPoint(0, 0);
		polygon.addPoint(w, 0);
		polygon.addPoint(w, h);
		polygon.addPoint(0, h);
		
		polygon.addPoint(borderSize, h-borderSize);
		polygon.addPoint(w-borderSize, h-borderSize);
		polygon.addPoint(w-borderSize, borderSize);
		polygon.addPoint(borderSize, borderSize);
		
	}
	
	
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

	@Override
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub
	}

	public void draw(Grafico g){
		g.setColor(Configuration.getInstance().getTheme().getBarColor());
		g.fillPolygon(polygon);
	}

	public int getBorderSize() {
		return borderSize;
	}

	public void setBorderSize(int borderSize) {
		this.borderSize = borderSize;
	}
		
}
