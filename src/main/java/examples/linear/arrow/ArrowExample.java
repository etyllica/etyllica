package examples.linear.arrow;

import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;
import examples.linear.arrow.application.ArrowApplication;

public class ArrowExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public ArrowExample() {
		super(640, 480);
	}
	
	public static void main(String[] args){
		ArrowExample example = new ArrowExample();
		example.init();
	}
	
	@Override
	public Application startApplication() {
		return new ArrowApplication(w,h);
	}
	
}
