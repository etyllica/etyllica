package br.com.etyllica.gui.button;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.base.BaseButton;
import br.com.etyllica.theme.Theme;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class RoundButton extends BaseButton{

	public RoundButton(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void draw(Graphics g){

		Theme theme = getTheme();

		if(!mouseOver){
			
			if(onFocus){
				
				g.setColor(theme.getButtonOnFocus());
				
			}else{
				
				g.setColor(theme.getBaseColor());
				
			}

		} else {		
			if (lastEvent == GUIEvent.MOUSE_LEFT_BUTTON_DOWN) {
				g.setColor(theme.getActiveSelectionColor());
			} else {
				g.setColor(theme.getSelectionColor());
			}
		}

		g.fillOval(x,y,w,h);
		drawLabel(g);
	}

}
