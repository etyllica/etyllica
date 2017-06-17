package examples.etyllica.animation.tween;
import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

public class TweenExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public TweenExample() {
		super(800, 600);
	}

	public static void main(String[] args){
		TweenExample viewer = new TweenExample();
		viewer.init();
	}	
	
	@Override
	public Application startApplication() {
		initialSetup("../");

		return new TweenBall(w,h);
	}

}

