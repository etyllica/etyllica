package examples.etyllica.animation.many;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ManyAnimationsExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public ManyAnimationsExample() {
		super(640, 480);
	}

	public static void main(String[] args) {
		ManyAnimationsExample app = new ManyAnimationsExample();
		app.init();
	}

	@Override
	public Application startApplication() {
		return new ManyAnimations(w,h);
	}

}
