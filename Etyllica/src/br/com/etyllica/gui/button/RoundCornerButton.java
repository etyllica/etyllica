package br.com.etyllica.gui.button;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.theme.Theme;

/**
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class RoundCornerButton extends DefaultButton{

	public RoundCornerButton(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.roundness = 10;
	}
	
	@Override
	public void draw(Graphic g) {
		
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
		
		g.fillRoundRect(x, y, w, h, roundness, roundness);
		
		drawLabel(g);
	}
		
}
