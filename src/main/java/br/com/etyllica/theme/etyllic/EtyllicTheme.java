package br.com.etyllica.theme.etyllic;

import br.com.etyllica.gui.base.BaseButton;
import br.com.etyllica.gui.base.BaseCheckBox;
import br.com.etyllica.gui.base.BaseRadioButton;
import br.com.etyllica.gui.base.BaseTable;
import br.com.etyllica.gui.base.BaseTextField;
import br.com.etyllica.gui.base.BaseTextView;
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
	
	public BaseRadioButton createRadioButton(int x, int y, int w, int h) {
		BaseRadioButton radioButton = new BaseRadioButton(x, y, w, h);
		return radioButton;
	}
	
	public BaseTextField createTextField(int x, int y, int w, int h) {
		BaseTextField textField = new BaseTextField(x, y, w, h);
		return textField;
	}

	@Override
	public BaseTextView createTextView(int x, int y, int w, int h) {
		BaseTextView textView = new BaseTextView(x, y, w, h);
		return textView;
	}

	@Override
	public BaseTable createTable(int x, int y, int w, int h) {
		BaseTable table = new BaseTable(x, y, w, h);
		return table;
	}
}
