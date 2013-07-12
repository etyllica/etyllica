package br.com.etyllica.gui.button;

import java.awt.Polygon;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.theme.Theme;
import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class PolygonalButton extends DefaultButton{

	protected Polygon polygon = new Polygon();

	public PolygonalButton(int x, int y) {
		super(x,y,0,0);
		polygon.addPoint(x, y);
	}

	@Override
	public void draw(Grafico g){

		Theme theme = getTheme();

		if(!mouseOver){

			g.setColor(theme.getButtonColor());

		}else{
			if(lastEvent == GUIEvent.MOUSE_LEFT_BUTTON_DOWN){

				g.setColor(theme.getButtonOnClick());

			}else{

				g.setColor(theme.getButtonOnMouse());

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
