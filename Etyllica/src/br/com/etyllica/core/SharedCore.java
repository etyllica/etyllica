package br.com.etyllica.core;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.MemoryImageSource;
import java.awt.image.VolatileImage;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.loader.FontLoader;
import br.com.etyllica.core.loader.ImageLoader;
import br.com.etyllica.core.loader.MultimediaLoader;
import br.com.etyllica.core.video.FullScreenWindow;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.effects.GenericFullScreenEffect;
import br.com.etyllica.gui.window.MainWindow;

public class SharedCore extends InnerCore{
	
	private int w;
	private int h;

	private java.awt.Component component;
	
	private MainWindow desktop;
	
	private VolatileImage volatileImage;
	
	
	private String path = "";
	
	private Graphic graphic;
		
	private FullScreenWindow telaCheia = null;
	
	private boolean fullScreenEnable;
	

	public SharedCore(java.awt.Component component, int w, int h){
		super();
		this.component = component;

		this.w = w;
		this.h = h;
				
		this.graphic = new Graphic(w,h);
		
		this.desktop = new MainWindow(0,0,w,h);
		
		defineSize(w, h);
		
	}
	
	public Graphic getGraphic() {
		return graphic;
	}

	public void setGraphic(Graphic graphic) {
		this.graphic = graphic;
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
		ImageLoader.getInstance().setUrl(path);
		FontLoader.getInstance().setUrl(path);
	}
	
	public void initSound(){
		MultimediaLoader.getInstance().setUrl(path);
	}
	
	public void enableFullScreen(){
		telaCheia = new FullScreenWindow(this);
		addEffect(new GenericFullScreenEffect(0, 0, w, h));
		
		fullScreenEnable = true;
	}
	
	public void disableFullScreen(){
		telaCheia.dispose();
		telaCheia = null;
		
		fullScreenEnable = false;
	}

	public void drawFullScreen(){
		if(fullScreenEnable){
			telaCheia.draw(graphic.getBimg());
		}
	}

	public boolean isFullScreenEnable() {
		return fullScreenEnable;
	}

	public void startCore(Application application) {
		this.desktop.setApplication(application);
		this.addWindow(desktop);
		
		component.setFocusTraversalKeysEnabled(false);
		component.setFocusable(true);
		component.requestFocus();
		
		hideDefaultCursor();
		mouse.updateArrowTheme();
		
		component.addMouseMotionListener( mouse );
		component.addMouseWheelListener( mouse );
		component.addMouseListener( mouse );
		
		component.addKeyListener( keyboard );
		
	}

	//Component Methods
	private VolatileImage createBackBuffer(int largura, int altura){
		return createBackBuffer(largura, altura, Transparency.OPAQUE);
	}

	private VolatileImage createBackBuffer(int largura, int altura, int transparency){
		GraphicsConfiguration gc = component.getGraphicsConfiguration();
		return gc.createCompatibleVolatileImage(largura, altura, transparency);
	}

	public void defineSize(int width, int height){

		component.setSize(width, height);

		volatileImage = createBackBuffer(width, height);
		
		graphic.setBufferedImage(volatileImage.getSnapshot());

	}

	public void validateVolatileImage() {
		GraphicsConfiguration gc = component.getGraphicsConfiguration();
		int valCode = volatileImage.validate(gc);

		// This means the device doesn't match up to this hardware accelerated image.
		if(valCode==VolatileImage.IMAGE_INCOMPATIBLE){
			volatileImage = createBackBuffer(w,h); // recreate the hardware accelerated image.
			//grafico.setBimg(volatileImg.getSnapshot());
		}
	}
	
	public void hideDefaultCursor(){
		int[] pixels = new int[16 * 16];
		Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				Toolkit.getDefaultToolkit().createImage( new MemoryImageSource(16, 16, pixels, 0, 16))
				, new Point(0, 0), "invisibleCursor");
		component.setCursor( transparentCursor );
	}
	
	public void paint( Graphics g ) {
		
		//GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		//GraphicsConfiguration gc = ge.getDefaultScreenDevice().getDefaultConfiguration();
		
		validateVolatileImage();		

		draw(graphic);

		//volatileImg.getGraphics().drawImage(desktop.getApplication().getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), this);
		//volatileImg.getGraphics().drawImage(grafico.getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), this);

		if(!fullScreenEnable){
			g.drawImage(graphic.getBimg(), desktop.getApplication().getX(), desktop.getApplication().getY(), component);
		}
		else{
			drawFullScreen();
		}
		
		g.dispose();
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
	
}
