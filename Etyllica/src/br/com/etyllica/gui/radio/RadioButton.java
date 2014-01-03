package br.com.etyllica.gui.radio;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.gui.Radio;
import br.com.etyllica.gui.theme.Theme;
import br.com.etyllica.theme.ThemeManager;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class RadioButton extends Radio{

	public RadioButton(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
			
	@Override
	public void draw(Graphic g){

		Theme theme = ThemeManager.getInstance().getTheme();

		if(!mouseOver){

			g.setColor(theme.getButtonColor());

		}else{

			if(lastEvent == GUIEvent.MOUSE_LEFT_BUTTON_DOWN){

				g.setColor(theme.getButtonOnClick());

			}else{

				g.setColor(theme.getButtonOnMouse());

			}
		}

		if(checked){
			g.setColor(theme.getButtonOnClick());
		}
		
		g.fillRect(x,y,w,h);
		drawLabel(g);
		
	}

}