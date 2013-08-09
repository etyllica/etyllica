package br.com.etyllica.animation.scripts;

import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.layer.Layer;


public class OrbitAnimationScript extends AnimationScript{

	private int centerX = 0, centerY = 0;
	private int tx, ty, tw, th;
	
	public OrbitAnimationScript(long time){
		super(time);
	}

	public OrbitAnimationScript(long delay, long time){
		super(delay, time);
	}

	public OrbitAnimationScript(Layer target, long time){
		super(target, time);
	}
		
	public void setCenter(int centerX, int centerY){
		this.centerX = centerX;
		this.centerY = centerY;
	}
				
	@Override
	public void setTarget(Layer target){
		super.setTarget(target);
		
		tx = target.getX();
		ty = target.getY();
		tw = target.getW()/2;
		th = target.getH()/2;
				
	}
	
	@Override
	public void update(double value){

		double angle = value*Math.PI/180;

		double s = Math.sin(angle);
		double c = Math.cos(angle);

		// translate point back to origin:
		int px = tx+tw-centerX;
		int py = ty+th-centerY;

		// rotate point
		double xnew = px * c - py * s;
		double ynew = px * s + py * c;

		// translate point back:
		target.setX((int)xnew + centerX - tw);
		target.setY((int)ynew + centerY - th);

	}	

}
