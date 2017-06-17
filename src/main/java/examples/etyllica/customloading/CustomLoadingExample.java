package examples.etyllica.customloading;

import br.com.etyllica.Etyllica;
import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.commons.context.Application;
import examples.etyllica.customloading.application.CustomLoadApplication;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class CustomLoadingExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public CustomLoadingExample() {
		super(800, 600);
	}

	public static void main(String[] args) {
		CustomLoadingExample app = new CustomLoadingExample();
		app.init();
	}
	
	@Override
	public Application startApplication() {
		return new CustomLoadApplication(w,h);
	}
	
}
