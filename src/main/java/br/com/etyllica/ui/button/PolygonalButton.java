package br.com.etyllica.ui.button;

import java.awt.Polygon;

import br.com.etyllica.commons.event.GUIEvent;
import br.com.etyllica.commons.graphics.Graphics;
import br.com.etyllica.ui.base.BaseButton;
import br.com.etyllica.ui.theme.Theme;

/**
 * 
 * @author yuripourre
 *
 */

public class PolygonalButton extends BaseButton {

	protected Polygon polygon = new Polygon();

	public PolygonalButton(int x, int y) {
		super(x,y,0,0);
		polygon.addPoint(x, y);
	}

	@Override
	public void draw(Graphics g){

		Theme theme = getTheme();

		if(!mouseOver){

			g.setColor(theme.getBaseColor());

		} else {
			if (lastEvent == GUIEvent.MOUSE_LEFT_BUTTON_DOWN){
				g.setColor(theme.getActiveColor());
			} else {
				g.setColor(theme.getSelectionColor());
			}
		}

		g.fillPolygon(polygon);
		drawLabel(g);
		
	}

	public void addPoint(int x, int y){
		polygon.addPoint(x, y);
	}

	@Override
	public boolean onMouse(int mx, int my){

		return polygon.contains(mx, my);
	}

}
