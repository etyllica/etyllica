package examples.etyllica.tutorial03;

import br.com.etyllica.Etyllica;
import examples.etyllica.tutorial03.application.ChatWindowExample;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Tutorial3 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial3() {
		super(640, 480);
	}
	
	@Override
	public void startGame() {
		
		setMainApplication(new ChatWindowExample(w,h));
	}
	
}