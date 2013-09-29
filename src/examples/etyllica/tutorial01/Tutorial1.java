package examples.etyllica.tutorial01;

import br.com.etyllica.Etyllica;
import examples.etyllica.tutorial01.application.HelloWorld;

public class Tutorial1 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial1() {
		super(800, 600);
	}
	
	@Override
	public void startGame() {
				
		//Etyllica tries to find the resources as your Application should be
		//To avoid this you should put your /assets/images in /Project/bin/examples/etyllica/tutorial1/
		
		//Upping three directories we have /Project/bin/res/images
		String s = getClass().getResource("").toString();
		setPath(s+"../../../");
		
		setMainApplication(new HelloWorld(w,h));
	}
	
}
