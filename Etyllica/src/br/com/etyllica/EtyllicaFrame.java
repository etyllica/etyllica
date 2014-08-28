package br.com.etyllica;

import java.awt.Graphics;

import javax.swing.JFrame;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.SharedCore;
import br.com.etyllica.core.engine.Engine;
import br.com.etyllica.core.engine.SharedEngine;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.input.mouse.Mouse;
import br.com.etyllica.core.loader.Loader;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class EtyllicaFrame extends JFrame implements Engine {

	private static final long serialVersionUID = 4588303747276461888L;

	private SharedCore core;
	
	private SharedEngine engine;

	protected int w = 640;
	protected int h = 480;

	private Application application;

	protected Mouse mouse;

	public EtyllicaFrame(int width, int height) {
		super();

		this.w = width;
		this.h = height;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	@Override
	public void init() {
				
		engine = new SharedEngine(this, w, h);
		
		core = engine.getCore();
		
		core.setEngine(this);

		initialSetup();

		this.application = startApplication();

		core.startCore(application);

		core.startEngine();
		
	}

	private void initialSetup(){

		//Load Monitors
		/*GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();*/

		String s = getClass().getResource("").toString();

		setPath(s);
				
	}

	protected void setPath(String path){

		core.setPath(path);

		engine.initDefault();
		
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