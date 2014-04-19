package br.com.etyllica.particle.fireworks;

import java.awt.Color;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.particle.BasicParticle;
import br.com.etyllica.particle.Emitter;
import br.com.etyllica.particle.Particle;

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
