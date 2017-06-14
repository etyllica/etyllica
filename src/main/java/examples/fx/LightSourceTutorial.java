package examples.fx;

import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.commons.context.Application;
import examples.fx.application.LightSourceApplication;

public class LightSourceTutorial extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public LightSourceTutorial() {
		super(768, 417);//Don't ask
	}

	@Override
	public Application startApplication() {
		initialSetup("../../");

		return new LightSourceApplication(w,h);
	}

}
