package br.com.etyllica.ui.list;

import br.com.etyllica.ui.ScrollView;
import br.com.etyllica.ui.panel.TextPanel;

public class OptionPanel extends ScrollView {

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
