package examples.etyllica.tutorial03;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial3 extends Etyllica{

	private static final long serialVersionUID = 1L;

	public Tutorial3() {
		super(768, 417);
	}
	
	@Override
	public Application startApplication() {
		
		//Etyllica tries to find the resources as your Application should be
		//To avoid this you should put your /assets/images in /Project/bin/examples/etyllica/tutorial2/
		
		//Upping three directories we have /Project/bin/assets/images
		String s = getClass().getResource("").toString();
		setPath(s+"../../../");
		
		return new StriderAnimation(w,h);
	}
	
}
