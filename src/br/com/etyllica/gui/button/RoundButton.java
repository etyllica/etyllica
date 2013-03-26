package br.com.etyllica.gui.button;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.Button;
import br.com.etyllica.theme.Theme;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class RoundButton extends Button{

	public RoundButton(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void draw(Grafico g){

		Theme theme = Configuration.getInstance().getTheme();

		if(!mouseOver){
			
			if(onFocus){
				
				g.setColor(theme.getButtonOnFocus());
				
			}else{
				
				g.setColor(theme.getButtonColor());
				
			}

		}else{
						
			if(lastEvent == GUIEvent.MOUSE_LEFT_BUTTON_DOWN){

				g.setColor(theme.getButtonOnClick());

			}else{

				g.setColor(theme.getButtonOnMouse());

			}
						
		}

		g.fillOval(x,y,w,h);
		
		drawLabel(g);

	}

}
