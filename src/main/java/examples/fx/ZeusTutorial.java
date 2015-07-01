package examples.fx;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;
import examples.fx.application.LightningApplication;

public class ZeusTutorial extends Etyllica {

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
