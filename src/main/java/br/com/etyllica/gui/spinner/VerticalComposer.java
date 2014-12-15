package br.com.etyllica.gui.spinner;

import br.com.etyllica.gui.Label;
import br.com.etyllica.gui.factory.DefaultButton;
import br.com.etyllica.gui.label.TextLabel;

public class VerticalComposer extends SpinnerComposer {
	
	public VerticalComposer(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public DefaultButton buildPlusButton(int x, int y, int w, int h) {
		DefaultButton plus = new DefaultButton(x+w-buttonWidth-border, y+border, buttonWidth, h/2-border-1);
		plus.setLabel(buildPlusLabel());
				
		return plus;
	}
	
	@Override
	protected Label buildPlusLabel() {
		return new TextLabel("+");
	}

	@Override
	public DefaultButton buildMinusButton(int x, int y, int w, int h) {
		DefaultButton minus = new DefaultButton(x+w-buttonWidth-border, y+h/2+border, buttonWidth, h/2-border);
		minus.setLabel(buildMinusLabel());
				
		return minus;
	}
	
	@Override
	protected Label buildMinusLabel() {
		return new TextLabel("-");
	}

}
