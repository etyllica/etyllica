package examples.gui.hud;

import java.awt.Color;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.theme.plurality.LeftPanel;
import br.com.etyllica.theme.plurality.Selection;
import br.com.etyllica.theme.plurality.TitleArrow;

/**
 * Concept based on Plurality (Movie)
 *  
 * http://www.jamiemartindesign.co.uk/#plurality
 * 
 */

public class PluralityUI extends Application {

	private final static String THEME_NAME = "PLURALITY";
	
	private final static String AUTHOR_NAME = "BY JAMIE MARTIN";
	
	private int mx = 0;

	private int my = 0;

	private int rectW = 210;

	private int rectH = 250;

	private Color backgroundColor = new Color(0x23, 0x27, 0x28);
	
	private TitleArrow title;
	
	private Selection selection;
	
	private LeftPanel leftPanel;
	
	public PluralityUI(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		int tw = 300;
		int th = 60;
		
		title = new TitleArrow(w/2-tw/2, 80, tw, th);
		
		selection = new Selection(mx-rectW/2, my-rectH/2, rectW, rectH);
		
		leftPanel = new LeftPanel(80, 160, 180, 300);
		
		loading = 100;
	}

	@Override
	public void draw(Graphic g) {

		g.setColor(backgroundColor);

		g.fillRect(0, 0, w, h);

		g.drawRect(mx-rectW/2, my-rectH/2, rectW, rectH);
		
		title.draw(g);
		
		selection.draw(g);
		
		leftPanel.draw(g);
		
		g.setColor(Color.WHITE);
		
		int offset = -10;
		
		g.setFontSize(40f);
		
		g.drawStringShadow(title.getX(), title.getY()+offset, title.getW(), title.getH()+offset, THEME_NAME, Color.BLACK);
		
		offset = 14;
		
		g.setFontSize(20f);
		
		g.drawStringShadow(title.getX(), title.getY()+offset, title.getW(), title.getH()+offset, AUTHOR_NAME, Color.BLACK);
		
				
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
