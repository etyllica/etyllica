package br.com.etyllica.gui.theme;

import br.com.etyllica.gui.base.BaseButton;
import br.com.etyllica.gui.base.BaseCheckBox;
import br.com.etyllica.gui.base.BasePanel;
import br.com.etyllica.gui.base.BaseRadioButton;
import br.com.etyllica.gui.base.BaseTable;
import br.com.etyllica.gui.base.BaseTextField;
import br.com.etyllica.gui.base.BaseTextView;

public interface ThemeFactory {
		
	BaseButton createButton(int x, int y, int w, int h);

	BaseButton createSliderButton(int x, int y, int w, int h);

	BaseCheckBox createCheckBox(int x, int y, int w, int h);
	
	BaseRadioButton createRadioButton(int x, int y, int w, int h);

	BasePanel createPanel(int x, int y, int w, int h);
	
	BasePanel createLeftPanel(int x, int y, int w, int h);
	
	BasePanel createRightPanel(int x, int y, int w, int h);
	
	BaseTable createTable(int x, int y, int w, int h);
	
	BaseTextField createTextField(int x, int y, int w, int h);
	
	BaseTextView createTextView(int x, int y, int w, int h);

}
