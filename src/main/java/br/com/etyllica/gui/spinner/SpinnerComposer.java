package br.com.etyllica.gui.spinner;

import br.com.etyllica.gui.Label;
import br.com.etyllica.gui.factory.DefaultButton;
import br.com.etyllica.gui.label.TextLabel;
import br.com.etyllica.layer.GeometricLayer;

public abstract class SpinnerComposer extends GeometricLayer {

	protected int buttonWidth = 100;
	
	protected int border = 1;
	
	public SpinnerComposer(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public abstract DefaultButton buildPlusButton(int x, int y, int w, int h);
	
	public abstract DefaultButton buildMinusButton(int x, int y, int w, int h);
	
	protected Label buildPlusLabel() {
		return new TextLabel("+");
	}
	
	protected Label buildMinusLabel() {
		return new TextLabel("-");
	}

	public int getButtonWidth() {
		return buttonWidth;
	}

	public void setButtonWidth(int buttonWidth) {
		this.buttonWidth = buttonWidth;
	}

	public int getBorder() {
		return border;
	}

	public void setBorder(int border) {
		this.border = border;
	}
	
}
