package examples.etyllica.gui.animation;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

public class AnimatedMenuExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public AnimatedMenuExample() {
		super(800, 600);
	}

	public static void main(String[] args) {
		AnimatedMenuExample app = new AnimatedMenuExample();
		app.init();
	}

	@Override
	public Application startApplication() {
		initialSetup("");
		return new AnimatedMenu(w, h);
	}
	
}
