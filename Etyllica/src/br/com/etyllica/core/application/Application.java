package br.com.etyllica.core.application;

import br.com.etyllica.core.application.load.DefaultLoadApplication;
import br.com.etyllica.core.application.load.GenericLoadApplication;


public abstract class Application extends Context{

	/**
	 * Load Application
	 */
	protected DefaultLoadApplication loadApplication = null;

	/**
	 * Returned Application (next Application to show up)
	 */
	protected Application returnApplication = this;
		
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

	public void setLoading(float loading) {
		this.loading = 100;
	}
		
}