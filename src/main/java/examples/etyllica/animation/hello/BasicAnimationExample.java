package examples.etyllica.animation.hello;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class BasicAnimationExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public BasicAnimationExample() {
		super(800, 600);
	}

	public static void main(String[] args) {
		BasicAnimationExample app = new BasicAnimationExample();
		app.init();
	}

	@Override
	public Application startApplication() {
		initialSetup("../../");
		return new HelloWorldAnimated(w,h);
	}

}
