package br.com.etyllica.theme;

import br.com.etyllica.gui.factory.DefaultButton;

public interface ThemeFactory {
	
	public DefaultButton createButton(int x, int y, int w, int h);

}
