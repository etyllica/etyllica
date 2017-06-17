package examples.etyllica.animation.script;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class AnimationScriptExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public AnimationScriptExample() {
		super(768, 417);
	}

	public static void main(String[] args) {
		AnimationScriptExample app = new AnimationScriptExample();
		app.init();
	}

	@Override
	public Application startApplication() {
		initialSetup("");
		
		return new StriderAnimation(w,h);
	}
	
}
