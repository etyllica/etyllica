package br.com.etyllica.awt;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.awt.camera.Camera;
import br.com.etyllica.commons.context.Context;
import br.com.etyllica.commons.context.ContextContainer;
import br.com.etyllica.commons.context.Session;
import br.com.etyllica.layer.GeometricLayer;

/**
 * 
 * @author yuripourre
 *
 */

public class AWTWindow extends GeometricLayer implements ContextContainer {
	
	private java.awt.Component component;
	
	protected Context application;

	protected Camera camera;

	protected boolean close = false;
	
	private List<AWTWindow> windows = new ArrayList<AWTWindow>();
		
	public AWTWindow(int w, int h) {
		this(0,0,w,h);
	}		
	
	public AWTWindow(int x, int y, int w, int h) {
		super(x,y,w,h);
		
		camera = new Camera(x, y, w, h);
	}
	
	public Rectangle getAsRectangle() {
		return new Rectangle(x, y, w, h);
	}

	public void restartWindow() {
		
	}

	public void setApplication(Context application) {
		this.application = application;
	}
	
	public void closeWindow() {
		setClose(true);
	}

	public boolean isClose() {
		return close;
	}

	public void setClose(boolean close) {
		this.close = close;
	}

	public List<AWTWindow> getWindows() {
		return windows;
	}

	public void setWindows(List<AWTWindow> windows) {
		this.windows = windows;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	@Override
	public Context getContext() {
		return application;
	}

	public java.awt.Component getComponent() {
		return component;
	}

	public void setComponent(java.awt.Component component) {
		this.component = component;
	}			
}
