package br.com.etyllica.animation.timeline;

import java.util.HashMap;
import java.util.Map;

import br.com.etyllica.animation.transformation.Transform;

public class KeyFrame<T> {
		
	private int time = 0;
	
	private Map<T, Transform> transformations = new HashMap<T, Transform>();
		
	public KeyFrame() {
		super();
	}
	
	public KeyFrame(int time) {
		super();
		
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public void addRotation(T bone, int angle) {
		
		transformations.put(bone, new Transform(angle));
	}

	public Transform getTransformation(T bone) {
		return transformations.get(bone);
	}
	
	public Map<T, Transform> getTransformations() {
		return transformations;
	}
	
}
