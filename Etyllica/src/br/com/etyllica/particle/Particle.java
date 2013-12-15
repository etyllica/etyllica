package br.com.etyllica.particle;

import br.com.etyllica.animation.Updatable;
import br.com.etyllica.layer.Layer;

public abstract class Particle extends Layer implements Updatable{
	
	public Particle(float x, float y){
		super(x,y);
	}
	
}
