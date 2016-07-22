package br.com.etyllica.theme;

import br.com.etyllica.gui.base.BaseButton;
import br.com.etyllica.gui.base.BaseCheckBox;
import br.com.etyllica.gui.base.BasePanel;
import br.com.etyllica.gui.base.BaseRadioButton;
import br.com.etyllica.gui.base.BaseTable;
import br.com.etyllica.gui.base.BaseTextField;
import br.com.etyllica.gui.base.BaseTextView;

public interface ThemeFactory {
		
	public BaseButton createButton(int x, int y, int w, int h);
	
	public BaseCheckBox createCheckBox(int x, int y, int w, int h);
	
	public BaseRadioButton createRadioButton(int x, int y, int w, int h);

	public BasePanel createPanel(int x, int y, int w, int h);
	
	public BasePanel createLeftPanel(int x, int y, int w, int h);
	
	public BasePanel createRightPanel(int x, int y, int w, int h);
	
	public BaseTable createTable(int x, int y, int w, int h);
	
	public BaseTextField createTextField(int x, int y, int w, int h);
	
	public BaseTextView createTextView(int x, int y, int w, int h);

}
