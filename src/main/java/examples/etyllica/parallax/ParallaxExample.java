package examples.etyllica.parallax;

import br.com.etyllica.Etyllica;
import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.commons.context.Application;

public class ParallaxExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public ParallaxExample() {
		super(800, 200);
	}

	public static void main(String[] args) {
		ParallaxExample app = new ParallaxExample();
		app.init();
	}

	@Override
	public Application startApplication() {
		//initialSetup("../../");
		return new ParallaxApplication(w, h);
	}
	
}
