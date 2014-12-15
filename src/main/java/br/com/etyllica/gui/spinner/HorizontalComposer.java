package br.com.etyllica.gui.spinner;

import br.com.etyllica.gui.factory.DefaultButton;

public class HorizontalComposer extends SpinnerComposer {
	
	public HorizontalComposer(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public DefaultButton buildPlusButton(int x, int y, int w, int h) {
		DefaultButton plus = new DefaultButton(x+w-buttonWidth-border, y+border, buttonWidth, h-border*2);
		plus.setLabel(buildPlusLabel());
				
		return plus;
	}
	
	@Override
	public DefaultButton buildMinusButton(int x, int y, int w, int h) {
		DefaultButton minus = new DefaultButton(x+border, y+border, buttonWidth, h-border*2);
		minus.setLabel(buildMinusLabel());
				
		return minus;
	}	

}
