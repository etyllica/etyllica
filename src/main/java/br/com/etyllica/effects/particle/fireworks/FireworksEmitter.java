package br.com.etyllica.effects.particle.fireworks;

import java.awt.Color;

import br.com.etyllica.core.effect.particle.Emitter;
import br.com.etyllica.core.effect.particle.Particle;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.effects.particle.BasicParticle;

public class FireworksEmitter extends Emitter{

	public FireworksEmitter(int x, int y){
		super(x, y, 20, 20);
	}
	
	@Override
	public void drawEmitter(Graphic g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, w, h);
	}

	@Override
	protected Particle createParticle(long now) {
		
		BasicParticle particle = new BasicParticle(x,y-20);
		
		return particle;
		
	}

}
