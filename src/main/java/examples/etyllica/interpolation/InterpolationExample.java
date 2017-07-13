package examples.etyllica.interpolation;

import java.awt.Color;

import br.com.etyllica.core.animation.AnimationModule;
import br.com.etyllica.core.animation.script.HorizontalMovementScript;
import br.com.etyllica.commons.context.Application;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.commons.interpolation.Interpolator;
import br.com.etyllica.commons.interpolation.LinearInterpolator;
import br.com.etyllica.commons.interpolation.QuadraticInterpolator;
import br.com.etyllica.commons.interpolation.RadialInterpolator;
import br.com.etyllica.commons.interpolation.ReverseQuadraticInterpolator;
import br.com.etyllica.commons.layer.Layer;

public class InterpolationExample extends Application {

	public InterpolationExample(int w, int h) {
		super(w, h);
	}
	
	HorizontalMovementScript rightLinearScript;
	HorizontalMovementScript leftLinearScript;
	
	HorizontalMovementScript rightQuadraticScript;
	HorizontalMovementScript leftQuadraticScript;
	
	HorizontalMovementScript rightRadialScript;
	HorizontalMovementScript leftRadialScript;
	
	HorizontalMovementScript rightReverseQuadraticScript;
	HorizontalMovementScript leftReverseQuadraticScript;
		
	private Layer linearBall;
	private Layer quadraticBall;
	private Layer radialBall;
	private Layer reverseQuadraticBall;
	
	@Override
	public void load() {
		
		loading = 10;
		
		linearBall = new Layer(40, 80, 30, 30);
		quadraticBall = new Layer(40, 120, 30, 30);
		radialBall = new Layer(40, 160, 30, 30);
		reverseQuadraticBall = new Layer(40, 200, 30, 30);
				
		leftLinearScript = createLeftScript(linearBall, new LinearInterpolator());
		rightLinearScript = createRightScript(linearBall, new LinearInterpolator());
		rightLinearScript.addNext(leftLinearScript);
		leftLinearScript.addNext(rightLinearScript);
		
		Interpolator quadraticInterpolator = new QuadraticInterpolator();
		
		leftQuadraticScript = createLeftScript(quadraticBall, quadraticInterpolator);
		rightQuadraticScript = createRightScript(quadraticBall, quadraticInterpolator);
		rightQuadraticScript.addNext(leftQuadraticScript);
		leftQuadraticScript.addNext(rightQuadraticScript);
		
		Interpolator radialInterpolator = new RadialInterpolator();
		
		leftRadialScript = createLeftScript(radialBall, radialInterpolator);
		rightRadialScript = createRightScript(radialBall, radialInterpolator);
		rightRadialScript.addNext(leftRadialScript);
		leftRadialScript.addNext(rightRadialScript);
		
		Interpolator reverseQuadraticInterpolator = new ReverseQuadraticInterpolator();
		
		leftReverseQuadraticScript = createLeftScript(reverseQuadraticBall, reverseQuadraticInterpolator);
		rightReverseQuadraticScript = createRightScript(reverseQuadraticBall, reverseQuadraticInterpolator);
		rightReverseQuadraticScript.addNext(leftReverseQuadraticScript);
		leftReverseQuadraticScript.addNext(rightReverseQuadraticScript);

		AnimationModule.getInstance().add(rightLinearScript);
		AnimationModule.getInstance().add(rightQuadraticScript);
		AnimationModule.getInstance().add(rightRadialScript);
		AnimationModule.getInstance().add(rightReverseQuadraticScript);
		
		loading = 100;
	}

	protected HorizontalMovementScript createRightScript(Layer target, Interpolator interpolator) {
		HorizontalMovementScript rightScript = new HorizontalMovementScript(target, 3000);
		rightScript.setInterval(40, 640);
		rightScript.setInterpolator(interpolator);
		return rightScript;
	}

	protected HorizontalMovementScript createLeftScript(Layer target, Interpolator interpolator) {
		HorizontalMovementScript leftScript = new HorizontalMovementScript(target, 3000);
		leftScript.setInterval(640, 40);
		leftScript.setInterpolator(interpolator);
		return leftScript;
	}
	
	@Override
	public void draw(Graphics g) {
		drawBall(g, linearBall);
		drawBall(g, quadraticBall);
		drawBall(g, radialBall);
		drawBall(g, reverseQuadraticBall);
	}

	protected void drawBall(Graphics g, Layer ball) {
		g.setColor(Color.RED);
		g.fillOval(ball.getX(), ball.getY(), ball.getW(), ball.getH());
		g.setColor(Color.BLACK);
		g.drawOval(ball.getX(), ball.getY(), ball.getW(), ball.getH());
	}
		
}