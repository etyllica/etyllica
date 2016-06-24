package br.com.etyllica.theme.etyllic.components;

import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.base.BaseCheckBox;
import br.com.etyllica.theme.Theme;
import br.com.etyllica.theme.ThemeManager;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class EtyllicCheckBox extends BaseCheckBox {

	public EtyllicCheckBox(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	@Override
	public void draw(Graphics g) {

		Theme theme = ThemeManager.getInstance().getTheme();

		if (!mouseOver) {
			g.setColor(theme.getButtonColor());
		} else {
			if (clicked || checked) {
				g.setColor(theme.getButtonOnClick());
			} else {
				g.setColor(theme.getButtonOnMouse());
			}
		}
		
		g.fillRect(x,y,w,h);
		
		if (checked) {
			checker.draw(g);
		}
	}

}
