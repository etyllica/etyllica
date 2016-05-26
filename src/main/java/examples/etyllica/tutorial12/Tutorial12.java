package examples.etyllica.tutorial12;
import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.core.context.Application;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial12 extends EtyllicaApplet{

	private static final long serialVersionUID = 1L;

	public Tutorial12() {
		super(640, 480);
	}

	@Override
	public Application startApplication() {
		initialSetup("../../");
		return new TimerApplication(w, h);
	}

}
