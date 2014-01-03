package br.com.etyllica;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import br.com.etyllica.core.Engine;
import br.com.etyllica.core.SharedCore;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.input.mouse.Mouse;
import br.com.etyllica.core.loader.FontLoader;
import br.com.etyllica.core.loader.ImageLoader;
import br.com.etyllica.core.loader.Loader;

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

	//From Luvia
	private ScheduledExecutorService executor;

	public EtyllicaFrame(int width, int height){
		super();

		this.w = width;
		this.h = height;

	}
	
	public void init() {

		core = new SharedCore(this, w, h);

		initialSetup();

		startGame();

		core.startCore(application);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//startEngineLoop();
		
		startEngine();

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
	
	boolean quit = false;
	
	private void startEngineLoop(){
				
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D; //~60fps

		int ups = 0;
		int fps = 0;

		long lastTimer = System.currentTimeMillis();
		long delta = 0;

		while(!quit) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			
			boolean renderOK = false;
			
			while(delta >= 1) {
				ups++;
				update(delta);
				delta -= 1;
				renderOK = true;
			}
			
			if(renderOK) {
				fps++;
				draw();
			}
			
			if(System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println("frames: " + fps + " | updates: " + ups);
				fps = 0;
				ups = 0;
			}
			
		}

	}

	private void startEngine(){
		
		executor = Executors.newScheduledThreadPool(2);

		Runnable updateEngine = new Runnable() {
			public void run() {
				update(System.currentTimeMillis());
			}
		};
		
		Runnable drawEngine = new Runnable() {
			public void run() {
				draw();
			}
		};
		
		executor.scheduleAtFixedRate(updateEngine, 0, updateDelay, TimeUnit.MILLISECONDS);
		executor.scheduleAtFixedRate(drawEngine, 0, drawDelay, TimeUnit.MILLISECONDS);

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

	public void update(long now){

		GUIEvent event = GUIEvent.NONE;
		
		core.update(now);

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
		loaders.add(loader);
	}

	protected void hideCursor() {
		core.hideCursor();
	}

	public void setMainApplication(Application application){
		this.application = application;
	}	

}