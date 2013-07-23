package examples.etyllica.tutorial7.application;

import java.awt.Color;

import br.com.etyllica.animation.scripts.HorizontalAnimationScript;
import br.com.etyllica.animation.scripts.OpacityAnimationScript;
import br.com.etyllica.animation.scripts.RotateAnimationScript;
import br.com.etyllica.animation.scripts.ScaleAnimationScript;
import br.com.etyllica.animation.scripts.SpiralAnimationScript;
import br.com.etyllica.animation.scripts.VerticalAnimationScript;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Grafico;
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
		
		HorizontalAnimationScript hscript = new HorizontalAnimationScript(text2, 10000);
		hscript.setInterval(400, 10);
		
		HorizontalAnimationScript invscript = new HorizontalAnimationScript(text2, 10000);
		invscript.setInterval(10, 400);
		//After the hscript, execute invscript
		hscript.setNext(invscript);
		
		VerticalAnimationScript vscript = new VerticalAnimationScript(text2, 600);
		vscript.setInterval(100, 200);
		vscript.setEndless(true);
		
		VerticalAnimationScript invVscript = new VerticalAnimationScript(600);
		invVscript.setTarget(text2);
		invVscript.setInterval(200, 100);
		vscript.setNext(invVscript);
				
		this.animation.add(hscript);
		this.animation.add(vscript);
		
		RotateAnimationScript rotate = new RotateAnimationScript(1000,6000);
		rotate.setTarget(text2);
		rotate.setInterval(0, 360);
		this.animation.add(rotate);
		
		
		OpacityAnimationScript opacityAnimation = new OpacityAnimationScript(0,10000);
		opacityAnimation.setTarget(text3);
		opacityAnimation.setInterval(0, 255);
		this.animation.add(opacityAnimation);
		
		ScaleAnimationScript scaleAnimation = new ScaleAnimationScript(0,10000);
		scaleAnimation.setTarget(text3);
		scaleAnimation.setInterval(1, 5);
		this.animation.add(scaleAnimation);
		
				
		SpiralAnimationScript spiral = new SpiralAnimationScript(0,60000);
		spiral.setTarget(layer);
		spiral.setInterval(0, 1080);
		spiral.setCenter(cx, cy);
		spiral.setEndless(true);
				
		this.animation.add(spiral);
		
		
		hello = new ImageLayer(200,100,"hello.png");
		helloFix = new ImageLayer(200,100,"hello.png");
		
		ScaleAnimationScript scaleHello = new ScaleAnimationScript(0,10000);
		scaleHello.setTarget(hello);
		scaleHello.setInterval(1, 2);
		this.animation.add(scaleHello);
		
		RotateAnimationScript rotateHello = new RotateAnimationScript(0,10000);
		rotateHello.setTarget(hello);
		rotateHello.setInterval(0, 360);
		this.animation.add(rotateHello);
		
		
		loading = 100;
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {

		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
						
		return GUIEvent.NONE;
	}
	
	@Override
	public void draw(Grafico g) {
				
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
