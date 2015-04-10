package br.com.etyllica.core;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.ComponentEvent;
import java.awt.image.MemoryImageSource;
import java.awt.image.VolatileImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import br.com.etyllica.collision.CollisionDetector;
import br.com.etyllica.context.Application;
import br.com.etyllica.context.Session;
import br.com.etyllica.core.engine.Engine;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.graphics.FullScreenWindow;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.graphics.Monitor;
import br.com.etyllica.core.loader.Loader;
import br.com.etyllica.core.loop.FrameSkippingLoop;
import br.com.etyllica.core.loop.GameLoop;
import br.com.etyllica.effects.GenericFullScreenEffect;
import br.com.etyllica.gui.Window;
import br.com.etyllica.util.io.IOHelper;

public class SharedCore implements Runnable, GameCore, java.awt.event.ComponentListener {

	private int width;

	private int height;

	private Set<Loader> loaders;

	private GraphicsConfiguration configuration;

	private java.awt.Component component;

	private Window window;

	private VolatileImage volatileImage;

	private String path = "";

	private Graphic graphic;

	private FullScreenWindow fullScreen = null;

	private Engine engine;
	
	private InnerCore innerCore;
		
	private boolean running = true;
	
	private GameLoop gameLoop;
	
	private boolean locked = false;
	
	private Future<?> future;
	
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	
	public SharedCore(Component component, int width, int height) {
		super();

		this.component = component;
		
		this.configuration = component.getGraphicsConfiguration();

		this.width = width;
		this.height = height;
		
		initGraphics(width, height);
		
		innerCore = new InnerCore();

		innerCore.initMonitors(width, height);
		innerCore.moveToCenter(component);
		
		window = new Window(component.getX(), component.getY(), width, height);
		
		gameLoop = new FrameSkippingLoop(this);		
	}
	
	public void setSession(Session session) {
		window.setSessionMap(session);
	}
	
	private void initGraphics(int width, int height) {
		
		locked = true;
		
		this.graphic = new Graphic(width, height);

		defineSize(width, height);
		
		locked = false;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {

		//For Windows
		String s = IOHelper.fixPath(path);

		this.path = s;
	}

	public void initDefault() {

		for(Loader loader:loaders) {
			loader.setUrl(path);
			loader.initLoader();
		}

	}

	public void enableFullScreen() {

		Monitor selectedMonitor = innerCore.getMonitors().get(0);

		Point p = this.component.getLocation();

		for(Monitor monitor: innerCore.getMonitors()) {

			if(CollisionDetector.colideRectPoint(monitor, p.x, p.y)) {
				selectedMonitor = monitor;
			}
		}

		if(!innerCore.fullScreenEnable) {
			fullScreen = new FullScreenWindow(innerCore, selectedMonitor);
			innerCore.fullScreenEnable = true;
		}

		innerCore.addEffect(new GenericFullScreenEffect(0, 0, this.width, height));
	}

	public void disableFullScreen() {
		fullScreen.dispose();

		innerCore.fullScreenEnable = false;
	}

	public void startCore(Application application) {

		this.window.setApplication(application);
		innerCore.replaceWindow(window);

		component.setFocusTraversalKeysEnabled(false);
		component.setFocusable(true);
		component.requestFocus();

		hideDefaultCursor();

		component.addMouseMotionListener( innerCore.mouse );
		component.addMouseWheelListener( innerCore.mouse );
		component.addMouseListener( innerCore.mouse );

		component.addKeyListener( innerCore.keyboard );

	}

	//Component Methods
	private VolatileImage createBackBuffer(int width, int height) {
		return createBackBuffer(width, height, Transparency.OPAQUE);
	}

	private VolatileImage createBackBuffer(int width, int height, int transparency) {

		return configuration.createCompatibleVolatileImage(width, height, transparency);
	}

	public void defineSize(int width, int height) {

		component.setSize(width, height);

		volatileImage = createBackBuffer(width, height);

		if(volatileImage != null) {
			//graphic.setBufferedImage(volatileImage.getSnapshot());
			graphic.setVolatileImage(volatileImage);
		}

	}

	public void validateVolatileImage() {

		int valCode = volatileImage.validate(configuration);

		// This means the device doesn't match up to this hardware accelerated image.
		if(valCode == VolatileImage.IMAGE_INCOMPATIBLE) {
			volatileImage = createBackBuffer(width,height); // recreate the hardware accelerated image.
			graphic.setVolatileImage(volatileImage);
		}

	}

	public void hideDefaultCursor() {
		
		int[] pixels = new int[16 * 16];
		
		Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				Toolkit.getDefaultToolkit().createImage( new MemoryImageSource(16, 16, pixels, 0, 16))
				, new Point(0, 0), "invisibleCursor");
		component.setCursor( transparentCursor );
	}

	public void paint( Graphics g ) {

		if(locked)
			return;
		
		//GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		//GraphicsConfiguration gc = ge.getDefaultScreenDevice().getDefaultConfiguration();

		validateVolatileImage();

		innerCore.draw(graphic);

		//volatileImg.getGraphics().drawImage(desktop.getApplication().getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), this);
		//volatileImg.getGraphics().drawImage(grafico.getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), this);

		if(graphic.getVimg() == null)
			return;
		
		if(!innerCore.fullScreenEnable) {
			g.drawImage(graphic.getVimg(), (int)window.getContext().getX(), (int)window.getContext().getY(), component);
		}
		else{
			fullScreen.draw(graphic.getVimg());
		}

		g.dispose();
	}

	public int getW() {
		return width;
	}

	public void setW(int w) {
		this.width = w;
	}

	public int getH() {
		return height;
	}

	public void setH(int h) {
		this.height = h;
	}

	public void setLoaders(Set<Loader> loaders) {
		this.loaders = loaders;
	}

	public void update(double delta) {
				
		long now = System.currentTimeMillis();

		innerCore.update(now);
			
		updateEngine(delta);
	}
	
	public void render() {
		engine.draw();	
	}
	
	public void hideCursor() {
		innerCore.hideCursor();
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public void startEngine() {
		
		component.setVisible(true);
		
		future = executor.submit(this);
	}
		
	@Override
	public void run() {

		if(!gameLoop.loop()) {
			getError();
		}
	}

	protected void getError() {
		try {
			future.get();
		} catch (ExecutionException e) {
			Throwable cause = e.getCause();
			cause.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
	private void updateEngine(double delta) {

		GUIEvent event = innerCore.getSuperEvent();

		engine.updateSuperEvent(event);
				
	}
	
	@Override
	public boolean isRunning() {
		
		return running;
	}

	@Override
	public void setFps(int fps) {

		//System.out.println("frames: " + fps);
		innerCore.setFps(fps);
	}

	@Override
	public void componentHidden(ComponentEvent event) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void componentMoved(ComponentEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void componentResized(ComponentEvent event) {
		
		Component component = event.getComponent();
		
		Rectangle bounds = component.getBounds();
		
		int width = bounds.width;
		int height = bounds.height;
		
		initGraphics(width, height);
		//System.out.println("Resized: "+bounds.x+", "+bounds.y+", "+bounds.width+", "+bounds.height);
		innerCore.resizeApplication(width, height);
	}

	@Override
	public void componentShown(ComponentEvent event) {
		// TODO Auto-generated method stub
	}

}
