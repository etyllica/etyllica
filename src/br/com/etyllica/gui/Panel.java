package br.com.etyllica.gui;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.theme.Theme;
import br.com.etyllica.core.video.Graphic;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Panel extends RoundGUIComponent{
	
	public Panel(int x, int y, int w, int h){
		super(x,y,w,h);
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		
		//Usado principalmente quando houver uma janela sobre as outras
		/*Mouse mouse = controle.getMouse();
		if(onMouse(mouse)){
			return GUIEvent.BLOCK_CLICK;
		}
		*/
		
		return GUIEvent.NONE;
	}

	@Override
	public void update(GUIEvent event) {
		
	}
	
	@Override
	public void draw(Graphic g) {
		
		Theme theme = Configuration.getInstance().getTheme();
		
		g.setColor(theme.getPanelColor());
		
		g.fillArc(x, y, roundness*2, roundness*2, 90, 90);
		g.fillRect(x+roundness, y, w-roundness*2, roundness);
		g.fillArc(x+w-roundness*2, y, roundness*2, roundness*2, 0, 90);
		
		g.fillRect(x, y+roundness, w, h-roundness*2);
		
		g.fillArc(x, y+h-roundness*2, roundness*2, roundness*2, 180, 90);
		g.fillRect(x+roundness, y+h-roundness, w-roundness*2, roundness);
		g.fillArc(x+w-roundness*2, y+h-roundness*2, roundness*2, roundness*2, 270, 90);
	
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}

}
