package br.com.etyllica;

import java.applet.Applet;
import java.awt.Graphics;
import java.net.URL;

import br.com.etyllica.awt.core.AWTCore;
import br.com.etyllica.awt.engine.AWTEngine;
import br.com.etyllica.core.Engine;
import br.com.etyllica.commons.module.Module;
import br.com.etyllica.core.animation.AnimationModule;
import br.com.etyllica.commons.context.Application;
import br.com.etyllica.core.EtyllicaFrame;
import br.com.etyllica.commons.event.GUIEvent;
import br.com.etyllica.i18n.LanguageModule;
import br.com.etyllica.loader.Loader;
import br.com.etyllica.ui.UI;
import br.com.etyllica.util.PathHelper;

/**
 * 
 * @author yuripourre
 *
 */

public abstract class EtyllicaApplet extends Applet implements EtyllicaFrame {

	private static final long serialVersionUID = 4588303747276461888L;
	
	private AWTCore core;
	private Engine engine;
		
	protected int w = 640;
	protected int h = 480;

	private Application application;
		
	public EtyllicaApplet(int width, int height) {
		super();
		
		this.w = width;
		this.h = height;	
	}
	
	public void init(String path) {
		initCore();
		addModules();
		setPath(path);

		this.application = startApplication();
		
		startCore();
	}
	
	@Override
	public void init() {
		initCore();
		addModules();
		initialSetup("");

		this.application = startApplication();
		
		startCore();
	}
	
	private void initCore() {
		engine = new AWTEngine(this, w, h);
		
		core = engine.getCore();
		core.setEngine(this);
	}
	
	private void startCore() {
		core.startCore(application);
		core.startEngine();
		
		addComponentListener(core);
	}

	protected void addModule(Module module) {
		core.addModule(module);
	}

	private void addModules() {
		addModule(AnimationModule.getInstance());
		addModule(UI.getInstance());
		addModule(LanguageModule.getInstance());
	}

	protected void initialSetup(String suffix) {

		//Load Monitors
		/*GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();*/

		String defaultPath = PathHelper.currentFileDirectory().toString();
					
		setPath(defaultPath+suffix);
	}
	
	protected void setPath(URL url) {
		setPath(url.toString());
	}

	protected void setPath(String path) {
		core.setPath(path);
		engine.init();
	}

	@Override
	public void paint( Graphics g ) {
		core.paint(g);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	public void draw() {
		repaint();
	}
	
	public void addLoader(Loader loader) {
		engine.addLoader(loader);
	}

	protected void hideCursor() {
		engine.hideCursor();
	}
	
	@Override
	public void updateSuperEvent(GUIEvent event) {
		engine.updateSuperEvent(event);
	}

}