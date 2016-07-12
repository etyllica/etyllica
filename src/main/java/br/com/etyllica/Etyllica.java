package br.com.etyllica;

import java.awt.Graphics;
import java.net.URL;

import javax.swing.JFrame;

import br.com.etyllica.awt.core.AWTCore;
import br.com.etyllica.awt.engine.AWTEngine;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.engine.Engine;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.loader.Loader;
import br.com.etyllica.loader.image.ImageLoader;
import br.com.etyllica.util.PathHelper;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class Etyllica extends JFrame implements Engine {

	private static final long serialVersionUID = 4588303747276461888L;

	private AWTCore core;
	private AWTEngine engine;
	
	protected int w = 640;
	protected int h = 480;
	protected String icon = "";

	private Application application;
	
	public Etyllica(int width, int height) {
		super();

		this.w = width;
		this.h = height;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
	public void init(String path) {
		
		initCore();

		setPath(path);

		this.application = startApplication();
		
		startCore();
		updateIcon();
	}
	
	@Override
	public void init() {

		initCore();

		initialSetup("");
		
		this.application = startApplication();
		
		startCore();
		updateIcon();
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
		engine.initDefault();
	}
	
	protected String getPath() {
		return core.getPath();
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
	
	protected void setIcon(String icon) {
		this.icon = icon;
	}
		
	private void updateIcon() {
		if(!icon.isEmpty()) {
			setIconImage(ImageLoader.getInstance().getImage(icon));
		}
	}

}