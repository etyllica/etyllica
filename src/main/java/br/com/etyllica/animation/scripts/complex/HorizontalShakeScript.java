package br.com.etyllica.animation.scripts.complex;

import br.com.etyllica.layer.Layer;

public class HorizontalShakeScript extends ShakeScript {
	
	private int initialX = 0;
		
	public HorizontalShakeScript(Layer target, long time) {
		super(target, time);
		
		initialX = target.getX();
	}
	
	@Override
	public void calculate(double factor) {
		
		double division = 1/(double)repeatTimes;
				
		int interval = (int)(factor/division);
				
		double part = interval+1 - (factor/division);
		
		double slice = part;
		
		if(interval%2 == 0) {
			
			slice = 1-slice;
		}
		
		double startValue = initialX-strength;
		
		double endValue = initialX+strength;
		
		double value = startValue+(endValue-startValue)*slice;		
		
		target.setX((int)value);		
	}
	
}
