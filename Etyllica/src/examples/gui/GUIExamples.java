package examples.gui;

import br.com.etyllica.EtyllicaFrame;
import br.com.etyllica.context.Application;
import examples.gui.hud.DarknessUI;
import examples.gui.hud.PluralityUI;

public class GUIExamples extends EtyllicaFrame {

	public GUIExamples(int w, int h) {
		super(w, h);
		// TODO Auto-generated constructor stub
	}
	
	public GUIExamples() {
		super(800,600);
	}

	public static void main(String[] args) {
		
		GUIExamples gui = new GUIExamples();
		
		gui.init();
		
	}

	@Override
	public Application startApplication() {
		
		//return new ThemeApplication(w, h);
		//return new DarknessUI(w, h);
		return new PluralityUI(w, h);
	}
	
}
