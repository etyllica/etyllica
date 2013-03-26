package br.com.etyllica.effects;

import java.awt.Color;

import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class GenericFullScreenEffect extends GlobalEffect{
	
	private int alpha = 100;
		
	public GenericFullScreenEffect(int x, int y, int w, int h) {
		super(x, y, w, h);
		
		start(35,80);
		
	}
	
	private final int rectW = 360;
	private final int rectH = 50;
	private final int rectX = w/2-rectW/2;
	private final int rectY = h/2-h/4;
	
	@Override
	public void draw(Grafico g) {
		
		g.setAlpha(alpha);
		
		g.setColor(Color.BLACK);
		g.fillArc(rectX-rectH/2, rectY, rectH, rectH,90,180);
		g.fillRect(rectX, rectY, rectW, rectH);
		g.fillArc(rectX+rectW-rectH/2, rectY, rectH, rectH,270,180);
		
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(20f));
		
		//TODO MultiLangLabel
		g.escreveLabelSombra(rectX,rectY,rectW, rectH, "Press ESC to exit fullscreen",Color.BLACK);
		
		g.setAlpha(100);
		
	}
	
	@Override
	protected void updateFX() {

		if(steps>30){
			
			if(alpha>0){
				alpha -= 2;
			}
			//else{System.out.println(steps);}
		}
		
	}	
	
}

