package examples.etyllica.tutorial04;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial4 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial4() {
		super(640, 480);
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../../../");
		hideCursor();
		
		return new ProceduralColorChange(w,h);
	}
	
}
