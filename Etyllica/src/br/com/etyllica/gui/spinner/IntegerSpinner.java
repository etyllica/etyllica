package br.com.etyllica.gui.spinner;

import br.com.etyllica.gui.Spinner;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class IntegerSpinner extends Spinner<Integer> {

	public IntegerSpinner(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.value = 0;
		this.step = 1;
		
		this.minValue = Integer.MIN_VALUE;
		this.maxValue = Integer.MAX_VALUE;
	}

	@Override
	public void add() {
		if(value.intValue()<maxValue){
			this.value = value.intValue() + step.intValue();
		}
	}

	@Override
	public void subtract() {
		if(value.intValue()>minValue){
			this.value = value.intValue() - step.intValue();
		}
	}
	
	public Integer getValue() {
		return this.value.intValue();
	}

}


