package examples.etyllica.tutorial16;

import br.com.etyllica.Etyllica;

public class Tutorial16 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial16() {
		super(800, 600);
	}
	
	@Override
	public void startGame() {
		
		//Etyllica tries to find the resources as your Application should be
		//To avoid this you should put your /assets/images in /Project/bin/examples/etyllica/tutorial2/
		
		//Upping three directories we have /Project/bin/assets/images
		String s = getClass().getResource("").toString();
		setPath(s+"../../../");
		
		setMainApplication(new ParticleApplication(w,h));
	}
	
}
