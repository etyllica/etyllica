package br.com.etyllica.context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.etyllica.animation.AnimationHandler;
import br.com.etyllica.animation.scripts.AnimationScript;
import br.com.etyllica.core.Updatable;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.Layer;
import br.com.etyllica.particle.Emitter;

public class Scene implements Updatable {
	
	protected List<Updatable> updatables = new ArrayList<Updatable>();
	
	protected Map<Layer, List<Layer>> graph = new LinkedHashMap<Layer, List<Layer>>();
	
	public Scene() {
		super();
	}
	
	public void addAnimation(AnimationScript animation) {
		AnimationHandler.getInstance().add(animation);
	}
	
	public void addEmitter(Emitter emitter) {
		updatables.add(emitter);
		add(emitter);
	}
	
	public void add(Layer layer) {
		graph.put(layer, new ArrayList<Layer>());
	}
	
	public void addChild(Layer layer, Layer child) {
				
		if(!graph.containsKey(layer)) {
			add(layer);
		}
		
		List<Layer> children = graph.get(layer);
		children.add(child);
	}
	
	public Map<Layer, List<Layer>> getGraph() {
		return graph;
	}

	@Override
	public void update(long now) {
		
		for(Updatable updatable: updatables) {
			updatable.update(now);
		}		
	}
	
	/**
	 * Draw Scene method with recurrency
	 */
	public void draw(Graphic g) {
		
		for(Layer layer: graph.keySet()) {
			drawLayer(g, layer);
		}
	}

	private void drawLayer(Graphic g, Layer layer) {
		
		layer.draw(g);
		
		List<Layer> children = graph.get(layer);
		
		for(Layer child: children) {
			drawLayer(g, child);
		}
	}
	
}
