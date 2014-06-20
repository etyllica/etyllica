package examples.etyllica.tutorial20;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class Tutorial20 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial20() {
		super(800, 600);
	}
	
	@Override
	public Application startApplication() {
		
		//Etyllica tries to find the resources as your Application should be
		//To avoid this you should put your /assets/images in /Project/bin/examples/etyllica/tutorial19/
		
		//Upping three directories we have /Project/bin/assets/images
		String s = getClass().getResource("").toString();
		setPath(s+"../../../");
		
		return new AnimatedDialog(w,h);
	}
	
}
