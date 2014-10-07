package examples.etyllica.tutorial08.application;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.graphics.SVGColor;

public class ByeWorld extends Application{

	public ByeWorld(int w, int h) {
		super(w, h);
	}

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
	public void draw(Graphic g) {

		//Set Background Color
		g.setColor(SVGColor.ORANGE_RED);

		//Draw Background
		g.fillRect(0, 0, w, h);

		g.setColor(SVGColor.FOREST_GREEN);

		//Write at center with shadow
		g.drawStringShadowX(100, "Bye World!");

		//Write at center with shadow
		g.drawStringShadowX(300, "Press <BACK SPACE> to go back.");
	}

	@Override
	public GUIEvent updateKeyboard( KeyEvent event) {

		if(event.isKeyDown(KeyEvent.TSK_BACK_SPACE)){
			nextApplication = new CustomLoadApplication(w,h);
		}

		return GUIEvent	.NONE;
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}

}
