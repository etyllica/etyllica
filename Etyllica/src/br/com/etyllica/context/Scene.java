package br.com.etyllica.context;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.animation.AnimationHandler;
import br.com.etyllica.animation.scripts.AnimationScript;
import br.com.etyllica.core.Updatable;
import br.com.etyllica.layer.Layer;
import br.com.etyllica.particle.Emitter;

public class Scene implements Updatable {
	
	protected List<Updatable> updatables = new ArrayList<Updatable>();
	
	protected List<Layer> graph = new ArrayList<Layer>();

	public Scene(){
		super();
	}
	
	public void addAnimation(AnimationScript animation){
		AnimationHandler.getInstance().add(animation);
	}
	
	public void addEmitter(Emitter emitter){
		updatables.add(emitter);
		graph.add(emitter);	
	}
	
	public void add(Layer layer){
		graph.add(layer);
	}
	
	public List<Layer> getGraph(){
		return graph;
	}

	@Override
	public void update(long now) {
		
		for(Updatable updatable: updatables){
			updatable.update(now);
		}
		
	}
		
}
