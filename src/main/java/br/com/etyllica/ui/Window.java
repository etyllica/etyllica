package br.com.etyllica.ui;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.cinematics.Camera;
import br.com.etyllica.core.context.Context;
import br.com.etyllica.core.context.ContextContainer;
import br.com.etyllica.core.context.Session;
import br.com.etyllica.layer.GeometricLayer;

/**
 * 
 * @author yuripourre
 *
 */

public class Window extends GeometricLayer implements ContextContainer {
	
	private java.awt.Component component;
	
	protected Context application;

	//TODO Change to Application backApplication
	protected List<Context> oldApplications = new ArrayList<Context>();

	protected Camera camera;
	
	protected Session session = new Session();

	protected boolean close = false;
	
	private List<Window> windows = new ArrayList<Window>();
		
	public Window(int w, int h) {
		this(0,0,w,h);
	}		
	
	public Window(int x, int y, int w, int h) {
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

	public List<Window> getWindows() {
		return windows;
	}

	public void setWindows(List<Window> windows) {
		this.windows = windows;
	}
	
	public Session getSession() {
		return session;
	}

	public void setSessionMap(Session sessionMap) {
		this.session = sessionMap;
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
