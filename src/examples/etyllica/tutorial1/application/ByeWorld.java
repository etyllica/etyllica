package examples.etyllica.tutorial1.application;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.util.SVGColor;

public class ByeWorld extends Application{

	@Override
	public void load() {

		for(int i=0;i<99;i++){
			
			//Simulating Loads
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Percentage of Load
			loading = i;
			
			if(loading<30){
				
				//Loading phrase
				loadingPhrase = "Wait...";
				
			}else if(loading<50){
				
				//Loading phrase
				loadingPhrase = "Please Wait...";
				
			}else if(loading<90){
				
				//Loading phrase
				loadingPhrase = "Almost Loaded...";
				
			}
		}

		loading = 100;
	}

	@Override
	public void draw(Grafico g) {

		//Set Background Color
		g.setColor(SVGColor.ORANGE_RED);
		
		//Draw Background
		g.getGraphics().fillRect(0, 0, w, h);

		g.setColor(SVGColor.FOREST_GREEN);
		
		//Write at center with shadow
		g.escreveSombraX(100, "Bye World!");
		
		//Write at center with shadow
		g.escreveSombraX(300, "Press <BACK SPACE> to go back.");
	}

	@Override
	public GUIEvent updateKeyboard( KeyboardEvent event) {

		if(event.getPressed(Tecla.TSK_BACK_SPACE)){
			returnApplication = new HelloWorld();
		}

		return GUIEvent	.NONE;
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}

}
