package br.com.etyllica.core.application;

import java.awt.Color;

import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class DefaultLoadApplication  extends Application implements LoadApplication{

	public DefaultLoadApplication(int x, int y, int w, int h) {
		super(x,y,w,h);
				
		load();
	}

	protected String phrase = "Loading...";
	
	protected String percent = "0%";

	protected int fill = 0;

	private int bxLimite = 400;
	private int byLimite = 30;

	private int bx = (w/2)-bxLimite/2;

	private int by = 395;

	public void load(){

		by = h-100;
		
		loading = 100;
	}

	@Override
	public void draw(Grafico g) {

		g.setColor(Color.BLACK);
		g.getGraphics().fillRect(x,y,w,h);

		g.setColor(Color.BLACK);
		g.getGraphics().drawRect(bx,by,bxLimite,byLimite);
		g.setColor(Color.WHITE);
		g.getGraphics().drawRect(bx-1,by-1,bxLimite+2,byLimite+2);

		g.setColor(Color.WHITE);
		g.escreveX(100, phrase,true);
			
		//Desenha preenchimento da barra
		g.fillRect(bx+2, by+2, fill*4, byLimite-3);


		g.setColor(Color.WHITE);
		g.escreveX(h-85, percent,true);

	}

	@Override
	public void setText(String phrase, int load){
		
		this.phrase = phrase;
		
		this.percent = Integer.toString(load)+"%";

		this.fill = load;
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {
		return GUIEvent.NONE;
	}

}