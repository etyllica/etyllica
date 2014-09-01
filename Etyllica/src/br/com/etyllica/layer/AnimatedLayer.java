package br.com.etyllica.layer;

import java.awt.geom.AffineTransform;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.loader.image.ImageLoader;
import br.com.etyllica.animation.listener.OnAnimationFinishListener;
import br.com.etyllica.animation.listener.OnFrameChangeListener;

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
	
	protected boolean animaEmX = true;

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
	 * @param xTile
	 * @param yTile
	 * @param path
	 */
	public AnimatedLayer(int x, int y, int xTile, int yTile, String path) {
		super(x,y,path);
		
		this.tileW = xTile;
		this.tileH = yTile;		
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param xTile
	 * @param yTile
	 */
	public AnimatedLayer(int x, int y, int xTile, int yTile) {
		super(x,y);
		
		this.tileW = xTile;
		this.tileH = yTile;		
	}

	protected void resetAnimation() {
		xImage = needleX;
		yImage = needleY;
		currentFrame = 0;
	}

	public void setAnimaEmX(boolean animaX) {
		animaEmX = animaX;
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
	 * @param xTile
	 * @param yTile
	 */
	public void setTileCoordinates(int tileW, int tileH) {
		setTileW(tileW);
		setTileH(tileH);
	}

	public void animateWithFrame(int frame) {
		setFrame(frame);
		
		if(frame == frames-1) {
			notifyAnimationFinishListener(0);
		}
	}	
	
	public void animate(long now) {
		
		if(stopped) {
			
			startedAt = now;
			changedAt = now;
		
			stopped = false;
			resetAnimation();
		}
	
		if(now>=changedAt+speed) {
			
			changedAt = now;
			
			boolean hasNextFrame = nextFrame(); 
			
			notifyFrameChangeListener(now);
			
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
	private void notifyFrameChangeListener(long now) {
		
		if(onFrameChangeListener != null) {
			onFrameChangeListener.onFrameChange(now);
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

		if(animaEmX) {
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
				
		if(animaEmX) {
			setXImage(needleX+tileW*frame);
		} else {
			setYImage(needleY+tileH*frame);
		}

	}

	@Override
	public int centralizeX(int xInicial, int xFinal) {
		
		int x = (((xInicial+xFinal)/2)-(getTileW()/2));
		
		setX(x);
		
		return x;
	}

	@Override
	public int centralizeY(int yInicial, int yFinal) {
		
		int y = (((yInicial+yFinal)/2)-(getTileH()/2));
		
		setY(y);
		
		return y;
	}

	@Override
	public boolean colideRetangular(Layer b) {
		if(b.getX() + b.getW() < getX())	return false;
		if(b.getX() > getX() + getTileW())		return false;

		if(b.getY() + b.getH() < getY())	return false;
		if(b.getY() > getY() + getTileH())		return false;

		return true;
	}

	public boolean colideRetangular(AnimatedLayer b) {
		
		if(b.getX() + b.getTileW() < getX())	return false;
		if(b.getX() > getX() + getTileW())		return false;

		if(b.getY() + b.getTileH() < getY())	return false;
		if(b.getY() > getY() + getTileH())		return false;

		return true;
		
	}

	public boolean colideCircular(AnimatedLayer b) {
		
		int xdiff = b.getX() - getX();
		int ydiff = b.getY() - getY();

		int dcentre_sq = (ydiff*ydiff) + (xdiff*xdiff);

		int r_sum_sq = b.getTileW()/2 + tileW/2;
		r_sum_sq *= r_sum_sq;

		if(dcentre_sq - r_sum_sq<=0)
		{
			return true;
		}

		return false;
	}

	@Override
	public void simpleDraw(Graphic g) {
		g.drawImage( ImageLoader.getInstance().getImage(path), x, y, x+tileW,y+tileH,
				xImage,yImage,xImage+tileW,yImage+tileH, null );
	}
	
	@Override
	public AffineTransform getTransform() {
		
		AffineTransform transform = new AffineTransform();

		if(angle!=0) {
			transform.concatenate(AffineTransform.getRotateInstance(Math.toRadians(angle),x+tileW/2, y+tileH/2));
		}
		
		if(scale!=1) {

			double sw = tileW*scale;
			double sh = tileH*scale;

			double dx = sw/2-tileW/2;
			double dy = sh/2-tileH/2;

			transform.translate(x-tileW/2-dx, y-tileH/2-dy);

			AffineTransform scaleTransform = new AffineTransform();

			scaleTransform.translate(tileW/2, tileH/2);
			scaleTransform.scale(scale,scale);
			scaleTransform.translate(-x, -y);

			transform.concatenate(scaleTransform);

		}
		
		return transform;
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

	public boolean getAnimaEmX() {
		return animaEmX;
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