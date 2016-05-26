package examples.etyllica.control;

import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;

public class ControlTutorial extends Etyllica {

	private static final long serialVersionUID = 1L;

	public ControlTutorial(int width, int height) {
		super(width, height);
	}
	
	public static void main(String[] args) {
		ControlTutorial example = new ControlTutorial(800, 600);
		example.init();
	}

	@Override
	public Application startApplication() {
		initialSetup("../../");
		
		return new ControllerExample(w, h);
	}

}
