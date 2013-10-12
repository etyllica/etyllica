package br.com.etyllica;

import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
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
	private final int ANIMATION_DELAY = 20; // 20ms. Implica em 50fps (1000/20) = 50

	private Application application;

	protected Mouse mouse;

	//From Luvia
	private ScheduledExecutorService executor;
	
	protected boolean initAll = false;
	protected boolean initSound = false;
	protected boolean initJoysick = false;

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
		startAnimation();
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	private void initialSetup(){
		
		String s = getClass().getResource("").toString();
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		
		String systemFonts[] = ge.getAvailableFontFamilyNames();
		FontLoader.getInstance().setSystemFonts(systemFonts);
		
		setPath(s);
		
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
	
	private void startAnimation(){
		Runnable animator = new Runnable() {           
            public void run() { 
                core.updateApplication();
            }
		};
		
		executor.scheduleAtFixedRate(animator, ANIMATION_DELAY, ANIMATION_DELAY, TimeUnit.MILLISECONDS);
	}
		
	protected void setPath(String path){
		
		core.setPath(path);
		
		initLoaders();
				
	}
	
	private void initLoaders(){

		core.initDefault();
		
		if(initAll||initSound){
			core.initSound();
		}
		
		if(initAll||initJoysick){
			initJoystick();
		}
		
	}
	
	protected void initJoystick(){
		core.initJoystick();
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
	
	protected void hideCursor() {
		core.hideCursor();
	}

	public void setMainApplication(Application application){
		this.application = application;
	}	

}