package br.com.etyllica.core.effect.particle;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.Updatable;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.Layer;

public abstract class Emitter extends Layer implements Updatable{
	
	protected List<Particle> particles = new ArrayList<Particle>();
	
	public Emitter(int x, int y) {
		super(x, y);
	}
	
	public Emitter(int x, int y, int w, int h) {
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
		
		if (now-lastParticleUpdate >= particleDelay) {
			
			if(particles.size() >= maxParticles) {
				particles.remove(0);
			}
			
			particles.add(createParticle(now));
			
			lastParticleUpdate = now;
		}
						
		if (diff >= delay) {
		
			for(Particle particle: particles) {
				particle.update(now);				
			}
			
			lastUpdate = now;
		
		}

	}
	
	protected abstract Particle createParticle(long now);
	
	@Override
	public void draw(Graphics g) {
		drawEmitter(g);
		drawParticles(g);
	}
	
	public void drawParticles(Graphics g) {
		for(Particle particle: particles) {
			particle.draw(g);
		}
	}
	
	public abstract void drawEmitter(Graphics g);
	
}
