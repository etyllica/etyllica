package examples.etyllica.fx;

import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;

public class EffectsTutorial extends Etyllica {

	private static final long serialVersionUID = 1L;

	public EffectsTutorial() {
		super(800, 600);		
	}

	@Override
	public Application startApplication() {

		//Etyllica tries to find the resources as your Application folder assets/

		//"Upping" three directories we have /Project/bin/assets/images
		String s = getClass().getResource("").toString();
		setPath(s+"../../../");

		//To avoid this, you should put your /assets/images in /Project/bin/examples/etyllica/tutorial1/

		return new LightningApplication(w,h);

	}

}
