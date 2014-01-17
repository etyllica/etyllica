package br.com.etyllica;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.Engine;
import br.com.etyllica.core.SharedCore;
import br.com.etyllica.core.input.mouse.Mouse;
import br.com.etyllica.core.loader.FontLoader;
import br.com.etyllica.core.loader.Loader;
import br.com.etyllica.core.loader.image.ImageLoader;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class EtyllicaFrame extends JFrame implements Engine{

	private static final long serialVersionUID = 4588303747276461888L;

	private SharedCore core;

	protected int w = 640;
	protected int h = 480;

	protected int updateDelay = 40; // 40ms. Implica em 25fps (1000/20) = 50
	protected int drawDelay = 25; // 40ms. Implica em 25fps (1000/20) = 50

	private Application application;

	private Set<Loader> loaders = new HashSet<Loader>();

	protected Mouse mouse;

	public EtyllicaFrame(int width, int height){
		super();

		this.w = width;
		this.h = height;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	public void init() {

		core = new SharedCore(this, w, h);
		core.setEngine(this);

		initialSetup();

		startGame();

		core.startCore(application);
		
		core.startEngine();
		
	}
	
	private void initialSetup(){

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
		//core.addLoader(MultimediaLoader.getInstance());
		//init3D
		//core.addLoader(MeshLoader.getInstance());
		//initSystemFonts
		//core.addLoader(SystemFontLoader.getInstance());

		//core.addLoader(JoystickLoader.getInstance());

		core.initDefault();

	}

	public abstract void startGame();

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

	public void setMainApplication(Application application){
		this.application = application;
	}	

}