package examples.etyllica.gui.resizer;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

public class ResizerExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public ResizerExample() {
		super(800, 600);
	}

	public static void main(String[] args) {
		ResizerExample app = new ResizerExample();
		app.init();
	}

	@Override
	public Application startApplication() {
		//initialSetup("../../");
		return new ResizerApplication(w, h);
	}
	
}
