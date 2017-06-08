package examples.ui.material.application;

import java.awt.Font;

import br.com.etyllica.awt.SVGColor;
import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.PointerEvent;
import br.com.etyllica.commons.graphics.Graphics;
import br.com.etyllica.ui.UI;
import br.com.etyllica.ui.label.TextLabel;
import br.com.etyllica.ui.theme.ThemeManager;
import examples.ui.material.application.model.MaterialButton;

public class BlueButtonApplication extends Application {
	
	int bx = 40;
	int by = 130;
	int bw = 140;
	int bh = 50;
	
	MaterialButton button;
	String text = "BUTTON";

	public BlueButtonApplication(int w, int h) {
		super(w,h);
	}

	public void load() {
		ThemeManager.getInstance().getTheme().setShadow(false);
		ThemeManager.getInstance().getTheme().setBaseColor(SVGColor.DEEP_SKY_BLUE);
		ThemeManager.getInstance().getTheme().setTextColor(SVGColor.WHITE);

		TextLabel label = new TextLabel(text);
		label.setFontStyle(Font.BOLD);
		label.setFontSize(16);
		
		button = new MaterialButton(bx, by, bw, bh);
		button.setLabel(label);
		UI.add(button);
	}

	@Override
	public void updateMouse(PointerEvent event) {

	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}

