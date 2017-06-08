package examples.etyllica.tutorial06;

import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.commons.context.Application;
import examples.etyllica.tutorial06.application.CustomLoadApplication;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial6 extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public Tutorial6() {
		super(800, 600);
	}
	
	@Override
	public Application startApplication() {
		return new CustomLoadApplication(w,h);
	}
	
}
