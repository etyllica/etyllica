package org.dyn4j;

import org.dyn4j.dynamics.World;

import br.com.etyllica.context.Application;

public abstract class PhysicsApplication extends Application{

	public PhysicsApplication(int w, int h) {
		super(w, h);
		// TODO Auto-generated constructor stub
	}

	/**
	 * The Physic World
	 */
	protected World world = new World();
	
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
}
