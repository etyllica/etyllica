package examples.etyllica.gui.slider;


import java.awt.Color;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.ui.UI;
import br.com.etyllica.ui.Slider;

public class SliderExample extends Application {

	Slider slider;
	
	public SliderExample(int w, int h) {
		super(w,h);
	}
		
	@Override
	public void load() {
		slider = new Slider(120, 120, 100, 32);
		slider.setMinValue(0);
		slider.setMaxValue(255);
		UI.add(slider);
		
		loading = 100;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, w, h);
		
		g.setColor(Color.BLACK);
		g.drawString(Float.toString(slider.getValue()), slider.getX()+200, slider.getY()-80);
	}

}
