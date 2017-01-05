package br.com.etyllica.gui.spinner.composer;

import br.com.etyllica.gui.Button;
import br.com.etyllica.gui.Label;
import br.com.etyllica.gui.label.TextLabel;
import br.com.etyllica.layer.GeometricLayer;

public abstract class SpinnerComposer extends GeometricLayer {
	
	public SpinnerComposer(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public abstract Button buildPlusButton(int x, int y, int w, int h);
	
	public abstract Button buildMinusButton(int x, int y, int w, int h);
	
	protected Label buildPlusLabel() {
		return new TextLabel("+");
	}
	
	protected Label buildMinusLabel() {
		return new TextLabel("-");
	}

}
