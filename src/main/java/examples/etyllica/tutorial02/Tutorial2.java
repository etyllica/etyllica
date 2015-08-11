package examples.etyllica.tutorial02;

import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial2 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial2() {
		super(800, 600);
	}

	@Override
	public Application startApplication() {
		initialSetup("../../");
		return new HelloWorldAnimated(w,h);
	}

}
