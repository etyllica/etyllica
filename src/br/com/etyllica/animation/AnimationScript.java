package br.com.etyllica.animation;

import br.com.etyllica.layer.Layer;

public abstract class AnimationScript{

	protected Layer target;

	protected long startedAt = 0;
	protected long time = 0;
	protected long delay = 0;

	private boolean started = false;
	private boolean stopped = false;
	private boolean endless = false;

	protected double startValue = 0;
	protected double endValue = 0;

	private AnimationScript next;

	public AnimationScript(long time){
		super();

		this.time = time;
	}

	public AnimationScript(long delay, long time){
		super();

		this.delay = delay;
		this.time = time;
	}

	public AnimationScript(Layer target, long time){
		super();

		setTarget(target);
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

		if(started&&!stopped){

			if(now-startedAt-delay>=time){
				//Last Update
				calculate(1);
				stopped = true;
			}else{
				if(now-startedAt>=delay){
					this.animate(now);
				}
			}

		}

	}

	public void animate(long now) {

		long timeElapsed = now-startedAt-delay;

		float factor = (float)timeElapsed/time;

		calculate(factor);
	}
	
	private void calculate(double factor){
		
		double value = startValue+(endValue-startValue)*factor;

		update(value);
		
	}
		
	protected abstract void update(double value);

	public boolean isStopped() {
		return stopped;
	}

	public boolean isEndless() {
		return endless;
	}

	public void setEndless(boolean endless) {
		this.endless = endless;
	}

	public Layer getTarget() {
		return target;
	}

	public void setTarget(Layer target) {
		this.target = target;
	}

	public void setInterval(double startValue, double endValue){
		this.startValue = startValue;
		this.endValue = endValue;
	}

	public AnimationScript getNext() {
		return next;
	}

	public void setNext(AnimationScript next) {
		this.next = next;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}	

}
