package examples.etyllica.tutorial17;

import br.com.etyllica.Etyllica;

public class Tutorial17 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial17() {
		super(800, 600);
	}
	
	@Override
	public void startGame() {
		
		//Etyllica tries to find the resources as your Application should be
		//To avoid this you should put your /assets/images in /Project/bin/examples/etyllica/tutorial2/
		
		//Upping three directories we have /Project/bin/assets/images
		String s = getClass().getResource("").toString();
		setPath(s+"../../../");
		
		setMainApplication(new TimedApplication(w,h));
	}
	
}
