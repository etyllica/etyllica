package br.com.etyllica.theme;

import br.com.etyllica.gui.base.BaseButton;
import br.com.etyllica.gui.base.BaseCheckBox;

public interface ThemeFactory {
	
	public BaseButton createButton(int x, int y, int w, int h);
	
	public BaseCheckBox createCheckBox(int x, int y, int w, int h);

}
