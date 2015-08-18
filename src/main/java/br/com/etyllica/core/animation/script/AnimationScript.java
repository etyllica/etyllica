package br.com.etyllica.core.animation.script;

import br.com.etyllica.core.animation.OnAnimationFinishListener;
import br.com.etyllica.core.interpolation.Interpolator;
import br.com.etyllica.core.interpolation.LinearInterpolator;


public abstract class AnimationScript {

	protected long startedAt = 0;
	protected long duration = 0;
	protected long delay = 0;
	protected int loop = 0;

	private boolean started = false;
	private boolean stopped = false;
	
	protected long endDelay = 0;	

	private AnimationScript next;
	
	public static final int REPEAT_FOREVER = -1;
	
	private OnAnimationFinishListener listener;
	
	protected Interpolator interpolator = new LinearInterpolator();

	public AnimationScript(long time) {
		super();

		this.duration = time;
	}

	public AnimationScript(long delay, long time) {
		super();

		this.delay = delay;
		this.duration = time;
	}

	public AnimationScript() {
		super();
	}

	public void restart() {
		started = false;
		stopped = false;
	}

	public void start(long now) {
		this.startedAt = now;
	}

	public void preAnimate(long now) {

		if(!started) {
			started = true;
			stopped = false;
			startedAt = now;
		}

		if(started && !stopped) {

			long elapsedTime = now-startedAt-delay;
			
			if(elapsedTime >= duration) {
				
				if(elapsedTime >= duration+endDelay) {
					stopped = true;
				}
				
			} else {
				if(now-startedAt >= delay) {
					this.animate(now);
				}
			}

		}

	}

	public void animate(long now) {
		long timeElapsed = now-startedAt-delay;
		float factor = (float)timeElapsed/duration;

		calculate(factor);
	}
	
	public abstract void calculate(double factor);
	
	public boolean isStopped() {
		return stopped;
	}

	public int getRepeat() {
		return loop;
	}

	public void setRepeat(int repeat) {
		this.loop = repeat;
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
	
	public void setEndDelay(long endDelay) {
		this.endDelay = endDelay;
	}

	public OnAnimationFinishListener getListener() {
		return listener;
	}

	public void setListener(OnAnimationFinishListener listener) {
		this.listener = listener;
	}

	public Interpolator getInterpolator() {
		return interpolator;
	}

	public void setInterpolator(Interpolator interpolator) {
		this.interpolator = interpolator;
	}

	public void finish(long now) {
		if(listener==null)
			return;
		
		listener.onAnimationFinish(now);
	}

}
