package br.com.etyllica.theme.etyllic;

import br.com.etyllica.gui.base.BaseButton;
import br.com.etyllica.gui.base.BaseCheckBox;
import br.com.etyllica.gui.base.BaseTextField;
import br.com.etyllica.theme.Theme;
import br.com.etyllica.theme.etyllic.components.EtyllicCheckBox;

public class EtyllicTheme extends Theme {
	
	public EtyllicTheme() {
		super();
	}
	
	public BaseButton createButton(int x, int y, int w, int h) {
		BaseButton button = new BaseButton(x, y, w, h);
		return button;
	}
	
	public BaseCheckBox createCheckBox(int x, int y, int w, int h) {
		BaseCheckBox checkBox = new EtyllicCheckBox(x, y, w, h);
		return checkBox;
	}
	
	public BaseTextField createTextField(int x, int y, int w, int h) {
		BaseTextField textField = new BaseTextField(x, y, w, h);
		return textField;
	}
}
