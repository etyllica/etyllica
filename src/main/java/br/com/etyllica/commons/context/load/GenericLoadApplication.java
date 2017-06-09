package br.com.etyllica.commons.context.load;

import java.awt.Color;

import br.com.etyllica.core.graphics.Graphics;

/**
 * 
 * @author yuripourre
 *
 */

public class GenericLoadApplication extends DefaultLoadApplication {
	
	protected float fill = 0;
	
	public GenericLoadApplication(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	private float rectW = w*2/3;
	private float rectX = w/2-rectW/2;
	private float rectY = h/2+100;
	private float rectH = 32;

	private Color backgroundColor = new Color(0x00,0xcc,0xff);
	
	@Override
	public void load() {

	}
	
	@Override
	public void onChangeText(String phrase) {
		this.phrase = phrase;
	}

	@Override
	public void onChangeLoad(float load) {
		this.percent = Integer.toString((int)load)+"%";
		this.fill = load;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(x, y, w, h);
		
		g.setColor(Color.WHITE);
		g.drawStringShadowX(phrase, 280-y);

		g.drawRect(rectX, rectY, rectW, rectH);
		g.fillRect(rectX+2, rectY+2, (int)((rectW*fill)/100)-3, rectH-3);
		
		g.drawStringShadow(percent, rectX, rectY, rectW, rectH, Color.BLACK);
	}
	
}