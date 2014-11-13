package examples.etyllica.camera;

import examples.etyllica.camera.application.CameraExample;
import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class CinematicsTutorial extends Etyllica {

	private static final long serialVersionUID = 1L;

	public CinematicsTutorial() {
		super(640, 480);
	}

	@Override
	public Application startApplication() {
		initialSetup("../../../../../");

		return new CameraExample(w,h);
	}
	
}