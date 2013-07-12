package br.com.etyllica.gui.button;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.theme.Theme;
import br.com.etyllica.core.video.Grafico;

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
	public void draw(Grafico g) {
		
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
		
		g.fillArc(x, y, roundness*2, roundness*2, 90, 90);
		g.fillRect(x+roundness, y, w-roundness*2, roundness);
		g.fillArc(x+w-roundness*2, y, roundness*2, roundness*2, 0, 90);
		
		g.fillRect(x, y+roundness, w, h-roundness*2);
		
		g.fillArc(x, y+h-roundness*2, roundness*2, roundness*2, 180, 90);
		g.fillRect(x+roundness, y+h-roundness, w-roundness*2, roundness);
		g.fillArc(x+w-roundness*2, y+h-roundness*2, roundness*2, roundness*2, 270, 90);
		
		drawLabel(g);
	}
		
}
