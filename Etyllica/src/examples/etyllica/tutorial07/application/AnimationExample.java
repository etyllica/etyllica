package examples.etyllica.tutorial07.application;

import java.awt.Color;

import br.com.etyllica.animation.scripts.AnimationScript;
import br.com.etyllica.animation.scripts.HorizontalMovement;
import br.com.etyllica.animation.scripts.OpacityAnimation;
import br.com.etyllica.animation.scripts.OrbitAnimation;
import br.com.etyllica.animation.scripts.RotateAnimation;
import br.com.etyllica.animation.scripts.ScaleAnimation;
import br.com.etyllica.animation.scripts.VerticalMovementScript;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.Layer;
import br.com.etyllica.layer.TextLayer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class AnimationExample extends Application{

	public AnimationExample(int w, int h) {
		super(w, h);
	}
	
	private TextLayer text = new TextLayer("Text!");
	
	private TextLayer text2 = new TextLayer(0,400,"Text2!");
	
	private TextLayer text3 = new TextLayer(300,400,"Text3!");
	
	private ImageLayer hello;
	private ImageLayer helloFix;
	
	private Layer layer = new Layer(200,440,40,40);
	int cx = 220, cy = 340;

	@Override
	public void load() {
		
		text.setBorder(true);
		text.setBorderColor(Color.BLACK);
		text.setBorderWidth(3f);
		
		text2.setSize(26);
		text2.setBorder(true);
		text2.setBorderColor(Color.BLACK);
		text2.setBorderWidth(3f);
		
		HorizontalMovement hscript = new HorizontalMovement(text2, 10000);
		hscript.setInterval(400, 10);
		
		HorizontalMovement invscript = new HorizontalMovement(text2, 10000);
		invscript.setInterval(10, 400);
		//After the hscript, execute invscript
		hscript.setNext(invscript);
		
		VerticalMovementScript vscript = new VerticalMovementScript(text2, 600);
		vscript.setInterval(100, 200);
		vscript.setRepeat(AnimationScript.REPEAT_FOREVER);
		
		VerticalMovementScript invVscript = new VerticalMovementScript(600);
		invVscript.setTarget(text2);
		invVscript.setInterval(200, 100);
		vscript.setNext(invVscript);
				
		this.scene.addAnimation(hscript);
		this.scene.addAnimation(vscript);
		
		RotateAnimation rotate = new RotateAnimation(1000,6000);
		rotate.setTarget(text2);
		rotate.setInterval(0, 360);
		this.scene.addAnimation(rotate);
		
		
		OpacityAnimation opacityAnimation = new OpacityAnimation(0,10000);
		opacityAnimation.setTarget(text3);
		opacityAnimation.setInterval(0, 255);
		this.scene.addAnimation(opacityAnimation);
		
		ScaleAnimation scaleAnimation = new ScaleAnimation(0,10000);
		scaleAnimation.setTarget(text3);
		scaleAnimation.setInterval(1, 5);
		this.scene.addAnimation(scaleAnimation);
		
				
		OrbitAnimation orbit = new OrbitAnimation(0,60000);
		orbit.setTarget(layer);
		orbit.setInterval(0, 1080);
		orbit.setCenter(cx, cy);
		
		orbit.setRepeat(AnimationScript.REPEAT_FOREVER);
				
		this.scene.addAnimation(orbit);
		
		
		hello = new ImageLayer(200,100,"hello.png");
		helloFix = new ImageLayer(200,100,"hello.png");
		
		ScaleAnimation scaleHello = new ScaleAnimation(0,10000);
		scaleHello.setTarget(hello);
		scaleHello.setInterval(1, 2);
		this.scene.addAnimation(scaleHello);
		
		RotateAnimation rotateHello = new RotateAnimation(0,10000);
		rotateHello.setTarget(hello);
		rotateHello.setInterval(0, 360);
		this.scene.addAnimation(rotateHello);
		
		
		loading = 100;
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {

		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
						
		return GUIEvent.NONE;
	}
	
	@Override
	public void draw(Graphic g) {
				
		//Drawing background
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x,y,w,h);
		
		text.draw(g);
		
		text2.draw(g);
		
		text3.draw(g);
		
		
		helloFix.draw(g);
		hello.draw(g);
		
		
		g.setColor(Color.BLACK);
		g.fillCircle(cx, cy, 10);
		g.drawCircle(cx, cy, 120);
		
		g.drawLine(cx, cy, layer.getX()+layer.getW()/2, layer.getY()+layer.getH()/2);
		
		g.setColor(Color.BLUE);
		g.drawRect(layer);
		
	}	
	
}
