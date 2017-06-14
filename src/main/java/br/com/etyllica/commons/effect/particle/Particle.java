package br.com.etyllica.commons.effect.particle;

import br.com.etyllica.commons.Updatable;
import br.com.etyllica.layer.Layer;

public abstract class Particle extends Layer implements Updatable {
	
	public Particle(int x, int y) {
		super(x,y);
	}
	
}
