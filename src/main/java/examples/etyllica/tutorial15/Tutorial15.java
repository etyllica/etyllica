package examples.etyllica.tutorial15;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial15 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial15() {
		super(640, 480);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../");
		return new ChatWindowExample(w,h);
	}
	
}
