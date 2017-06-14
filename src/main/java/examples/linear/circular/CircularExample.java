package examples.linear.circular;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;
import examples.linear.circular.application.CircularApplication;

public class CircularExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public CircularExample() {
		super(640, 480);
	}
	
	public static void main(String[] args){
		CircularExample example = new CircularExample();
		example.init();
	}
	
	@Override
	public Application startApplication() {
		return new CircularApplication(w,h);
	}
	
}
