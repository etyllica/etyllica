package br.com.etyllica.particle;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.animation.Updatable;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.Layer;

public abstract class Emitter extends Layer implements Updatable{
	
	protected List<Particle> particles = new ArrayList<Particle>();
	
	public Emitter(int x, int y){
		super(x, y);
	}
	
	public Emitter(int x, int y, int w, int h){
		super(x, y, w, h);
	}

	private long lastUpdate = 0;
	
	private long initialDelay = 4000;
	private long delay = 40;
	
	private boolean emit = false;
	
	@Override
	public void update(long now) {
		
		System.out.println("time: "+now);

		long diff = now-lastUpdate;
		
		System.out.println("diff: "+diff);
		
		if(!emit){
		
			if(now>initialDelay){
				
				particles.add(createParticle());
				
				emit = true;
				
			}
			
		}
				
		if(diff>=delay){
		
			for(Particle particle: particles){
				updateParticle(particle);
			}
			
			lastUpdate = now;
		
		}

	}
	
	protected abstract void updateParticle(Particle particle);
	
	protected abstract Particle createParticle();
	
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
