package examples.etyllica.fx;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;
import examples.etyllica.fx.application.LightSourceApplication;

public class LightSourceTutorial extends Etyllica {

	private static final long serialVersionUID = 1L;

	public LightSourceTutorial() {
		super(768, 417);//Don't ask
	}

	@Override
	public Application startApplication() {

		return new LightSourceApplication(w,h);
	}

}