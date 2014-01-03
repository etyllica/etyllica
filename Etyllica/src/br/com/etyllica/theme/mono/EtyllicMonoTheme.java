package br.com.etyllica.theme.mono;

import java.awt.Color;

import br.com.etyllica.gui.button.DefaultButton;
import br.com.etyllica.gui.theme.Theme;

public class EtyllicMonoTheme extends Theme{
	
	public EtyllicMonoTheme(){
		super();
		
		this.borderColor = Color.BLACK;
		
		this.buttonColor = Color.WHITE;
		
		this.buttonOnMouse = Color.GRAY;
	}
	
	public DefaultButton createButton(int x, int y, int w, int h){
		DefaultButton button = new BorderButton(x, y, w, h);
		button.setTheme(this);
		return button;
	}
	
}
