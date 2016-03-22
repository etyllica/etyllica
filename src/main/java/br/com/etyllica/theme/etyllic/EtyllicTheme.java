package br.com.etyllica.theme.etyllic;

import br.com.etyllica.gui.base.BaseButton;
import br.com.etyllica.gui.base.BaseCheckBox;
import br.com.etyllica.theme.Theme;
import br.com.etyllica.theme.etyllic.components.CheckBox;

public class EtyllicTheme extends Theme {
	
	public EtyllicTheme() {
		super();
	}
	
	public BaseButton createButton(int x, int y, int w, int h) {
		BaseButton button = new BaseButton(x, y, w, h);
		return button;
	}
	
	public BaseCheckBox createCheckBox(int x, int y, int w, int h) {
		BaseCheckBox button = new CheckBox(x, y, w, h);
		return button;
	}
}
