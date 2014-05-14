package examples.gui.hud;

import java.awt.Color;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.theme.plurality.Selection;

/**
 * Concept based on Plurality (Movie)
 *  
 * http://www.jamiemartindesign.co.uk/#plurality
 * 
 */

public class PluralityUI extends Application {

	private int mx = 0;

	private int my = 0;

	private int rectW = 210;

	private int rectH = 250;

	private Color backgroundColor = new Color(0x23, 0x27, 0x28);
	
	private Selection selection;

	public PluralityUI(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		selection = new Selection(mx-rectW/2, my-rectH/2, rectW, rectH);
		
		loading = 100;
	}

	@Override
	public void draw(Graphic g) {

		g.setColor(backgroundColor);

		g.fillRect(0, 0, w, h);

		g.drawRect(mx-rectW/2, my-rectH/2, rectW, rectH);
		
		selection.draw(g);
		
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {

		mx = event.getX();

		my = event.getY();
		
		selection.setBounds(mx-rectW/2, my-rectH/2, rectW, rectH);

		return null;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return null;
	}	

}
