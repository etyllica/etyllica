package examples.etyllica.animation;

import java.awt.Color;

import br.com.etyllica.animation.Animation;
import br.com.etyllica.core.animation.LayerAnimation;
import br.com.etyllica.core.animation.script.FadeInAnimation;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.interpolation.Interpolator;
import br.com.etyllica.layer.Layer;

public class AnimationExample extends Application {

	public AnimationExample(int w, int h) {
		super(w, h);
	}
	
	private Layer fadeBall;
	
	private Layer linearBall;
	private Layer quadraticBall;
	private Layer reverseQuadraticBall;
	
	@Override
	public void load() {
		
		loading = 10;
				
		linearBall = new Layer(40, 80, 30, 30);
		quadraticBall = new Layer(40, 120, 30, 30);
		reverseQuadraticBall = new Layer(40, 160, 30, 30);
		
		fadeBall = new Layer(40, 200, 30, 30);
	
		int duration = 2000;
		
		LayerAnimation linearAnimation = Animation.animate(linearBall).move(duration).from(40, 80).to(480, 80).interpolator(Interpolator.LINEAR_INTERPOLATOR);
		linearAnimation.then(linearAnimation.move(duration).from(480,80).to(40, 80).interpolator(Interpolator.LINEAR_INTERPOLATOR).then(linearAnimation));
		linearAnimation.start();
		
		LayerAnimation quadraticAnimation = Animation.animate(quadraticBall).move(duration).from(40, 120).to(480, 120).interpolator(Interpolator.QUADRATIC_INTERPOLATOR); 
		quadraticAnimation.then(quadraticAnimation.move(duration).from(480,120).to(40, 120).interpolator(Interpolator.QUADRATIC_INTERPOLATOR).then(quadraticAnimation));
		quadraticAnimation.start();
		
		LayerAnimation reverseQuadraticAnimation = Animation.animate(reverseQuadraticBall).move(duration).from(40, 160).to(480, 160).interpolator(Interpolator.REVERSE_QUADRATIC_INTERPOLATOR);
		reverseQuadraticAnimation.then(reverseQuadraticAnimation.move(duration).from(480,160).to(40, 160).interpolator(Interpolator.REVERSE_QUADRATIC_INTERPOLATOR).then(reverseQuadraticAnimation));
		reverseQuadraticAnimation.start();
		
		LayerAnimation fadeAnimation = Animation.animate(fadeBall);
		fadeAnimation.fadeIn().duration(duration).interpolator(Interpolator.LINEAR_INTERPOLATOR);
		fadeAnimation.start();
		
		loading = 100;
	}
	
	@Override
	public void draw(Graphic g) {
		drawBall(g, linearBall);
		drawBall(g, quadraticBall);
		drawBall(g, reverseQuadraticBall);
		
		g.setOpacity(fadeBall.getOpacity());
		drawBall(g, fadeBall);
		g.resetOpacity();
	}

	protected void drawBall(Graphic g, Layer ball) {
		g.setColor(Color.BLUE);
		g.fillOval(ball.getX(), ball.getY(), ball.getW(), ball.getH());
		g.setColor(Color.BLACK);
		g.drawOval(ball.getX(), ball.getY(), ball.getW(), ball.getH());
	}
		
	@Override
	public void update(long now) {
		
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
				
		// TODO Auto-generated method stub
		return null;
	}
		
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		// TODO Auto-generated method stub
		return null;
	}
	
}