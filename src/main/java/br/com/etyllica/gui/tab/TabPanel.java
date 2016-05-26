package br.com.etyllica.gui.tab;

import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.Panel;
import br.com.etyllica.theme.Theme;
import br.com.etyllica.theme.ThemeManager;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class TabPanel extends Panel{

	public TabPanel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	@Override
	public void draw(Graphics g) {
		
		Theme theme = ThemeManager.getInstance().getTheme();
		
		g.setColor(theme.getButtonOnMouse());
		
		g.fillArc(x, y, roundness*2, roundness*2, 90, 90);
		g.fillRect(x+roundness, y, w-roundness*2, roundness);
		g.fillArc(x+w-roundness*2, y, roundness*2, roundness*2, 0, 90);
		
		g.fillRect(x, y+roundness, w, h-roundness*2);
		
		g.fillArc(x, y+h-roundness*2, roundness*2, roundness*2, 180, 90);
		g.fillRect(x+roundness, y+h-roundness, w-roundness*2, roundness);
		g.fillArc(x+w-roundness*2, y+h-roundness*2, roundness*2, roundness*2, 270, 90);
		
	}
		
}
