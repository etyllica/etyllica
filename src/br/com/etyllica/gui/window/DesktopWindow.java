package br.com.etyllica.gui.window;

import java.awt.Color;

import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.Window;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class DesktopWindow extends Window{

	public DesktopWindow(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	@Override
	public void draw(Grafico g){
		//g.setBufferedImage(application.getBimg());

		if(application.isClearBeforeDraw()){
			g.setColor(Color.WHITE);

			g.fillRect(0,0,application.getW(),application.getH());
		}
	}

}
