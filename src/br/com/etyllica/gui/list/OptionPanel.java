package br.com.etyllica.gui.list;

import br.com.etyllica.gui.ScrollerPanel;
import br.com.etyllica.gui.panel.TextPanel;

public class OptionPanel extends ScrollerPanel{

	private TextPanel panel;

	public OptionPanel(int x, int y, int w, int h) {
		super(x, y, w, h);

		panel = new TextPanel(0,0,w,h);
		
		setComponent(panel);

	}

	public void addOption(Option option){
		panel.addLine(option.getLabel());
	}

}
