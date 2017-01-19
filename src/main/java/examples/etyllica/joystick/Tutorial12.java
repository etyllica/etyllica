package examples.etyllica.joystick;

import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.input.JoystickHandler;

public class Tutorial12 extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public Tutorial12() {
		super(800, 600);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../../../../");
		
		//Init searching for 5 joysticks
		JoystickHandler.getInstance().init(5);

		return new JoystickExample(w,h);
	}
	
}
