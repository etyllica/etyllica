package br.com.etyllica.theme;

import br.com.etyllica.gui.base.BaseButton;
import br.com.etyllica.gui.base.BaseCheckBox;
import br.com.etyllica.gui.base.BaseRadioButton;
import br.com.etyllica.gui.base.BaseTextField;

public interface ThemeFactory {
	
	public BaseButton createButton(int x, int y, int w, int h);
	
	public BaseCheckBox createCheckBox(int x, int y, int w, int h);
	
	public BaseRadioButton createRadioButton(int x, int y, int w, int h);
	
	public BaseTextField createTextField(int x, int y, int w, int h);

}
