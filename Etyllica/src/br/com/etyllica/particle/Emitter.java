package br.com.etyllica.particle;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.animation.Updatable;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.Layer;

public abstract class Emitter extends Layer implements Updatable{
	
	protected List<Particle> particles = new ArrayList<Particle>();
	
	public Emitter(float x, float y){
		super(x, y);
	}
	
	public Emitter(float x, float y, float w, float h){
		super(x, y, w, h);
	}

	private int maxParticles = 15;
	
	private long lastUpdate = 0;
	private long lastParticleUpdate = 0;
	
	private long delay = 40;
	private long particleDelay = 1500;
		
	@Override
	public void update(long now) {

		long diff = now-lastUpdate;
		
		if(now-lastParticleUpdate>=particleDelay){
			
			if(particles.size()>=maxParticles){
				particles.remove(0);
			}
			
			particles.add(createParticle(now));
			
			lastParticleUpdate = now;
		}
						
		if(diff>=delay){
		
			for(Particle particle: particles){
				particle.update(now);				
			}
			
			lastUpdate = now;
		
		}

	}
	
	protected abstract Particle createParticle(long now);
	
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
