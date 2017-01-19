package br.com.etyllica.gui;

import br.com.etyllica.gui.base.BaseCheckBox;
import br.com.etyllica.gui.base.UIView;
import br.com.etyllica.gui.theme.ThemeManager;

public class CheckBox extends UIView {
	
	private BaseCheckBox checkbox;
	
	public CheckBox(int x, int y, int w, int h) {
		super();
		
		this.checkbox = ThemeManager.getInstance().getTheme().createCheckBox(x, y, w, h);
		delegateView(checkbox);
	}

	public String getAlt() {
		return checkbox.getAlt();
	}

	public Label getLabel() {
		return checkbox.getLabel();
	}
	
	public void setAlt(String alt) {
		checkbox.setAlt(alt);
	}

	public void setLabel(Label label) {
		checkbox.setLabel(label);
	}

	public void setCenterLabel(Label label) {
		checkbox.setCenterLabel(label);
	}

	public void setChecked(boolean checked) {
		checkbox.setChecked(checked);
	}
	
	public void setChecker(Label checker) {
		checkbox.setChecker(checker);
	}

}
