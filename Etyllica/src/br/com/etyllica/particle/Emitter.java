package br.com.etyllica.particle;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.animation.Updatable;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.Layer;

public abstract class Emitter extends Layer implements Updatable{

	List<Particle> particles = new ArrayList<Particle>();
	
	public Emitter(){
		super();
	}

	@Override
	public void update(long now) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Graphic g){
		drawEmitter(g);
		drawParticles(g);
	}
	
	public void drawParticles(Graphic g){
		for(Particle particle: particles){
			particle.draw(g);
		}
	}
	
	public abstract void drawEmitter(Graphic g);
	
}
