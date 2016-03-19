package examples.ui.material.application;

import java.awt.Font;

import br.com.etyllica.awt.ColorHelper;
import br.com.etyllica.awt.SVGColor;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;

public class BlueButtonApplication extends Application {

	int bx = 40;
	int by = 130;
	int bw = 140;
	int bh = 50;
	int radius = 6;
	
	String label = "BUTTON";

	public BlueButtonApplication(int w, int h) {
		super(w,h);
	}

	public void load() {

	}

	public void draw(Graphic g) {

		g.setFont(g.getFont().deriveFont(Font.BOLD));
		g.setFontSize(16f);

		//Draw diffuse Button
		g.setColor(ColorHelper.darker(SVGColor.DEEP_SKY_BLUE, 8));
		g.fillRoundRect(bx, by+bh-radius*2, bw, radius*2+3, radius, radius);

		//Draw bottom effect
		g.setColor(SVGColor.DEEP_SKY_BLUE);
		g.fillRoundRect(bx, by, bw, bh, radius, radius);

		//Draw Label
		g.setColor(SVGColor.WHITE);
		g.drawString(bx, by, bw, bh, label);

		//Draw Shadow
		g.setAlpha(80);
		g.setColor(SVGColor.GRAY);
		g.drawLine(bx+radius/4, by+bh+3, bx+bw-radius/2, by+bh+3);
		
		g.setAlpha(30);
		g.setColor(SVGColor.GRAY);
		g.drawLine(bx+radius/4-1, by+bh+3+1, bx+bw-radius/2-2, by+bh+3+1);
		g.drawLine(bx-1, by+radius/4, bx-1, by+bh-radius/2);
		g.drawLine(bx+bw-1, by+radius/4, bx+bw-1, by+bh-radius/2);
		
		g.setAlpha(100);
	}

	@Override
	public void updateMouse(PointerEvent event) {

	}
}

