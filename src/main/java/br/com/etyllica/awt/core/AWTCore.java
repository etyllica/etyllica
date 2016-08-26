package br.com.etyllica.awt.core;

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
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ComponentEvent;
import java.awt.image.MemoryImageSource;
import java.awt.image.VolatileImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.etyllica.awt.AWTGraphics;
import br.com.etyllica.awt.FullScreenWindow;
import br.com.etyllica.awt.core.input.AWTKeyboard;
import br.com.etyllica.core.GameCore;
import br.com.etyllica.core.InnerCore;
import br.com.etyllica.core.collision.CollisionDetector;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.context.Context;
import br.com.etyllica.core.context.Session;
import br.com.etyllica.core.engine.Engine;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.graphics.Monitor;
import br.com.etyllica.core.loop.FrameSkippingLoop;
import br.com.etyllica.core.loop.GameLoop;
import br.com.etyllica.effects.GenericFullScreenEffect;
import br.com.etyllica.gui.Window;
import br.com.etyllica.loader.Loader;
import br.com.etyllica.util.io.IOHelper;

public class AWTCore extends InnerCore implements Runnable, GameCore, java.awt.event.ComponentListener, DropTargetListener {

	private int width;
	private int height;

	private Set<Loader> loaders;

	private GraphicsConfiguration configuration;

	private java.awt.Component component;

	private Window window;

	private VolatileImage volatileImage;

	private String path = "";

	private AWTGraphics graphic;

	private FullScreenWindow fullScreen = null;

	private Engine engine;

	private boolean running = true;

	private GameLoop gameLoop;

	private boolean locked = false;

	private DropTarget dropTarget;

	public AWTCore(Component component, int width, int height) {
		super(width, height);

		this.component = component;

		this.configuration = component.getGraphicsConfiguration();

		this.width = width;
		this.height = height;

		initGraphics(width, height);

		//Core methods
		initMonitors(width, height);
		moveToCenter();

		window = new Window(component.getX(), component.getY(), width, height);
		window.setComponent(component);

		gameLoop = new FrameSkippingLoop(this);

		dropTarget = new DropTarget(component, this);
	}

	public void initMonitors(int width, int height) {

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] devices = ge.getScreenDevices();

		if(devices.length > 0) {

			for (int i = 0; i < devices.length; i++) {

				Rectangle gcBounds = devices[i].getDefaultConfiguration().getBounds();

				int x = gcBounds.x;
				int y = gcBounds.y;
				int w = gcBounds.width;
				int h = gcBounds.height;

				monitors.add(new Monitor(x, y, w, h));
			}

		} else {
			monitors.add(new Monitor(0, 0, width, height));
		}
	}

	public void moveToCenter() {	
		Monitor firstMonitor = monitors.get(0);
		int x = firstMonitor.getW()/2-component.getWidth()/2;
		int y = firstMonitor.getH()/2-component.getHeight()/2;

		component.setLocation(x, y);
	}

	public void setSession(Session session) {
		window.setSessionMap(session);
	}

	private void initGraphics(int width, int height) {

		locked = true;

		this.graphic = new AWTGraphics(width, height);

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

		Monitor selectedMonitor = monitors.get(0);

		Point p = this.component.getLocation();

		for(Monitor monitor: monitors) {

			if(CollisionDetector.colideRectPoint(monitor, p.x, p.y)) {
				selectedMonitor = monitor;
			}
		}

		if(!isFullScreenEnable()) {
			fullScreen = new FullScreenWindow(this, selectedMonitor);
			setFullScreenEnable(true);
		}

		addEffect(new GenericFullScreenEffect(0, 0, this.width, height));
	}

	public void disableFullScreen() {
		fullScreen.dispose();

		setFullScreenEnable(false);
	}

	public void startCore(Application application) {

		this.window.setApplication(application);
		replaceWindow(window);

		component.setFocusTraversalKeysEnabled(false);
		component.setFocusable(true);
		component.requestFocus();

		hideDefaultCursor(component);

		component.addMouseMotionListener( getMouse() );
		component.addMouseWheelListener( getMouse() );
		component.addMouseListener( getMouse() );

		component.addKeyListener( (AWTKeyboard)getKeyboard() );
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

	public static void hideDefaultCursor(Component component) {

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

		draw(graphic);

		//volatileImg.getGraphics().drawImage(desktop.getApplication().getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), this);
		//volatileImg.getGraphics().drawImage(grafico.getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), this);

		if(graphic.getVimg() == null)
			return;

		if(!isFullScreenEnable()) {
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

	public void update(double delta) throws Exception {

		long now = System.currentTimeMillis();

		update(now);

		updateEngine(delta);
	}

	public void render() {
		engine.draw();
	}

	@Override
	public void hideCursor() {
		super.hideCursor();
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public void startEngine() {
		component.setVisible(true);
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(this);
	}

	@Override
	public void run() {
		try {
			gameLoop.loop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateEngine(double delta) {
		GUIEvent event = getSuperEvent();
		engine.updateSuperEvent(event);
	}

	@Override
	public boolean isRunning() {

		return running;
	}

	@Override
	public void setFps(int fps) {
		//System.out.println("frames: " + fps);
		super.setFps(fps);
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
		resizeApplication(width, height);
	}

	@Override
	public void componentShown(ComponentEvent event) {
		// TODO Auto-generated method stub
	}

	//DropTargetListener Methods
	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		Context context = currentContext();
		context.dragEnter();
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragExit(DropTargetEvent dte) {
		Context context = currentContext();
		context.dragExit();
	}

	@Override
	public void drop(DropTargetDropEvent evt) {
		int action = evt.getDropAction();
		evt.acceptDrop(action);
		try {
			Transferable data = evt.getTransferable();
			//DataFlavor flavors[] = data.getTransferDataFlavors();
			if (data.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
				List<File> list = (List<File>) data.getTransferData(DataFlavor.javaFileListFlavor);
				
				Context context = currentContext();
				int mx = (int) evt.getLocation().getX();
				int my = (int) evt.getLocation().getY();
				context.dropFiles(mx, my, list);
			}
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			evt.dropComplete(true);
		}
	}

}
