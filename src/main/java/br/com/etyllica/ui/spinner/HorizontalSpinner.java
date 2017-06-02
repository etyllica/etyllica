package br.com.etyllica.ui.spinner;

import br.com.etyllica.ui.Spinner;
import br.com.etyllica.ui.spinner.composer.HorizontalComposer;
import br.com.etyllica.ui.spinner.composer.SpinnerComposer;

public abstract class HorizontalSpinner<T extends Number> extends Spinner<T> {
	
	public HorizontalSpinner(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	@Override
	protected SpinnerComposer buildComposer() {
		return new HorizontalComposer(x, y, w, h);
	}
	
}
