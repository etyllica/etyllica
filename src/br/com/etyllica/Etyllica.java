package br.com.etyllica;

import java.applet.Applet;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.MemoryImageSource;
import java.awt.image.VolatileImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.etyllica.core.Core;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.input.mouse.Mouse;
import br.com.etyllica.core.loader.FontLoader;
import br.com.etyllica.core.loader.ImageLoader;
import br.com.etyllica.core.loader.MultimediaLoader;
import br.com.etyllica.core.video.FullScreenWindow;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.effects.GenericFullScreenEffect;
import br.com.etyllica.gui.window.MainWindow;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class Etyllica extends Applet{

	private static final long serialVersionUID = 4588303747276461888L;

	private Core core;
	
	private FullScreenWindow telaCheia = null;
	private boolean fullScreen = false;

	protected int w = 640;
	protected int h = 480;

	//TODO determinar o fps por cada sessao
	private final int UPDATE_DELAY = 40; // 40ms. Implica em 25fps (1000/40) = 25
	private final int ANIMATION_DELAY = 20; // 20ms. Implica em 50fps (1000/20) = 50

	private Application application;

	private VolatileImage volatileImg;

	private MainWindow desktop;

	private Mouse mouse;

	private Graphic grafico;

	//From Luvia
	private ScheduledExecutorService executor;
	
	private String path = "";
	
	protected boolean initAll = false;
	protected boolean initSound = false;
	protected boolean initJoysick = false;

	public Etyllica(int largura, int altura){

		this.w = largura;
		this.h = altura;
		
	}

	public void init() {
		
		defineSize(w,h);
		
		core = new Core();

		String s = getClass().getResource("").toString();
		
		setPath(s);

		grafico = new Graphic(w,h);		
		grafico.setBufferedImage(volatileImg.getSnapshot());
				
		mouse = core.getControl().getMouse();
		//keyboard = core.getControl().getTeclado();

		desktop = new MainWindow(0,0,w,h);
		
		startGame();		
		
		
		desktop.setApplication(application);	
		core.addWindow(desktop);
		
		hideDefaultCursor();
		mouse.updateArrowTheme();

		this.setFocusTraversalKeysEnabled(false);
		setFocusable(true);
		requestFocus();

		addMouseMotionListener( mouse );
		addMouseWheelListener( mouse );
		addMouseListener( mouse );
		addKeyListener( core.getControl().getTeclado() );

		executor = Executors.newScheduledThreadPool(2);
		startEngine();
		startAnimation();

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
		
		this.path = s;
		
		initLoaders();
				
	}
	
	private void initLoaders(){

		initDefault();
		
		if(initAll||initSound){
			initSound();
		}
		
		if(initAll||initJoysick){
			initJoystick();
		}
		
	}
	
	protected void initDefault(){
		ImageLoader.getInstance().setUrl(path);
		FontLoader.getInstance().setUrl(path);
	}
	
	protected void initSound(){
		MultimediaLoader.getInstance().setUrl(path);
	}
	
	protected void initJoystick(){
		core.initJoystick();
	}

	public abstract void startGame();

	@Override
	public void paint( Graphics g ) {

		//GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		//GraphicsConfiguration gc = ge.getDefaultScreenDevice().getDefaultConfiguration();
		GraphicsConfiguration gc = this.getGraphicsConfiguration();
		int valCode = volatileImg.validate(gc);

		// This means the device doesn't match up to this hardware accelerated image.
		if(valCode==VolatileImage.IMAGE_INCOMPATIBLE){
			volatileImg = createBackBuffer(w,h); // recreate the hardware accelerated image.
			//grafico.setBimg(volatileImg.getSnapshot());
		}

		core.draw(grafico);

		//volatileImg.getGraphics().drawImage(desktop.getApplication().getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), this);
		//volatileImg.getGraphics().drawImage(grafico.getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), this);

		if(!fullScreen){
			g.drawImage(grafico.getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), this);
		}
		else{
			if(telaCheia!=null){
				//telaCheia.desenha(volatileImg.getSnapshot());
				telaCheia.draw(grafico.getBimg());
			}
		}
		
		g.dispose();

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
			enableFullScreen();
		}else if(event==GUIEvent.DISABLE_FULL_SCREEN){
			disableFullScreen();

			//TODO When Frame
		//}else if(event==GUIEvent.WINDOW_MOVE){
		//	setLocation(this.getX()+(mouse.getX()-mouse.getDragX()), this.getY()+(mouse.getY()-mouse.getDragY()));
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
	
	public void setFullScreen(boolean fullscreen){

		if(fullscreen){
			enableFullScreen();
		}else{
			disableFullScreen();
		}

	}

	private void enableFullScreen(){

		if(!fullScreen){
			fullScreen = true;

			telaCheia = new FullScreenWindow(core);
			
			core.addEffect(new GenericFullScreenEffect(0, 0, w, h));
		}
	}

	private void disableFullScreen(){
		if(fullScreen){
			fullScreen = false;

			telaCheia.dispose();
			telaCheia = null;
		}
	}

}