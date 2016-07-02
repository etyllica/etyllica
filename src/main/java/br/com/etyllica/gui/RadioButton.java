package br.com.etyllica.gui;

import br.com.etyllica.gui.base.BaseRadioButton;
import br.com.etyllica.gui.base.UIView;
import br.com.etyllica.theme.ThemeManager;

public class RadioButton extends UIView {
	
	private BaseRadioButton radio;
	
	public RadioButton(int x, int y, int w, int h) {
		super();
		
		this.radio = ThemeManager.getInstance().getTheme().createRadioButton(x, y, w, h);
		this.delegatedView = radio;
	}

	public String getAlt() {
		return radio.getAlt();
	}

	public Label getLabel() {
		return radio.getLabel();
	}
	
	public RadioGroup getGroup() {
		return radio.getGroup();
	}
	
	public void setAlt(String alt) {
		radio.setAlt(alt);
	}

	public void setLabel(Label label) {
		radio.setLabel(label);
	}

	public void setCenterLabel(Label label) {
		radio.setCenterLabel(label);
	}

	public void setChecked(boolean checked) {
		radio.setChecked(checked);
	}
	
	public void setChecker(Label checker) {
		radio.setChecker(checker);
	}
	
	public void setGroup(RadioGroup group) {
		radio.setGroup(group);
	}

	public void check() {
		radio.check();
	}

}
