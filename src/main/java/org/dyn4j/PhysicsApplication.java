package org.dyn4j;

import org.dyn4j.dynamics.World;

import br.com.etyllica.core.context.Application;

public abstract class PhysicsApplication extends Application{

	/**
	 * The Physic World
	 */
	protected World world = new World();
	
	public PhysicsApplication(int w, int h) {
		super(w, h);
		
		this.world.setGravity(World.EARTH_GRAVITY.negate());
		
	}
	
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
}
