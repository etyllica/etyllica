package br.com.etyllica.core;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.MemoryImageSource;
import java.awt.image.VolatileImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.loader.Loader;
import br.com.etyllica.core.video.FullScreenWindow;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.core.video.Monitor;
import br.com.etyllica.effects.GenericFullScreenEffect;
import br.com.etyllica.gui.window.MainWindow;

public class SharedCore {

	private int width;

	private int height;

	private Set<Loader> loaders;

	private GraphicsConfiguration configuration;

	private java.awt.Component component;

	private MainWindow desktop;

	private VolatileImage volatileImage;

	private String path = "";

	private Graphic graphic;

	private FullScreenWindow telaCheia = null;

	private InnerCore innerCore;

	private boolean drawing = false;

	private List<Monitor> monitors = new ArrayList<Monitor>();

	public SharedCore(java.awt.Component component, int width, int height){
		super();

		this.component = component;

		this.configuration = component.getGraphicsConfiguration();

		this.width = width;
		this.height = height;

		this.graphic = new Graphic(width,height);

		this.desktop = new MainWindow(0,0,width,height);

		defineSize(width, height);

		innerCore = new InnerCore();

		initMonitors();

	}

	private void initMonitors(){

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] devices = ge.getScreenDevices();

		if(devices.length>0){

			for (int i = 0; i < devices.length; i++) {

				Rectangle gcBounds = devices[i].getDefaultConfiguration().getBounds();

				int x = gcBounds.x;

				int y = gcBounds.y;

				int width = gcBounds.width;

				int height = gcBounds.height;

				monitors.add(new Monitor(x,y,width,height));
				
			}
			
		}else{
			
			monitors.add(new Monitor(0,0,width,height));
			
		}

	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {

		//For Windows
		String s = path.replaceAll("%20"," ");

		this.path = s;
	}

	public void initDefault(){

		for(Loader loader:loaders){
			loader.setUrl(path);
			loader.start();
		}

	}

	public void enableFullScreen(){

		Monitor selectedMonitor = monitors.get(0);
		
		Point p = this.component.getLocation();
		
		for(Monitor monitor: monitors){
			
			if(monitor.colideRectPoint(p.x, p.y)){
				selectedMonitor = monitor;
			}
			
		}
		
		telaCheia = new FullScreenWindow(innerCore, selectedMonitor);
		innerCore.addEffect(new GenericFullScreenEffect(0, 0, this.width, height));

		innerCore.fullScreenEnable = true;
	}

	public void disableFullScreen(){
		telaCheia.dispose();

		innerCore.fullScreenEnable = false;
	}

	public void startCore(Application application) {

		this.desktop.setApplication(application);
		innerCore.addWindow(desktop);

		component.setFocusTraversalKeysEnabled(false);
		component.setFocusable(true);
		component.requestFocus();

		hideDefaultCursor();

		component.addMouseMotionListener( innerCore.mouse );
		component.addMouseWheelListener( innerCore.mouse );
		component.addMouseListener( innerCore.mouse );

		component.addKeyListener( innerCore.keyboard );

		innerCore.animator.startAnimation();

	}

	//Component Methods
	private VolatileImage createBackBuffer(int width, int height){
		return createBackBuffer(width, height, Transparency.OPAQUE);
	}

	private VolatileImage createBackBuffer(int width, int height, int transparency){

		return configuration.createCompatibleVolatileImage(width, height, transparency);
	}

	public void defineSize(int width, int height){

		component.setSize(width, height);

		volatileImage = createBackBuffer(width, height);

		if(volatileImage!=null){
			//graphic.setBufferedImage(volatileImage.getSnapshot());
			graphic.setVolatileImage(volatileImage);
		}

	}

	public void validateVolatileImage() {

		int valCode = volatileImage.validate(configuration);

		// This means the device doesn't match up to this hardware accelerated image.
		if(valCode==VolatileImage.IMAGE_INCOMPATIBLE){
			volatileImage = createBackBuffer(width,height); // recreate the hardware accelerated image.
			graphic.setVolatileImage(volatileImage);
		}

	}

	public void hideDefaultCursor(){
		int[] pixels = new int[16 * 16];
		Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				Toolkit.getDefaultToolkit().createImage( new MemoryImageSource(16, 16, pixels, 0, 16))
				, new Point(0, 0), "invisibleCursor");
		component.setCursor( transparentCursor );
		innerCore.drawCursor = true;
	}

	public void paint( Graphics g ) {

		drawing = true;

		//GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		//GraphicsConfiguration gc = ge.getDefaultScreenDevice().getDefaultConfiguration();

		validateVolatileImage();

		innerCore.draw(graphic);

		//volatileImg.getGraphics().drawImage(desktop.getApplication().getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), this);
		//volatileImg.getGraphics().drawImage(grafico.getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), this);

		if(!innerCore.fullScreenEnable){
			g.drawImage(graphic.getBimg(), (int)desktop.getApplication().getX(), (int)desktop.getApplication().getY(), component);
		}
		else{
			telaCheia.draw(graphic.getBimg());
		}

		g.dispose();

		drawing = false;
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

	public void update(){
		if(!drawing){
			innerCore.update(System.currentTimeMillis());
		}
	}

	public GUIEvent getSuperEvent(){
		return innerCore.getSuperEvent();
	}

	public void hideCursor(){
		innerCore.hideCursor();
	}

}
