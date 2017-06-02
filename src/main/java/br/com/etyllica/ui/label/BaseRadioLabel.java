package br.com.etyllica.ui.label;

import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.ui.Label;
import br.com.etyllica.ui.theme.Theme;

public class BaseRadioLabel extends Label {

	public BaseRadioLabel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void draw(Graphics g) {
		Theme theme = getTheme();
		
		//DrawShadow
		if(theme.isShadow()) {
			g.setColor(theme.getShadowColor());

			g.fillCircle(w/2, h/2, w/4+1);
		}

		g.setColor(theme.getTextColor());
		g.fillCircle(w/2, h/2, w/4);	
	}
	
}
