package examples.etyllica.time.timer;
import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class TimerExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public TimerExample() {
		super(640, 480);
	}

	public static void main(String[] args) {
		TimerExample app = new TimerExample();
		app.init();
	}

	@Override
	public Application startApplication() {
		return new TimerApplication(w, h);
	}

}
