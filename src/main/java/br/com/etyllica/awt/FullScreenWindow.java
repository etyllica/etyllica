package br.com.etyllica.awt;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;

import br.com.etyllica.core.InnerCore;
import br.com.etyllica.core.graphics.Monitor;
import br.com.etyllica.core.input.keyboard.Keyboard;
import br.com.etyllica.core.input.mouse.Mouse;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class FullScreenWindow extends Window{
	
	private static final long serialVersionUID = -5176767672500250086L;
	
	private InnerCore core;
	
	private int w;
	private int h;
	
	private int utilHeight;
	
	private int offsetY = 0;
	
	public FullScreenWindow(InnerCore core, Monitor monitor) {

		super(new Frame());
				
		this.core = core;
		
		//int gnomebar = 20;
		
		//setBounds(0, gnomebar, ss.width, ss.height-gnomebar*2);
		//setBounds(0, 0, ss.width, ss.height-gnomebar*2);
		setBounds((int)monitor.getX(), (int)monitor.getY(), (int)monitor.getW(), (int)monitor.getH());
		
		this.w = (int)monitor.getW();
		this.h = (int)monitor.getH();
		
		//TODO Calcular se eh widescreen
		
		int wfactor = this.w/16;
		
		utilHeight = 9*wfactor;
		
		offsetY = (this.h-utilHeight)/2;
				
		//setLayout(null);

		hideDefaultCursor();
		
		setVisible(true);
		setAlwaysOnTop(true);
		setListeners();
		
	}	
	
	private void setListeners(){
		
		Mouse mouse = core.getControl().getMouse();
		Keyboard keyboard = core.getControl().getKeyboard();
		
		addMouseMotionListener( mouse );
		addMouseWheelListener( mouse );
		addMouseListener( mouse );
		addKeyListener( keyboard );
	}
		
	public void draw(Image volatileImage){
		
		//int w, int h;
		int y = offsetY;
		
		BufferedImage resized = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = resized.createGraphics();
	    
	    //Better Quality
	    //g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    //Fastest (in multicore)
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
	    
	    //g.drawImage(volatileImage, 0, 0, largura, altura, 0, 0, volatileImage.getWidth(), volatileImage.getHeight(), null);
	    g.drawImage(volatileImage, 0, y, w, h, 0, 0, volatileImage.getWidth(null), y+volatileImage.getHeight(null), null);
	    g.dispose();
				
		getGraphics().drawImage(resized,0,0,null);
	}
	
	private void hideDefaultCursor(){
		int[] pixels = new int[16 * 16];
		Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				Toolkit.getDefaultToolkit().createImage( new MemoryImageSource(16, 16, pixels, 0, 16))
				, new Point(0, 0), "invisibleCursor");
		setCursor( transparentCursor );
		
		core.showCursor();
	}
	
}
