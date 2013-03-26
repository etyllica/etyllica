package br.com.etyllica.theme;

import br.com.etyllica.gui.Button;
import br.com.etyllica.gui.MultipleSelect;
import br.com.etyllica.gui.PasswordField;
import br.com.etyllica.gui.Radio;
import br.com.etyllica.gui.Select;
import br.com.etyllica.gui.TextField;

public interface ThemeFactory{

	public Button createButton();
	
	public Radio createRadio();
	
	public Radio createCheckBox();
	
	public Select createSelect();
	
	public MultipleSelect createMultipleSelect();
	
	public TextField createTextField();
	
	public PasswordField createPasswordField();
	
}
