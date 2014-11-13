package br.com.etyllica.core.engine;

import java.awt.Component;
import java.util.HashSet;
import java.util.Set;

import br.com.etyllica.context.Session;
import br.com.etyllica.core.SharedCore;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.loader.FontLoader;
import br.com.etyllica.core.loader.Loader;
import br.com.etyllica.core.loader.image.ImageLoader;

public class SharedEngine {

	private Component component;

	private SharedCore core;

	private Set<Loader> loaders = new HashSet<Loader>();

	public SharedEngine(Component component, int w, int h, Session session) {
		super();

		this.component = component;

		core = new SharedCore(component, w, h);		
		core.setSession(session);
				
	}

	public void updateSuperEvent(GUIEvent event) {

		if(event==GUIEvent.ENABLE_FULL_SCREEN) {

			core.enableFullScreen();

		}else if(event==GUIEvent.DISABLE_FULL_SCREEN) {

			core.disableFullScreen();

			//TODO When Frame
			//}else if(event==GUIEvent.WINDOW_MOVE) {
			//	setLocation(this.getX()+(mouse.getX()-mouse.getDragX()), this.getY()+(mouse.getY()-mouse.getDragY()));
		}
		else if(event==GUIEvent.REQUEST_FOCUS) {

			if ( !component.hasFocus() ) {
				component.requestFocus();
			}

		}

	}
	
	public SharedCore getCore() {
		return core;
	}

	public void initDefault() {
		
		initLoaders();

		core.initDefault();
	}
	
	public void initLoaders() {

		addLoader(ImageLoader.getInstance());
		addLoader(FontLoader.getInstance());

		core.setLoaders(loaders);		
	}

	public Set<Loader> getLoaders() {
		return loaders;
	}

	public void addLoader(Loader loader) {
		loaders.add(loader);
	}
	
	public void hideCursor() {
		core.hideCursor();
	}

}
