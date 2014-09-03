package examples.etyllica.fx;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class EffectsTutorial extends Etyllica {

	private static final long serialVersionUID = 1L;

	public EffectsTutorial() {
		super(800, 600);		
	}

	@Override
	public Application startApplication() {

		return new LightningApplication(w,h);
	}

}
