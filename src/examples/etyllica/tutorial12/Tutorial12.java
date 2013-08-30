package examples.etyllica.tutorial12;

import br.com.etyllica.Etyllica;
import examples.etyllica.tutorial01.application.HelloWorld;
import examples.etyllica.tutorial12.application.JoystickExample;

public class Tutorial12 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial12() {
		super(800, 600);
	}
	
	@Override
	public void startGame() {
		
		initJoysick = true;
		
		//Etyllica tries to find the resources as your Application should be
		//To avoid this you should put your /res/images in /Project/bin/examples/etyllica/tutorial1/
		
		//Upping three directories we have /Project/bin/res/images
		String s = getClass().getResource("").toString();
		setPath(s+"../../../");
		
		setMainApplication(new JoystickExample(w,h));
	}
	
}
