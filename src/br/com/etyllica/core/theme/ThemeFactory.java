package br.com.etyllica.core.theme;

import br.com.etyllica.gui.button.DefaultButton;

public interface ThemeFactory {
	
	public DefaultButton createButton(int x, int y, int w, int h);

}
