package examples.etyllica.tutorial06.application;

import java.awt.Color;

import br.com.etyllica.awt.SVGColor;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;

public class CustomLoadApplication extends Application{
	
	public CustomLoadApplication(int w, int h) {
		super(w, h);
		
		loadApplication = new YellowLoading(x, y, w, h);
	}

	//Text Offset
	private int yText = 100;
	private int xText = 0;
	
	//Hold Mouse position
	private float mx = 0;
	private float my = 0;
	
	@Override
	public void load() {
			
		//Simulating Loads
		fakeLoad();
	}
	
	private void fakeLoad(){
				
		while(loading<100){
			
			loading+=3;
			
			if(loading<30){
				
				loadingInfo = "Loading Nothing...";
				
			}else if(loading<50){
				
				loadingInfo = "Loading Something...";
				
			}else if(loading<90){
				
				loadingInfo = "Almost Loaded...";
				
			}else if(loading>=100){
				loading = 100;
				
				return;
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void draw(Graphics g) {

		//Set Color to SVG Crimson
		g.setColor(SVGColor.CRIMSON);		
		//Draw Background
		g.fillRect(0, 0, w, h);
		
		//Set Color to Black
		g.setColor(Color.BLACK);
		//Write HelloWorld!
		g.drawString(xText, yText, "Hello World!");

		g.drawStringX(250, "Click with Mouse to Gain Keyboard Focus.");
		
		g.drawStringX(300, "Press <SPACE> to change the application!");
		
		g.drawStringX(350, "Press <ALT+ENTER> to change to Fullscreen Mode");
		
		//Fill Circle around the Mouse Point
		g.setColor(Color.WHITE);
		g.fillCircle(mx, my, 10);
		
		//Draw Circle around the Mouse Point
		g.setColor(Color.BLACK);
		g.drawCircle(mx, my, 10);
		
	}
	
	@Override
	public void updateKeyboard(KeyEvent event) {
				
		//If Up Arrow is Pressed
		if(event.isKeyDown(KeyEvent.VK_UP)) {
			yText-=10;
		}
		//Else If Down Arrow is Pressed
		else if(event.isKeyDown(KeyEvent.VK_DOWN)) {
			yText+=10;
		}
		
		//If RIGHT Arrow is Pressed
		if(event.isKeyDown(KeyEvent.VK_RIGHT)) {
			xText+=10;
		}
		//If LEFT Arrow is Pressed
		else if(event.isKeyDown(KeyEvent.VK_LEFT)) {
			xText-=10;
		}
		
		if(event.isKeyDown(KeyEvent.VK_ENTER)){
			xText = 0;
			yText = 100;
		}
		
		if(event.isKeyDown(KeyEvent.VK_SPACE)){
			nextApplication = new ByeWorld(w,h);
		}
	}
	
	@Override
	public void updateMouse(PointerEvent event) {
		mx = event.getX();
		my = event.getY();
	}
	
}
