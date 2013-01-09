package br.com.etyllica.nucleo.video;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.VolatileImage;

import br.com.etyllica.nucleo.Gerenciador;


public class TelaCheia extends Window{
	
	private static final long serialVersionUID = -5176767672500250086L;
	
	private int largura;
	private int altura;
	
	public TelaCheia() {

		super(new Frame());    

		Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
		//setBounds(0, 0, ss.width, ss.height);
		
		//int gnomebar = 20;
		
		//setBounds(0, gnomebar, ss.width, ss.height-gnomebar*2);
		//setBounds(0, 0, ss.width, ss.height-gnomebar*2);
		largura = ss.width;
		altura = ss.height;
		
		setBounds(0, 0, largura, altura);
		
		setLayout(null);

		setVisible(true);
		
		Gerenciador grrr = Gerenciador.getInstancia();
		
		addMouseMotionListener( grrr.getControle().getMouse() );
		addMouseListener( grrr.getControle().getMouse() );
		addKeyListener( grrr.getControle().getTeclado() );

		escondeCursor();
	}	
		
	protected void escondeCursor(){
		int[] pixels = new int[16 * 16];
		Image image = Toolkit.getDefaultToolkit().createImage( new MemoryImageSource(16, 16, pixels, 0, 16));
		Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "invisibleCursor");
		setCursor( transparentCursor );
	}
	
	public void desenha(VolatileImage volatileImage){
		Image img = volatileImage.getScaledInstance(largura, altura, BufferedImage.SCALE_FAST);
		
		getGraphics().drawImage(img,0,0,null);
	}

}
