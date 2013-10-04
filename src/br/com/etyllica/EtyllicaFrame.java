package br.com.etyllica;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.MemoryImageSource;
import java.awt.image.VolatileImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import br.com.etyllica.core.Core;
import br.com.etyllica.core.SharedCore;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.input.mouse.Mouse;
import br.com.etyllica.core.loader.FontLoader;
import br.com.etyllica.gui.window.MainWindow;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class EtyllicaFrame extends JFrame{

	private static final long serialVersionUID = 4588303747276461888L;
	
	private Core core;
	
	private SharedCore sharedCore;

	protected int w = 640;
	protected int h = 480;

	private final int UPDATE_DELAY = 40; // 40ms. Implica em 25fps (1000/40) = 25
	private final int ANIMATION_DELAY = 20; // 20ms. Implica em 50fps (1000/20) = 50

	private Application application;

	private VolatileImage volatileImg;

	private MainWindow desktop;

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
		
		defineSize(w,h);
		
		initialSetup();

		//MeshLoader.getInstancia().setUrl(s);
		sharedCore = new SharedCore(w,h);
		this.core = sharedCore.getCore();
		
		sharedCore.getGraphic().setBufferedImage(volatileImg.getSnapshot());
		
		desktop = new MainWindow(0,0,w,h);

		mouse = core.getControl().getMouse();

		desktop = new MainWindow(0,0,w,h);

		startGame();		
		
		desktop.setApplication(application);		
		core.addWindow(desktop);
		
		hideDefaultCursor();
		mouse.updateArrowTheme();

		this.setFocusTraversalKeysEnabled(false);
		
		addMouseMotionListener( mouse );
		addMouseWheelListener( mouse );
		addMouseListener( mouse );
		addKeyListener( core.getControl().getKeyboard() );

		executor = Executors.newScheduledThreadPool(2);
		startEngine();
		startAnimation();
		
		setFocusable(true);
		setVisible(true);
		requestFocus();
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
				gerencia();
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
		
		//For Windows
		String s = path.replaceAll("%20"," ");
		
		sharedCore.setPath(s);
		
		initLoaders();
				
	}
	
	private void initLoaders(){

		sharedCore.initDefault();
		
		if(initAll||initSound){
			sharedCore.initSound();
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

		GraphicsConfiguration gc = this.getGraphicsConfiguration();
		int valCode = volatileImg.validate(gc);

		// This means the device doesn't match up to this hardware accelerated image.
		if(valCode==VolatileImage.IMAGE_INCOMPATIBLE){
			volatileImg = createBackBuffer(w,h); // recreate the hardware accelerated image.
		}

		core.draw(sharedCore.getGraphic());

		//volatileImg.getGraphics().drawImage(desktop.getApplication().getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), this);
		//volatileImg.getGraphics().drawImage(grafico.getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), this);

		if(!sharedCore.isFullScreenEnable()){
			g.drawImage(sharedCore.getGraphic().getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), this);
		}
		else{
			sharedCore.drawFullScreen();
		}

	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	private void draw(){
		repaint();
	}

	private void gerencia(){

		GUIEvent event = GUIEvent.NONE;

		core.gerencia();

		event = core.getSuperEvent();

		if(event==GUIEvent.ENABLE_FULL_SCREEN){
			sharedCore.enableFullScreen();
		}else if(event==GUIEvent.DISABLE_FULL_SCREEN){
			sharedCore.disableFullScreen();

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

	public void setMainApplication(Application application){
		this.application = application;
	}
	
	protected void hideCursor(){
		core.hideCursor();
	}
	
	protected void showCursor(){
		core.showCursor();
	}
	
	private void hideDefaultCursor(){
		int[] pixels = new int[16 * 16];
		Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				Toolkit.getDefaultToolkit().createImage( new MemoryImageSource(16, 16, pixels, 0, 16))
				, new Point(0, 0), "invisibleCursor");
		setCursor( transparentCursor );
	}

	private VolatileImage createBackBuffer(int largura, int altura){
		return createBackBuffer(largura, altura, Transparency.OPAQUE);
	}

	private VolatileImage createBackBuffer(int largura, int altura, int transparency){
		GraphicsConfiguration gc = getGraphicsConfiguration();
		return gc.createCompatibleVolatileImage(largura, altura, transparency);
	}

	private void defineSize(int width, int height){

		this.w = width;
		this.h = height;

		setSize(width, height);

		volatileImg = createBackBuffer(width, height);

	}

}