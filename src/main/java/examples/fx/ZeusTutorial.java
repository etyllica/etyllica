package examples.fx;

import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.core.context.Application;
import examples.fx.application.LightningApplication;

public class ZeusTutorial extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public ZeusTutorial() {
		super(800, 600);
	}

	@Override
	public Application startApplication() {
		initialSetup("../../");

		return new LightningApplication(w,h);
	}

}
