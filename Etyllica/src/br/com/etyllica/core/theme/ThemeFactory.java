package br.com.etyllica.core.theme;

import br.com.etyllica.gui.button.DefaultButton;

public interface ThemeFactory {
	
	public DefaultButton createButton(float x, float y, float w, float h);

}
