package br.com.etyllica.effects.particle;

import br.com.etyllica.core.Updatable;
import br.com.etyllica.layer.Layer;

public abstract class Particle extends Layer implements Updatable{
	
	public Particle(int x, int y){
		super(x,y);
	}
	
}
