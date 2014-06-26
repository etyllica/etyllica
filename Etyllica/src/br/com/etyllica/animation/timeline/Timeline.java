package br.com.etyllica.animation.timeline;

import java.util.ArrayList;
import java.util.List;

public abstract class Timeline<T> {
	
	protected int cursor = 0;
	
	protected List<KeyFrame<T>> frames = new ArrayList<KeyFrame<T>>();
	
	public Timeline() {
		super();
	}

	public void nextFrame() {
		
		if(cursor<frames.size()-1) {
			cursor++;
			
			reloadFrame(currentFrame());
		}		
	}
	
	public void previousFrame() {
		
		if(cursor > 0) {
			cursor--;
			
			reloadFrame(currentFrame());
		}
	}
	
	public KeyFrame<T> currentFrame() {
		return frames.get(cursor);
	}
	
	public abstract void reloadFrame(KeyFrame<T> frame);
	
}
