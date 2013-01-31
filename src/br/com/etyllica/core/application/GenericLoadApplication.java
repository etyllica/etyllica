package br.com.etyllica.core.application;

import java.awt.Color;
import java.awt.Font;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.Theme;
import br.com.etyllica.core.video.Grafico;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class GenericLoadApplication extends DefaultLoadApplication{

	private Font f;
	private Font p;
	
	public GenericLoadApplication(int x, int y, int w, int h) {
		super(x, y, w, h);
		
		Theme theme = Configuration.getInstance().getTheme();
		
		f = new Font(theme.getFontName(), theme.getFontStyle(), 26);
		p = new Font(theme.getFontName(), theme.getFontStyle(), 18);
	}
	
	private int rectW = 400;
	private int rectX = w/2-rectW/2;
	private int rectY = h/2+100;
	private int rectH = 32;

	@Override
	public void draw(Grafico g) {

		g.setFont(f);
		
		g.setColor(Color.WHITE);
		g.escreveSombraX(320, frase);

		g.drawRect(rectX, rectY, rectW, rectH);
		g.fillRect(rectX+2, rectY+2, ((rectW*fill)/100)-3, rectH-3);
		
		g.setFont(p);
		g.escreveLabelSombra(rectX, rectY, rectW, rectH, porcent, Color.BLACK);
		
	}	
	
	
}
