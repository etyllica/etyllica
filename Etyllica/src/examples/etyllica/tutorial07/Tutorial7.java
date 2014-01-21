package examples.etyllica.tutorial07;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;
import examples.etyllica.tutorial07.application.AnimationExample;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial7 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial7() {
		super(640, 480);
	}

	@Override
	public Application startApplication() {
	
		//Etyllica tries to find the resources as your Application should be
		//To avoid this you should put your /res/images in /Project/bin/examples/etyllica/tutorial7/

		//Upping three directories we have /Project/bin/res/images
		String s = getClass().getResource("").toString();
		setPath(s+"../../../");

		return new AnimationExample(w,h);
	}

}
