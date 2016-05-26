package br.com.etyllica.gui.base;

import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.theme.Theme;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class BaseCheckBox extends BaseButton {

	protected boolean checked = false;

	public BaseCheckBox(int x, int y) {
		super(x, y, 22, 22);
	}
	
	public BaseCheckBox(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	@Override
	protected void leftClick() {
		swapChecked();
	}

	private void swapChecked() {
		
		checked = !checked;		
	}
	
	@Override
	public void draw(Graphics g) {

		Theme theme = getTheme();

		super.draw(g);

		if(checked) {

			//DrawShadow
			if(theme.isShadow()) {

				g.setColor(theme.getShadowColor());
				
				g.drawLine(x+3,y+3,x+w/2+1, y+h/2+1);
				g.drawLine(x+w/2+1, y+h/2+1,x+w+5+1,y-5+1);
			}

			g.setColor(theme.getTextColor());

			g.drawLine(x+2,y+2,x+w/2, y+h/2);
			g.drawLine(x+w/2, y+h/2,x+w+5,y-5);

		}

	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}


}
