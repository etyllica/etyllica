package examples.etyllica.gui.chat;

import br.com.etyllica.Etyllica;
import br.com.etyllica.EtyllicaApplet;
import br.com.etyllica.commons.context.Application;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ChatExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public ChatExample() {
		super(640, 480);
	}

	public static void main(String[] args) {
		ChatExample app = new ChatExample();
		app.init();
	}
	
	@Override
	public Application startApplication() {
		return new ChatWindow(w,h);
	}
	
}
