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
import br.com.etyllica.core.control.mouse.Mouse;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.loader.FontLoader;
import br.com.etyllica.core.loader.ImageLoader;
import br.com.etyllica.core.loader.MultimediaLoader;
import br.com.etyllica.core.video.FullScreenWindow;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.effects.GenericFullScreenEffect;
import br.com.etyllica.gui.window.DesktopWindow;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public abstract class Etyllica extends Applet implements Runnable{

	private static final long serialVersionUID = 4588303747276461888L;

	private FullScreenWindow telaCheia = null;
	private boolean fullScreen = false;

	protected int w = 640;
	protected int h = 480;

	//TODO determinar o fps por cada sessao
	private final int FRAME_DELAY = 80; // 20ms. Implica em 12fps (1000/80) = 12.5
	private final int UPDATE_DELAY = 40; // 40ms. Implica em 25fps (1000/40) = 25
	//private final int UPDATE_DELAY = 20; // 20ms. Implica em 50fps (1000/20) = 50

	private Application application;

	private VolatileImage volatileImg;

	private DesktopWindow desktop;

	private Mouse mouse;

	//protected Keyboard keyboard;

	private Grafico grafico;

	//From Luvia
	private ScheduledExecutorService executor;

	public Etyllica(int largura, int altura){

		this.w = largura;
		this.h = altura;
		
	}

	public void init() {

		//TODO Mudar isso
		//String s = getCodeBase().toString();

		String s = getClass().getResource("").toString();
		//For Windows
		s = s.replaceAll("%20"," ");
		//System.out.println(s);

		//TODO load largura e altura from a .ini file
		defineTamanho(w,h);

		ImageLoader.getInstance().setUrl(s);
		FontLoader.getInstancia().setUrl(s);
		MultimediaLoader.getInstancia().setUrl(s);

		//MeshLoader.getInstancia().setUrl(s);
		grafico = new Grafico(w,h);		
		grafico.setBufferedImage(volatileImg.getSnapshot());
		desktop = new DesktopWindow(0,0,w,h);
		Core.getInstance().setDesktopWindow(desktop);

		mouse = Core.getInstance().getControl().getMouse();
		//keyboard = Core.getInstance().getControl().getTeclado();

		//defineTamanho(largura,altura);

		startGame();

		desktop.changeApplication(application);

		escondeCursor();

		this.setFocusTraversalKeysEnabled(false);
		setFocusable(true);
		requestFocus();

		addMouseMotionListener( mouse );
		addMouseWheelListener( mouse );
		addMouseListener( mouse );
		addKeyListener( Core.getInstance().getControl().getTeclado() );

		executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(this, UPDATE_DELAY, UPDATE_DELAY, TimeUnit.MILLISECONDS);
		//executor.scheduleAtFixedRate(this, TIME_UPDATE_INTERVAL, TIME_UPDATE_INTERVAL, TimeUnit.MILLISECONDS);

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

		Core.getInstance().draw(grafico);

		//volatileImg.getGraphics().drawImage(desktop.getApplication().getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), this);
		//volatileImg.getGraphics().drawImage(grafico.getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), this);

		if(!fullScreen){
			g.drawImage(grafico.getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), this);
		}
		else{
			if(telaCheia!=null){
				//telaCheia.desenha(volatileImg.getSnapshot());
				telaCheia.desenha(grafico.getBimg());
			}
		}
		
		g.dispose();

	}

	@Override
	public void run() {

		draw();
		gerencia();

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

		Core.getInstance().gerencia();

		event = Core.getInstance().getSuperEvent();

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

	private void escondeCursor(){
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

	private void defineTamanho(int largura, int altura){

		this.w = largura;
		this.h = altura;

		setSize(largura, altura);

		volatileImg = createBackBuffer(largura, altura);

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

			telaCheia = new FullScreenWindow();
			//telaCheia.setGerenciador(indice);
			Core.getInstance().addEffect(new GenericFullScreenEffect(0, 0, w, h));
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