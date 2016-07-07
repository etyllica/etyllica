package br.com.etyllica.gui;

import br.com.etyllica.gui.base.BaseButton;
import br.com.etyllica.gui.base.UIView;
import br.com.etyllica.theme.ThemeManager;

public class Button extends UIView {
	
	private BaseButton button;
	
	public Button(int x, int y, int w, int h) {
		super();
		
		this.button = ThemeManager.getInstance().getTheme().createButton(x, y, w, h);
		delegateView(button);
	}
	
	public String getAlt() {
		return button.getAlt();
	}

	public Label getLabel() {
		return button.getLabel();
	}

	public void setAlt(String alt) {
		button.setAlt(alt);
	}

	public void setLabel(Label label) {
		button.setLabel(label);
	}

	public void setCenterLabel(Label label) {
		button.setCenterLabel(label);
	}

}
