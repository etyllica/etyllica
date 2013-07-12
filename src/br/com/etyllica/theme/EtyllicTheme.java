package br.com.etyllica.theme;

import br.com.etyllica.core.theme.Theme;
import br.com.etyllica.gui.button.DefaultButton;

public class EtyllicTheme extends Theme{
	
	public EtyllicTheme(){
		super();
	}
	
	public DefaultButton createButton(int x, int y, int w, int h){
		DefaultButton button = new DefaultButton(x, y, w, h);
		button.setTheme(this);
		return button;
	}
	
}
