package br.com.etyllica.gui.spinner.composer;

import br.com.etyllica.gui.Label;
import br.com.etyllica.gui.label.TextLabel;

public class StringHorizontalComposer extends HorizontalComposer {
	
	public StringHorizontalComposer(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	protected Label buildPlusLabel() {
		return new TextLabel(">");
	}
	
	@Override
	protected Label buildMinusLabel() {
		return new TextLabel("<");
	}	

}
