package examples.etyllica.joystick;

import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.loader.JoystickLoader;

public class Tutorial12 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial12() {
		super(800, 600);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../../../../");
		
		//Init searching for 5 joysticks
		JoystickLoader.getInstance().init(5);

		return new JoystickExample(w,h);
	}
	
}
