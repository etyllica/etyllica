package br.com.etyllica.theme.mono;

import java.awt.Color;

import br.com.etyllica.core.theme.Theme;
import br.com.etyllica.gui.button.DefaultButton;

public class EtyllicMonoTheme extends Theme{
	
	public EtyllicMonoTheme(){
		super();
		
		this.borderColor = Color.BLACK;
		
		this.buttonColor = Color.WHITE;
				
	}
	
	public DefaultButton createButton(float x, float y, float w, float h){
		DefaultButton button = new BorderButton(x, y, w, h);
		button.setTheme(this);
		return button;
	}
	
}
