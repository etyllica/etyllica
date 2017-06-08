package examples.etyllica.tutorial03;

import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.commons.context.Application;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial3 extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public Tutorial3() {
		super(768, 417);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../");
		
		return new StriderAnimation(w,h);
	}
	
}
