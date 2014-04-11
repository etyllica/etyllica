package examples.etyllica.gui;

import examples.etyllica.gui.applications.DarknessUI;
import examples.etyllica.gui.applications.ThemeApplication;
import br.com.etyllica.EtyllicaFrame;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.Configuration;

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
		
		Configuration.getInstance().setTimerClick(true);
	}

	@Override
	public Application startApplication() {
		
		//return new ThemeApplication(w, h);
		return new DarknessUI(w, h);
	}
	
}
