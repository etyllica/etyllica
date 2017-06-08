package br.com.etyllica.ui.tab;

import br.com.etyllica.commons.graphics.Graphics;
import br.com.etyllica.ui.Panel;
import br.com.etyllica.ui.base.BasePanel;

/**
 * 
 * @author yuripourre
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
