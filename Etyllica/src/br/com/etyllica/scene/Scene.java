package br.com.etyllica.scene;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.animation.AnimationHandler;
import br.com.etyllica.animation.AnimationScript;
import br.com.etyllica.animation.Updatable;
import br.com.etyllica.layer.Layer;

public class Scene implements Updatable {

	/**
	 * Animation
	 */
	protected AnimationHandler animationHandler = new AnimationHandler();
	
	protected List<Layer> graph = new ArrayList<Layer>();

	public void addAnimation(AnimationScript animation){
		animationHandler.add(animation);
	}
	
	public void add(Layer layer){
		graph.add(layer);
	}
	
	public List<Layer> getGraph(){
		return graph;
	}

	@Override
	public void update(long now) {
		animationHandler.update(now);
	}
	
	
	
}
