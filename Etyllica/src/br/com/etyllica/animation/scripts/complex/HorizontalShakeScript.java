package br.com.etyllica.animation.scripts.complex;

import br.com.etyllica.animation.scripts.RepeatedScript;
import br.com.etyllica.layer.Layer;

public class HorizontalShakeScript extends RepeatedScript {
	
	private int initialX = 0;
	
	private int strength = 10;
	
	public HorizontalShakeScript(Layer target, long time) {
		super(target, time);
		
		initialX = target.getX();
	}
	
	@Override
	protected void calculate(double factor) {
		
		double division = 1/(double)repeatTimes;
				
		int interval = (int)(factor/division);
		
		double part = interval+1 - (factor/division);
		
		double offset = (part)*strength;
		
		if(interval%2 == 1) {
		
			offset = -offset;
		}
		
		target.setX(initialX+(int)offset);
		
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}
	
}
