package examples.etyllica.tutorial1.application;

import java.awt.Color;
import java.awt.Font;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.application.DefaultLoadApplication;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.theme.Theme;

public class HelloCustomLoading extends DefaultLoadApplication{

	private Font f;
	private Font p;
		
	public HelloCustomLoading(int x, int y, int w, int h) {
		super(x, y, w, h);
		
		Theme theme = Configuration.getInstance().getTheme();
		
		f = new Font(theme.getFontName(), theme.getFontStyle(), 26);
		p = new Font(theme.getFontName(), theme.getFontStyle(), 18);
	}
	
	private int rectW = w*2/3;
	private int rectX = w/2-rectW/2;
	private int rectY = h/2+100;
	private int rectH = 32;

	private Color backgroundColor = new Color(0xff,0xcc,0x0);
	
	@Override
	public void draw(Grafico g) {

		g.setFont(f);
		
		g.setColor(backgroundColor);
		g.fillRect(0, 0, w, h);
		
		g.setColor(Color.WHITE);
		g.escreveSombraX(280-y, phrase);

		g.drawRect(rectX, rectY, rectW, rectH);
		g.fillRect(rectX+2, rectY+2, ((rectW*fill)/100)-3, rectH-3);
		
		g.setFont(p);
		g.escreveLabelSombra(rectX, rectY, rectW, rectH, percent, Color.BLACK);
		
	}
	
}
