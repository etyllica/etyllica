package examples.etyllica.gui;

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
		// TODO Auto-generated method stub
		
		return new ThemeApplication(w, h);
	}
	
}
