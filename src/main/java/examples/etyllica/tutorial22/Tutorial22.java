package examples.etyllica.tutorial22;

import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.commons.context.Application;

public class Tutorial22 extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public Tutorial22() {
		super(800, 600);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../");
		return new SplitCameraApplication(w, h);
	}
	
}
