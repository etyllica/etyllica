package br.com.etyllica;

import java.awt.Graphics;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import sound.MultimediaLoader;
import br.com.etyllica.core.Engine;
import br.com.etyllica.core.SharedCore;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.input.mouse.Mouse;
import br.com.etyllica.core.loader.FontLoader;
import br.com.etyllica.core.loader.ImageLoader;
import br.com.etyllica.core.loader.JoystickLoader;
import br.com.etyllica.core.loader.Loader;
import br.com.etyllica.core.loader.SystemFontLoader;
import br.com.luvia.loader.MeshLoader;

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

	private final int UPDATE_DELAY = 40; // 40ms. Implica em 25fps (1000/40) = 25

	private Application application;

	protected Mouse mouse;

	//From Luvia
	private ScheduledExecutorService executor;

	protected boolean initAll = false;
	protected boolean initSound = false;
	protected boolean initJoysick = false;
	protected boolean init3D = false;
	protected boolean initSystemFonts = false;

	public EtyllicaFrame(int width, int height){

		this.w = width;
		this.h = height;

	}

	public void init() {

		core = new SharedCore(this, w, h);

		initialSetup();

		startGame();

		core.startCore(application);

		executor = Executors.newScheduledThreadPool(2);
		startEngine();

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

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

	private void startEngine(){

		Runnable engine = new Runnable() {
			public void run() {
				draw();
				update();
			}
		};

		executor.scheduleAtFixedRate(engine, 0, UPDATE_DELAY, TimeUnit.MILLISECONDS);

	}

private void initLoaders(){
		
		core.addLoader(ImageLoader.getInstance());
		core.addLoader(FontLoader.getInstance());
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

	public void update(){

		GUIEvent event = GUIEvent.NONE;

		core.gerencia();

		event = core.getSuperEvent();

		if(event==GUIEvent.ENABLE_FULL_SCREEN){
			core.enableFullScreen();
		}else if(event==GUIEvent.DISABLE_FULL_SCREEN){
			core.disableFullScreen();

			//TODO When Frame
		}else if(event==GUIEvent.WINDOW_MOVE){
			setLocation(this.getX()+(mouse.getX()-mouse.getDragX()), this.getY()+(mouse.getY()-mouse.getDragY()));
		}

		else if(event==GUIEvent.REQUEST_FOCUS){

			if ( !hasFocus() ) {
				requestFocus();
			}
		}

		//Calls Garbage Collector
		//System.gc();

	}
	
	protected void addLoader(Loader loader) {
		core.addLoader(loader);
	}

	protected void hideCursor() {
		core.hideCursor();
	}

	public void setMainApplication(Application application){
		this.application = application;
	}	

}