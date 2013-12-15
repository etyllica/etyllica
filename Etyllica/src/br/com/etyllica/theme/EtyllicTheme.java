package br.com.etyllica.theme;

import br.com.etyllica.core.theme.Theme;
import br.com.etyllica.gui.button.DefaultButton;

public class EtyllicTheme extends Theme{
	
	public EtyllicTheme(){
		super();
	}
	
	public DefaultButton createButton(float x, float y, float w, float h){
		DefaultButton button = new DefaultButton(x, y, w, h);
		button.setTheme(this);
		return button;
	}
	
}
