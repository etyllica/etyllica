package examples.etyllica.customloading.application;

import br.com.etyllica.awt.SVGColor;
import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphics;

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
	public void draw(Graphics g) {

		//Set Background Color
		g.setColor(SVGColor.ORANGE_RED);

		//Draw Background
		g.fillRect(0, 0, w, h);

		g.setColor(SVGColor.FOREST_GREEN);

		//Write at center with shadow
		g.drawStringShadowX("Bye World!", 100);

		//Write at center with shadow
		g.drawStringShadowX("Press <BACK SPACE> to go back.", 300);
	}

	@Override
	public void updateKeyboard( KeyEvent event) {
		if(event.isKeyDown(KeyEvent.VK_BACK_SPACE)){
			nextApplication = new CustomLoadApplication(w,h);
		}
	}

}
