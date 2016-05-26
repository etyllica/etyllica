package examples.etyllica.tutorial11;
import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.core.context.Application;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial11 extends EtyllicaApplet{

	private static final long serialVersionUID = 1L;

	public Tutorial11() {
		super(640, 480);
	}

	@Override
	public Application startApplication() {
		initialSetup("../../");
		return new CaptureAudioApplication(w, h);
	}

}
