package examples.linear.polygonal;

import examples.linear.polygonal.application.PolygonalArea;
import examples.linear.polygonal.application.PolygonalMultiArea;
import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;

public class PolygonalExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public PolygonalExample() {
		super(640, 480);
	}
	
	public static void main(String[] args){
		PolygonalExample example = new PolygonalExample();
		example.init();
	}
	
	@Override
	public Application startApplication() {
		return new PolygonalMultiArea(w,h);
	}
	
}
