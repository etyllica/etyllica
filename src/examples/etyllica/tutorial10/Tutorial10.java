package examples.etyllica.tutorial10;

import br.com.etyllica.Etyllica;
import examples.etyllica.tutorial10.application.SpinnerExample;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial10 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial10() {
		super(800, 600);
	}
	
	@Override
	public void startGame() {
		setMainApplication(new SpinnerExample(w,h));
	}
	
}
