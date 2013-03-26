package br.com.etyllica.gui.tab;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.Theme;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.button.IconedButton;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ImageTab extends IconedButton{

	private boolean active = false;
	
	public ImageTab(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void draw(Grafico g){

		Theme theme = Configuration.getInstance().getTheme();
		
		if(!mouseOver){

			if(!active){
				
				g.setColor(theme.getButtonColor());
				
			}else{
				
				g.setColor(theme.getButtonOnMouse());
				
			}

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
		
		g.fillRect(x, y+roundness, w, h-roundness);
				
		//Draw Icon
		icon.centraliza(x, y, w, h);
		icon.draw(g);
		
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
