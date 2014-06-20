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
		
		double slice = 1-part;
		
		if(interval%2 == 1) {
			
			slice = 1-slice;
		}
		
		double startValue = initialX-strength;
		
		double endValue = initialX+strength;
		
		double value = startValue+(endValue-startValue)*slice;		
		
		target.setX((int)value);
		
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}
	
}
