package br.com.etyllica.animation;

import br.com.etyllica.layer.Layer;

public abstract class AnimationScript {

	protected Layer target;

	protected long startedAt = 0;

	protected long time = 0;

	protected boolean started = false;
	protected boolean stopped = false;
	
	private AnimationScript next;

	public AnimationScript(long time){
		super();

		this.time = time;
	}
	
	public AnimationScript(long time, Layer target){
		super();

		this.time = time;
		this.target = target;
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

	public abstract void animate(long now);

	public boolean isStopped() {
		return stopped;
	}

	public Layer getTarget() {
		return target;
	}

	public void setTarget(Layer target) {
		this.target = target;
	}

	public AnimationScript getNext() {
		return next;
	}

	public void setNext(AnimationScript next) {
		this.next = next;
	}
	
}