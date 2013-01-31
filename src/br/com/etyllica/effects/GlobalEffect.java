package br.com.etyllica.effects;

import java.util.Timer;
import java.util.TimerTask;

import br.com.etyllica.core.DrawableComponent;
import br.com.etyllica.layer.Layer;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public abstract class GlobalEffect extends Layer implements DrawableComponent{

	private Timer timer;
	private long delay = 0;
	private boolean end = false;
	
	protected int steps = 0;
	protected int maxSteps = 0;

	public GlobalEffect(int x, int y, int w, int h){
		super(x,y,w,h);
	}

	/**
	 * Method to start effect,
	 * each delay represents one step
	 * 
	 * @param delay
	 * @param maxSteps
	 */
	public void start(long delay, int maxSteps){
		this.delay = delay;
		this.maxSteps = maxSteps;

		initTimer();
	}
	
	private void initTimer(){
		timer = new Timer();

		TimerTask task = new TimerTask() {

			@Override
			public void run() {

				if(steps<maxSteps){
					steps++;
					updateFX();
				}else{
					end = true;
					timer.purge();
				}

			}
		};

		timer.scheduleAtFixedRate(task, delay, delay);
	}

	protected abstract void updateFX();

	public boolean isEnd() {
		return end;
	}

}