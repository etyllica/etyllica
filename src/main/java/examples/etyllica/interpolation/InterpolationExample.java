package examples.etyllica.interpolation;

import java.awt.Color;

import br.com.etyllica.core.animation.AnimationHandler;
import br.com.etyllica.core.animation.script.HorizontalMovement;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.interpolation.Interpolator;
import br.com.etyllica.core.interpolation.LinearInterpolator;
import br.com.etyllica.core.interpolation.QuadraticInterpolator;
import br.com.etyllica.core.interpolation.ReverseQuadraticInterpolator;
import br.com.etyllica.layer.Layer;

public class InterpolationExample extends Application {

	public InterpolationExample(int w, int h) {
		super(w, h);
	}
	
	HorizontalMovement rightLinearScript;
	HorizontalMovement leftLinearScript;
	
	HorizontalMovement rightQuadraticScript;
	HorizontalMovement leftQuadraticScript;
	
	HorizontalMovement rightReverseQuadraticScript;
	HorizontalMovement leftReverseQuadraticScript;
	
	private Layer linearBall;
	private Layer quadraticBall;
	private Layer reverseQuadraticBall;
	
	@Override
	public void load() {
		
		loading = 10;
		
		linearBall = new Layer(40, 80, 30, 30);
		quadraticBall = new Layer(40, 120, 30, 30);
		reverseQuadraticBall = new Layer(40, 160, 30, 30);
				
		leftLinearScript = createLeftScript(linearBall, new LinearInterpolator());
		rightLinearScript = createRightScript(linearBall, new LinearInterpolator());
		rightLinearScript.addNext(leftLinearScript);
		leftLinearScript.addNext(rightLinearScript);
		
		Interpolator quadraticInterpolator = new QuadraticInterpolator();
		
		leftQuadraticScript = createLeftScript(quadraticBall, quadraticInterpolator);
		rightQuadraticScript = createRightScript(quadraticBall, quadraticInterpolator);
		rightQuadraticScript.addNext(leftQuadraticScript);
		leftQuadraticScript.addNext(rightQuadraticScript);
		
		Interpolator reverseQuadraticInterpolator = new ReverseQuadraticInterpolator();
		
		leftReverseQuadraticScript = createLeftScript(reverseQuadraticBall, reverseQuadraticInterpolator);
		rightReverseQuadraticScript = createRightScript(reverseQuadraticBall, reverseQuadraticInterpolator);
		rightReverseQuadraticScript.addNext(leftReverseQuadraticScript);
		leftReverseQuadraticScript.addNext(rightReverseQuadraticScript);
		
		AnimationHandler.getInstance().add(rightLinearScript);
		AnimationHandler.getInstance().add(rightQuadraticScript);
		AnimationHandler.getInstance().add(leftReverseQuadraticScript);
		
		loading = 100;
	}

	protected HorizontalMovement createRightScript(Layer target, Interpolator interpolator) {
		HorizontalMovement rightScript = new HorizontalMovement(target, 3000);
		rightScript.setInterval(40, 640);
		rightScript.setInterpolator(interpolator);
		return rightScript;
	}

	protected HorizontalMovement createLeftScript(Layer target, Interpolator interpolator) {
		HorizontalMovement leftScript = new HorizontalMovement(target, 3000);
		leftScript.setInterval(640, 40);
		leftScript.setInterpolator(interpolator);
		return leftScript;
	}
	
	@Override
	public void draw(Graphic g) {
		drawBall(g, linearBall);
		drawBall(g, quadraticBall);
		drawBall(g, reverseQuadraticBall);
	}

	protected void drawBall(Graphic g, Layer ball) {
		g.setColor(Color.RED);
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