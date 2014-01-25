package br.com.etyllica;

import java.applet.Applet;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.Engine;
import br.com.etyllica.core.SharedCore;
import br.com.etyllica.core.loader.FontLoader;
import br.com.etyllica.core.loader.Loader;
import br.com.etyllica.core.loader.image.ImageLoader;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class Etyllica extends Applet implements Engine{

	private static final long serialVersionUID = 4588303747276461888L;

	private SharedCore core;

	protected int w = 640;
	protected int h = 480;

	//TODO define fps by Application
	protected int updateDelay = 40; // 40ms. Implica em 25fps (1000/40) = 25	

	private Application application;

	private Set<Loader> loaders = new HashSet<Loader>();

	public Etyllica(int largura, int altura){

		this.w = largura;
		this.h = altura;

	}

	@Override
	public void init() {

		core = new SharedCore(this, w, h);
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

		initLoaders();

	}

	private void initLoaders(){

		addLoader(ImageLoader.getInstance());
		addLoader(FontLoader.getInstance());

		core.setLoaders(loaders);

		//initSound
		//addLoader(MultimediaLoader.getInstance());
		//init3D
		//addLoader(MeshLoader.getInstance());
		//initSystemFonts
		//addLoader(SystemFontLoader.getInstance());
		//initJoystick
		//addLoader(JoystickLoader.getInstance());

		core.initDefault();

	}

	@Override
	public void paint( Graphics g ) {
		core.paint(g);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	public void draw(){
		repaint();
	}
		
	protected void addLoader(Loader loader) {
		loaders.add(loader);
	}

	protected void hideCursor() {
		core.hideCursor();
	}

}