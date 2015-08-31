package br.com.etyllica.layer;

import br.com.etyllica.core.animation.OnAnimationFinishListener;
import br.com.etyllica.core.animation.OnFrameChangeListener;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class AnimatedLayer extends ImageLayer {

	protected int tileW = 0;
	
	protected int tileH = 0;
	
	protected int needleX = 0;
	
	protected int needleY = 0;

	protected boolean once = false;
	
	protected boolean stopped = true;
		
	protected boolean animateHorizontally = true;

	protected boolean lockOnce = false;

	private int inc = 1;

	protected int frames = 1;
	
	protected int currentFrame = 0;

	protected int speed = 500;
	
	protected long startedAt = 0;
	
	protected long changedAt = 0;

	protected OnAnimationFinishListener onAnimationFinishListener;
	
	protected OnFrameChangeListener onFrameChangeListener;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public AnimatedLayer(int x, int y) {
		this(x, y, 0, 0);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param tileW
	 * @param tileH
	 * @param path
	 */
	public AnimatedLayer(int x, int y, int tileW, int tileH, String path) {
		super(x,y,path);
		
		this.tileW = tileW;
		this.tileH = tileH;		
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param tileW
	 * @param tileH
	 */
	public AnimatedLayer(int x, int y, int tileW, int tileH) {
		super(x,y);
		
		this.tileW = tileW;
		this.tileH = tileH;		
	}

	public void restartAnimation() {
		stopped = false;
		resetAnimation();
	}
	
	public void resetAnimation() {
		xImage = needleX;
		yImage = needleY;
		currentFrame = 0;
	}

	public void setAnimateHorizontally(boolean animateHorizontally) {
		this.animateHorizontally = animateHorizontally;
	}

	public int getTileW() {
		return tileW;
	}

	public int getTileH() {
		return tileH;
	}

	/**
	 * 
	 * @param tileW
	 */
	public void setTileW(int tileW) {
		this.tileW = tileW;
	}

	/**
	 * 
	 * @param tileH
	 */
	public void setTileH(int tileH) {
		this.tileH = tileH;
	}

	/**
	 * 
	 * @param tileW
	 * @param tileH
	 */
	public void setTileCoordinates(int tileW, int tileH) {
		setTileW(tileW);
		setTileH(tileH);
	}

	public void animateWithFrame(int frame) {
		if(this.currentFrame != frame) {
			notifyFrameChangeListener(0, frame);	
		}
		
		setFrame(frame);
		
		if(frame == frames-1) {
			notifyAnimationFinishListener(0);
		}
	}	
	
	public void animate(long now) {
		
		if(stopped) {
			
			startedAt = now;
			changedAt = now;
		
			restartAnimation();
		}
	
		if(now >= changedAt+speed) {
			
			changedAt = now;
			
			boolean hasNextFrame = nextFrame(); 
			
			notifyFrameChangeListener(now, currentFrame);
			
			if(!hasNextFrame) {

				notifyAnimationFinishListener(now);
				
			}
						
		}
		
	}
		
	//Notify Listener about the end of animation
	protected void notifyAnimationFinishListener(long now) {
		
		if(onAnimationFinishListener != null) {
			onAnimationFinishListener.onAnimationFinish(now);
		}
		
	}
	
	//Notify Listener about the frame change
	protected void notifyFrameChangeListener(long now, int currentFrame) {
		if(onFrameChangeListener != null) {
			onFrameChangeListener.onFrameChange(now, currentFrame);
		}
	}
	
	public void animate() {

		nextFrame();

		stopped = false;
	}

	public void stopAnimation() {
		stopped = true;
	}	

	public void animaOnce() {

		visible = true;
		
		lockOnce = false;
		
		once = true;
		
		stopped = false;

		currentFrame = 0;

		if(animateHorizontally) {
			xImage = 0;
		}else{
			yImage = 0;
		}

	}
	
	public boolean nextFrame() {
		
		boolean hasNextFrame = true;
		
		if((currentFrame < frames-1) && (currentFrame >= 0)) {
		
			currentFrame+=inc;
			
		} else {

			if(once) {
				visible = false;
				lockOnce = true;
				//stopped = true;
				//setFrame(currentFrame);
				
			} else {
			
				currentFrame = 0;
				
			}
			
			hasNextFrame = false;
						
		}

		if(!stopped) {
			setFrame(currentFrame);
		}
		
		return hasNextFrame;
	}

	private void setFrame(int frame) {
				
		if(animateHorizontally) {
			setXImage(needleX+tileW*frame);
		} else {
			setYImage(needleY+tileH*frame);
		}

	}
	
	@Override
	public int utilWidth() {
		return tileW;
	}
	
	@Override
	public int utilHeight() {
		return tileH;
	}
	
	/**
	 * 
	 * @param stopped
	 */
	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

	public boolean isStopped() {
		return stopped;
	}


	/**
	 * Set Number of Frames
	 * 
	 * @param frames
	 */
	public void setFrames(int frames) {
		this.frames = frames;
	}

	public int getFrames() {
		return frames;
	}
	
	public int getCurrentFrame() {
		return currentFrame;
	}

	public boolean getAnimateHorizontally() {
		return animateHorizontally;
	}

	public void setLockOnce(boolean lockOnce) {
		this.lockOnce = lockOnce;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getNeedleX() {
		return needleX;
	}

	public void setNeedleX(int needleX) {
		this.needleX = needleX;
	}

	public int getNeedleY() {
		return needleY;
	}

	public void setNeedleY(int needleY) {
		this.needleY = needleY;
	}

	public OnAnimationFinishListener getListener() {
		return onAnimationFinishListener;
	}

	public void setOnAnimationFinishListener(OnAnimationFinishListener onAnimationFinishListener) {
		this.onAnimationFinishListener = onAnimationFinishListener;
	}
	
	public void setOnFrameChangeListener(OnFrameChangeListener onFrameChangeListener) {
		this.onFrameChangeListener = onFrameChangeListener;
	}
		
}