package examples.ui.material;

import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;
import examples.ui.material.application.BlueButtonApplication;

public class ButtonExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public ButtonExample() {
		super(640, 480);
	}

	public static void main(String[] args){
		ButtonExample example = new ButtonExample();
		example.init();
	}

	@Override
	public Application startApplication() {
		return new BlueButtonApplication(w,h);
	}

}
