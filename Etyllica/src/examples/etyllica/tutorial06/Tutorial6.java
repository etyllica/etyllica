package examples.etyllica.tutorial06;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;
import examples.etyllica.tutorial06.application.SubWindowExample;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial6 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial6() {
		super(640, 480);
	}
	
	@Override
	public Application startApplication() {
		
		return new SubWindowExample(w,h);
	}
	
}
