package examples.etyllica.swing;

import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.core.context.Application;

public class SwingIntegration extends EtyllicaApplet {

	private static final long serialVersionUID = 1L;

	public SwingIntegration() {
		super(800, 600);
	}

	@Override
	public Application startApplication() {
		initialSetup("../../");
		return new FileExample(w,h);
	}

}
