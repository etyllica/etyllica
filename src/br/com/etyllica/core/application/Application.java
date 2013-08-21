package br.com.etyllica.core.application;

import org.dyn4j.dynamics.World;


public abstract class Application extends InternalApplication{

	/**
	 * Load Application
	 */
	protected DefaultLoadApplication loadApplication = null;

	/**
	 * Returned Application (next Application to show up)
	 */
	protected Application returnApplication = this;
	
	/**
	 * The Physic World
	 */
	protected World world;
	
	public Application(int w, int h) {
		super(w, h);
		loadApplication = new GenericLoadApplication(0,0,w,h);
	}
	
	public Application(int x, int y, int w, int h) {
		super(x, y, w, h);
		loadApplication = new GenericLoadApplication(x,y,w,h);
	}

	public DefaultLoadApplication getLoadApplication() {
		return loadApplication;
	}
	
	public Application getReturnApplication() {
		return returnApplication;
	}

	/**
	 * 
	 * @param returnApplication
	 */
	public void setReturnApplication(Application returnApplication) {
		this.returnApplication = returnApplication;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
		
}
