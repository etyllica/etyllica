package examples.etyllica.tutorial07;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;
import examples.etyllica.tutorial07.application.AnimationExample;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial7 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial7() {
		super(640, 480);
	}

	@Override
	public Application startApplication() {
		initialSetup("../../../../");
		return new AnimationExample(w,h);
	}

}
