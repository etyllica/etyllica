package examples.etyllica.tutorial12;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.loader.JoystickLoader;

public class Tutorial12 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial12() {
		super(800, 600);
		
		addLoader(JoystickLoader.getInstance());
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../../../");
		return new JoystickExample(w,h);
	}
	
}
