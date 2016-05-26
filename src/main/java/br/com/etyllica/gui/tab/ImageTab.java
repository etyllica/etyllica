package br.com.etyllica.gui.tab;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.base.BaseButton;
import br.com.etyllica.theme.Theme;
import br.com.etyllica.theme.ThemeManager;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ImageTab extends BaseButton {

	private boolean active = false;
	
	public ImageTab(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void draw(Graphics g){

		Theme theme = ThemeManager.getInstance().getTheme();
		
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
		drawLabel(g);
		
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
