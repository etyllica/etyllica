package examples.etyllica.basic.mouse;

import br.com.etyllica.Etyllica;
import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.commons.context.Application;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class MouseExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public MouseExample() {
		super(640, 480);
	}

	public static void main(String[] args) {
		MouseExample app = new MouseExample();
		app.init();
	}

	@Override
	public Application startApplication() {
		return new FollowingText(w,h);
	}
	
}
