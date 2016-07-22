package br.com.etyllica.gui.tab;

import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.Panel;
import br.com.etyllica.gui.base.BasePanel;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class TabPanel extends Panel {

	public TabPanel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getTheme().getSelectionColor());
		BasePanel.roundPanel(g, x, y, w, h, style.roundness);
	}
		
}
