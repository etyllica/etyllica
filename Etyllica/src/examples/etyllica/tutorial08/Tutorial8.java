package examples.etyllica.tutorial08;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;
import examples.etyllica.tutorial06.application.SubWindowExample;
import examples.etyllica.tutorial08.application.CustomLoadApplication;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial8 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial8() {
		super(800, 600);
	}
	
	@Override
	public Application startApplication() {
				
		return new CustomLoadApplication(w,h);
	}
	
}
