package examples.etyllica.fx;

import examples.etyllica.fx.application.LightningApplication;
import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class ZeusTutorial extends Etyllica {

	private static final long serialVersionUID = 1L;

	public ZeusTutorial() {
		super(800, 600);
	}

	@Override
	public Application startApplication() {
		initialSetup("../../../../");

		return new LightningApplication(w,h);
	}

}
