package examples.etyllica.tutorial05;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial5 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial5() {
		super(640, 480);
	}
	
	@Override
	public Application startApplication() {
		
		return new FollowingText(w,h);
	}
	
}
