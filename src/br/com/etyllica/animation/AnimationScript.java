package br.com.etyllica.animation;

import br.com.etyllica.layer.Layer;

public class AnimationScript implements Script{

	protected Layer target;

	protected long startedAt = 0;

	protected long time = 0;

	private boolean started = false;
	private boolean stopped = false;
	private boolean endless = false;
	
	protected int startValue = 0;
	
	protected int endValue = 0;
	
	private AnimationScript next;
	

	public AnimationScript(long time){
		super();

		this.time = time;
	}
	
	public AnimationScript(Layer target, long time){
		super();

		this.target = target;
		this.time = time;
	}
	
	public void restart(){
		started = false;
		stopped = false;
	}

	public void start(long now){
		this.startedAt = now;
	}

	public void preAnimate(long now){

		if(!started){
			started = true;
			stopped = false;
			startedAt = now;
		}

		if(!stopped){

			if(now-startedAt>=time){
				stopped = true;
			}else{
				this.animate(now);
			}
			
		}

	}

	public void animate(long now){
		
	}

	public boolean isStopped() {
		return stopped;
	}
	
	public boolean isEndless() {
		return endless;
	}

	public void setEndless(boolean endless) {
		this.endless = endless;
	}

	public int getEndValue() {
		return endValue;
	}

	public void setEndValue(int endValue) {
		this.endValue = endValue;
	}

	public Layer getTarget() {
		return target;
	}

	public void setTarget(Layer target) {
		this.target = target;
	}
	
	public void setInterval(int startValue, int endValue){
		this.startValue = startValue;
		this.endValue = endValue;
	}

	public AnimationScript getNext() {
		return next;
	}

	public void setNext(AnimationScript next) {
		this.next = next;
	}
		
}