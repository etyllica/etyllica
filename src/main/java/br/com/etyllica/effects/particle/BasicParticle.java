package br.com.etyllica.effects.particle;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;


public class BasicParticle extends Particle{

	private ImageLayer layer;
	
	public BasicParticle(int x, int y){
		super(x,y);
		
		layer = new ImageLayer(x, y, "particle.png");
	}

	@Override
	public void draw(Graphic g){
	
		layer.draw(g);
		
	}
	
	@Override
	public void setX(int x){
		this.x = x;
		
		layer.setX(x);
	}
	
	@Override
	public void setY(int y){
		this.y = y;
		
		layer.setY(y);
	}

	@Override
	public void update(long now) {		
		setOffsetY(-1);
	}
	
}
