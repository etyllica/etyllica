package examples.etyllica.tutorial02;

import br.com.etyllica.Etyllica;
import examples.etyllica.tutorial02.application.HelloWorldAnimated;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial2 extends Etyllica{

	private static final long serialVersionUID = 1L;

	public Tutorial2() {
		super(800, 600);
	}
	
	@Override
	public void startGame() {
		
		//Etyllica tries to find the resources as your Application should be
		//To avoid this you should put your /res/images in /Project/bin/examples/etyllica/tutorial2/
		
		//Upping three directories we have /Project/bin/res/images
		String s = getClass().getResource("").toString();
		setPath(s+"../../../");
		
		setMainApplication(new HelloWorldAnimated(w,h));
	}
	
}
