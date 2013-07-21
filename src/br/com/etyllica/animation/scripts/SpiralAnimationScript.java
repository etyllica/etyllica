package br.com.etyllica.animation.scripts;

import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.layer.Layer;


public class SpiralAnimationScript extends AnimationScript{

	public SpiralAnimationScript(long time){
		super(time);
	}

	public SpiralAnimationScript(long delay, long time){
		super(delay, time);
	}

	public SpiralAnimationScript(Layer target, long time){
		super(target, time);
	}
	
	int cx = 0, cy = 0;
	
	public void setCenter(int centerX, int centerY){
		this.cx = centerX;
		this.cy = centerY;
	}
	
	int tx, ty, tw, th;
	
	public void setTarget(Layer target){
		this.target = target;
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
		int px = tx+tw;
		int py = ty+th;

		px -= cx;
		py -= cy;

		// rotate point
		double xnew = px * c - py * s;
		double ynew = px * s + py * c;

		// translate point back:
		target.setX((int)xnew + cx - tw);
		target.setY((int)ynew + cy - th);

	}	

}
