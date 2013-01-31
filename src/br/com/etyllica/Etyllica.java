package br.com.etyllica;

import java.applet.Applet;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.MemoryImageSource;
import java.awt.image.VolatileImage;

import javax.swing.JFrame;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.control.keyboard.Keyboard;
import br.com.etyllica.core.control.mouse.Mouse;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.loader.MultimediaLoader;
import br.com.etyllica.core.loader.FontLoader;
import br.com.etyllica.core.loader.ImageLoader;
import br.com.etyllica.core.video.FullScreenWindow;
import br.com.etyllica.effects.GenericFullScreenEffect;
import br.com.etyllica.gui.Gui;
import br.com.etyllica.gui.window.DesktopWindow;

/**
 * 
 * @author mscythe
 * @license GPLv3
 *
 */

public abstract class Etyllica extends Applet implements Runnable{

	private static final long serialVersionUID = 4588303747276461888L;

	private FullScreenWindow telaCheia = null;
	private boolean fullScreen = false;

	protected int largura = 640;
	protected int altura = 480;

	private boolean isRunning = true;

	//TODO determinar o fps por cada sessao
	private final int FRAME_DELAY = 20; // 20ms. Implica em 50fps (1000/20) = 50
	private final int UPDATE_DELAY = 10; // 20ms. Implica em 50fps (1000/20) = 50

	private Application application;

	private VolatileImage volatileImg;

	private DesktopWindow desktop;
	
	protected Mouse mouse;
	
	protected Keyboard keyboard;
	
	public Etyllica(int largura, int altura){

		this.largura = largura;
		this.altura = altura;

	}

	public void init() {

		//TODO Mudar isso
		//String s = getCodeBase().toString();

		String s = getClass().getResource("").toString();
		//For Windows
		s = s.replaceAll("%20"," ");
		//System.out.println(s);
		
		//TODO load largura e altura from a .ini file

		ImageLoader.getInstance().setUrl(s);
		FontLoader.getInstancia().setUrl(s);
		MultimediaLoader.getInstancia().setUrl(s);

		desktop = new DesktopWindow(0,0,largura,altura);
		Gui.getInstance().setDesktopWindow(desktop);

		mouse = Gui.getInstance().getControl().getMouse();
		keyboard = Gui.getInstance().getControl().getTeclado();

		defineTamanho(largura,altura);
		
		comecaJogo();

		desktop.changeApplication(application);

		escondeCursor();
		
		this.setFocusTraversalKeysEnabled(false);
		setFocusable(true);
		requestFocus();
		
		addMouseMotionListener( mouse );
		addMouseWheelListener( mouse );
		addMouseListener( mouse );
		addKeyListener( keyboard );
		
		Thread t = new Thread(this);
		t.start();

		//gerenciaSistema();
	}

	public abstract void comecaJogo();

	public void setTamanho(int largura, int altura){
		this.largura = largura;
		this.altura = altura;
	}

	/*public void gerenciaSistema(){
		
		Thread t = new Thread(new Runnable() {
			
			public void run() {

				long cycleTime = System.currentTimeMillis();

				while(true){
					gerencia();

					cycleTime = cycleTime + UPDATE_DELAY;
					long difference = cycleTime - System.currentTimeMillis();

					try {
						Thread.sleep(Math.max(0, difference));
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		});
		t.start();
	}*/

	@Override
	public void paint( Graphics g ) {

		GraphicsConfiguration gc = this.getGraphicsConfiguration();
		int valCode = volatileImg.validate(gc);

		// This means the device doesn't match up to this hardware accelerated image.
		if(valCode==VolatileImage.IMAGE_INCOMPATIBLE){
			volatileImg = createBackBuffer(largura,altura); // recreate the hardware accelerated image.
		}

		Gui.getInstance().draw();

		//DesktopWindow desktop = indice.getDesktop();

		volatileImg.getGraphics().drawImage(desktop.getApplication().getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), this);

		if(!fullScreen){
			g.drawImage(volatileImg, 0, 0, this);
		}
		else{
			if(telaCheia!=null){
				telaCheia.desenha(volatileImg);
			}
		}

	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	private void draw(){
		repaint();
	}

	@Override
	public void run() {
		long cycleTime = System.currentTimeMillis();

		while(isRunning) {

			//TODO Separar em duas threads gera problema de modificacao concorrente
			
			keyboard.poll();
			
			draw();
			gerencia();
			

			cycleTime = cycleTime + FRAME_DELAY;
			long difference = cycleTime - System.currentTimeMillis();

			try {
				Thread.sleep(Math.max(0, difference));
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	private GUIEvent gerencia(){

		GUIEvent event = GUIEvent.NONE;

			Gui.getInstance().gerencia();
			
			event = Gui.getInstance().getSuperEvent();
			
			if(event==GUIEvent.ENABLE_FULL_SCREEN){
				enableFullScreen();
			}else if(event==GUIEvent.DISABLE_FULL_SCREEN){
				disableFullScreen();
				
			//TODO When Frame
			}else if(event==GUIEvent.WINDOW_MOVE){
				setLocation(this.getX()+(mouse.getX()-mouse.getDragX()), this.getY()+(mouse.getY()-mouse.getDragY()));
			}
		
			//TODO Request Focus
			/*if(mouse.getPressionado(Mouse.BOTAO_ESQUERDO)||mouse.getPressionado(Mouse.BOTAO_MEIO)||mouse.getPressionado(Mouse.BOTAO_DIREITO)){
				if ( !hasFocus() ) {
				requestFocus();
				}
				//System.gc();
			}*/

		return GUIEvent.NONE;

	}
	
	public void setPrimeiraApplicacao(Application application){
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
		
		this.largura = largura;
		this.altura = altura;

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
			Gui.getInstance().addEffect(new GenericFullScreenEffect(0, 0, largura, altura));
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