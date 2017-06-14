package examples.linear.line;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;
import examples.linear.line.application.ElasticLineApplication;

public class LineExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public LineExample() {
		super(640, 480);
	}
	
	public static void main(String[] args){
		LineExample example = new LineExample();
		example.init();
	}
	
	@Override
	public Application startApplication() {
		return new ElasticLineApplication(w,h);
	}
	
}
