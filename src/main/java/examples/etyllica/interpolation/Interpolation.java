package examples.etyllica.interpolation;
import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;

public class Interpolation extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Interpolation() {
		super(800, 600);
	}

	public static void main(String[] args){
		Interpolation viewer = new Interpolation();
		viewer.init();
	}
	
	@Override
	public Application startApplication() {
		initialSetup("../../../");

		return new InterpolationExample(w,h);
	}

}

