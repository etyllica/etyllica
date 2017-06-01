package br.com.etyllica.gui;

import br.com.etyllica.gui.base.BaseButton;
import br.com.etyllica.gui.base.UIView;
import br.com.etyllica.gui.theme.ThemeManager;

public class SliderButton extends UIView {

	private BaseButton button;

	public SliderButton(int x, int y, int w, int h) {
		super(x,y,w,h);

		this.button = ThemeManager.getInstance().getTheme().createSliderButton(x, y, w, h);
		delegateView(button);
	}

	public SliderButton(Label label) {
		this(0,0,0,0);
		setLabel(label);
	}

	public void rebuild() {
		BaseButton view = ThemeManager.getInstance().getTheme().createSliderButton(x, y, w, h);
		view.copy(delegatedView);

		delegateView(view);
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
		this.button.setLabel(label);
	}

	public void setCenterLabel(Label label) {
		button.setCenterLabel(label);
	}

}
