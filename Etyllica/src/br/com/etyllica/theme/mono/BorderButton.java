package br.com.etyllica.theme.mono;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.button.DefaultButton;
import br.com.etyllica.gui.theme.Theme;

public class BorderButton extends DefaultButton{

	public BorderButton(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	@Override
	public void draw(Graphic g){
		
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

		g.fillRect(x,y,w,h);
		
		g.setColor(theme.getBorderColor());
		
		g.drawRect(x,y,w,h);
		
		drawLabel(g);

	}

}
