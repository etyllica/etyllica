package examples.etyllica.tutorial15;

import br.com.etyllica.Etyllica;

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
