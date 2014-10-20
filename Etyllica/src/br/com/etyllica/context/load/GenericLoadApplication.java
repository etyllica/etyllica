package br.com.etyllica.context.load;

import java.awt.Color;
import java.awt.Font;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.theme.Theme;
import br.com.etyllica.theme.ThemeManager;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class GenericLoadApplication extends DefaultLoadApplication{

	private Font f;
	private Font p;
	
	protected float fill = 0;
	
	public GenericLoadApplication(int x, int y, int w, int h) {
		super(x, y, w, h);
		
		Theme theme = ThemeManager.getInstance().getTheme();
		
		f = new Font(theme.getFontName(), theme.getFontStyle(), 26);
		p = new Font(theme.getFontName(), theme.getFontStyle(), 18);
	}
	
	private float rectW = w*2/3;
	private float rectX = w/2-rectW/2;
	private float rectY = h/2+100;
	private float rectH = 32;

	private Color backgroundColor = new Color(0x00,0xcc,0xff);
	
	@Override
	public void draw(Graphic g) {

		g.setFont(f);
		
		g.setColor(backgroundColor);
		g.fillRect(0, 0, w, h);
		
		g.setColor(Color.WHITE);
		g.drawStringShadowX(280-y, phrase);

		g.drawRect(rectX, rectY, rectW, rectH);
		g.fillRect(rectX+2, rectY+2, (int)((rectW*fill)/100)-3, rectH-3);
		
		g.setFont(p);
		g.drawStringShadow(rectX, rectY, rectW, rectH, percent, Color.BLACK);
		
	}

	@Override
	public void load() {
		
	}

	@Override
	public void setText(String phrase, float load){
		
		this.phrase = phrase;
		
		//this.percent = Float.toString(load)+"%";
		this.percent = Integer.toString((int)load)+"%";

		this.fill = load;
	}
		
}