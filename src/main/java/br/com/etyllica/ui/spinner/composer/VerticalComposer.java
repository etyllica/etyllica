package br.com.etyllica.ui.spinner.composer;

import br.com.etyllica.ui.Button;
import br.com.etyllica.ui.Label;
import br.com.etyllica.ui.label.TextLabel;

public class VerticalComposer extends SpinnerComposer {
	
	public VerticalComposer(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public Button buildPlusButton(int x, int y, int w, int h) {
		Button plus = new Button(x, y, w, h);
		plus.setLabel(buildPlusLabel());
		
		return plus;
	}
	
	@Override
	protected Label buildPlusLabel() {
		return new TextLabel("+");
	}

	@Override
	public Button buildMinusButton(int x, int y, int w, int h) {
		Button minus = new Button(x, y, w, h);
		minus.setLabel(buildMinusLabel());
				
		return minus;
	}
	
	@Override
	protected Label buildMinusLabel() {
		return new TextLabel("-");
	}

}
