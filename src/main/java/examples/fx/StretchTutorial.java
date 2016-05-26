package examples.fx;

import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.core.context.Application;
import examples.fx.application.StretchApplication;

public class StretchTutorial extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public StretchTutorial() {
		super(800, 600);
	}

	@Override
	public Application startApplication() {
		initialSetup("../../");

		return new StretchApplication(w,h);
	}

}
