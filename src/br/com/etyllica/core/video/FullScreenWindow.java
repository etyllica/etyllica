package br.com.etyllica.core.video;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;

import br.com.etyllica.core.Core;
import br.com.etyllica.core.control.keyboard.Keyboard;
import br.com.etyllica.core.control.mouse.Mouse;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class FullScreenWindow extends Window{
	
	private static final long serialVersionUID = -5176767672500250086L;
	
	private int w;
	private int h;
	
	private int utilHeight;
	
	private int offsetY = 0;
	
	public FullScreenWindow() {

		super(new Frame());    

		Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
				
		//int gnomebar = 20;
		
		//setBounds(0, gnomebar, ss.width, ss.height-gnomebar*2);
		//setBounds(0, 0, ss.width, ss.height-gnomebar*2);
		setBounds(0, 0, ss.width, ss.height);
		
		w = ss.width;
		h = ss.height;
		
		//TODO Calcular se eh widescreen
		
		int wfactor = ss.width/16;
		
		utilHeight = 9*wfactor;
		
		offsetY = (ss.height-utilHeight)/2;
				
		//setLayout(null);

		escondeCursor();
		
		setVisible(true);
		setAlwaysOnTop(true);
		setListeners();
		
	}	
	
	private void setListeners(){
		
		Mouse mouse = Core.getInstance().getControl().getMouse();
		Keyboard keyboard = Core.getInstance().getControl().getTeclado();
		
		addMouseMotionListener( mouse );
		addMouseWheelListener( mouse );
		addMouseListener( mouse );
		addKeyListener( keyboard );
	}
		
	protected void escondeCursor(){
		int[] pixels = new int[16 * 16];
		Image image = Toolkit.getDefaultToolkit().createImage( new MemoryImageSource(16, 16, pixels, 0, 16));
		Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "invisibleCursor");
		setCursor( transparentCursor );
	}
	
	public void desenha(BufferedImage bufferedImage){
		
		//int w, int h;
		int y = offsetY;
		
		BufferedImage resized = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = resized.createGraphics();
	    
	    //Better
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    //Fastest (in multicore)
	    //g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
	    
	    //g.drawImage(volatileImage, 0, 0, largura, altura, 0, 0, volatileImage.getWidth(), volatileImage.getHeight(), null);
	    g.drawImage(bufferedImage, 0, y, w, h, 0, 0, bufferedImage.getWidth(), y+bufferedImage.getHeight(), null);
	    g.dispose();
				
		getGraphics().drawImage(resized,0,0,null);
	}

}
