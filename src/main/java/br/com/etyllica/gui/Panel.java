package br.com.etyllica.gui;

import br.com.etyllica.gui.base.BasePanel;
import br.com.etyllica.gui.base.UIViewGroup;
import br.com.etyllica.theme.ThemeManager;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Panel extends UIViewGroup {
	
	private BasePanel panel;
	
	public Panel(int x, int y, int w, int h) {
		super();
		
		this.panel = ThemeManager.getInstance().getTheme().createPanel(x, y, w, h);
		delegateView(panel);
	}

	public Panel() {
		this(0,0,0,0);
	}
	
}
