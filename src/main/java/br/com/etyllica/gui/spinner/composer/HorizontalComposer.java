package br.com.etyllica.gui.spinner.composer;

import br.com.etyllica.gui.base.BaseButton;

public class HorizontalComposer extends SpinnerComposer {
	
	public HorizontalComposer(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public BaseButton buildPlusButton(int x, int y, int w, int h) {
		BaseButton plus = new BaseButton(x+w-buttonWidth-border, y+border, buttonWidth, h-border*2);
		plus.setLabel(buildPlusLabel());
				
		return plus;
	}
	
	@Override
	public BaseButton buildMinusButton(int x, int y, int w, int h) {
		BaseButton minus = new BaseButton(x+border, y+border, buttonWidth, h-border*2);
		minus.setLabel(buildMinusLabel());
				
		return minus;
	}	

}
