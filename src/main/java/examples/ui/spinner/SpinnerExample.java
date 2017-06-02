package examples.ui.spinner;

import br.com.etyllica.Etyllica;
import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.core.context.Application;
import examples.ui.spinner.application.SpinnerExampleApplication;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class SpinnerExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public SpinnerExample() {
		super(800, 600);
	}

	public static void main(String[] args){
		SpinnerExample example = new SpinnerExample();
		example.init();
	}

	@Override
	public Application startApplication() {
		return new SpinnerExampleApplication(w,h);
	}
	
}
