package br.com.etyllica.core.application.load;

import java.awt.Color;

import br.com.etyllica.core.application.InternalApplication;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.video.Graphic;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class DefaultLoadApplication extends InternalApplication implements LoadApplication{

	
	public DefaultLoadApplication(int w, int h) {
		super(w,h);
	}
	
	public DefaultLoadApplication(int x, int y, int w, int h) {
		super(x,y,w,h);
	}

	protected String phrase = "Loading...";
	
	protected String percent = "0%";

	protected float fill = 0;

	private int bxLimite = 400;
	private int byLimite = 30;

	private int bx = (w/2)-bxLimite/2;

	private int by = 395;

	public void load(){

		by = h-100;
		
		loading = 100;		
	}

	@Override
	public void draw(Graphic g) {
		
		g.setColor(Color.BLACK);
		g.getGraphics().fillRect(x,y,w,h);

		g.setColor(Color.BLACK);
		g.getGraphics().drawRect(bx,by,bxLimite,byLimite);
		g.setColor(Color.WHITE);
		g.getGraphics().drawRect(bx-1,by-1,bxLimite+2,byLimite+2);

		g.setColor(Color.WHITE);
		g.escreveX(100, phrase,true);
			
		//Desenha preenchimento da barra
		g.fillRect(bx+2, by+2, (int)fill*4, byLimite-3);


		g.setColor(Color.WHITE);
		g.escreveX(h-85, percent,true);

	}

	@Override
	public void setText(String phrase, float load){
		
		this.phrase = phrase;
		
		//this.percent = Float.toString(load)+"%";
		this.percent = Integer.toString((int)load)+"%";

		this.fill = load;
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		return GUIEvent.NONE;
	}

}