package examples.etyllica.tutorial8.application;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.util.SVGColor;

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

		final ScheduledExecutorService loadSimulator = Executors.newSingleThreadScheduledExecutor();

		loadSimulator.scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {
				loading+=3;

				if(loading<30){

					loadingPhrase = "Loading Nothing...";

				}else if(loading<50){

					loadingPhrase = "Loading Something...";

				}else if(loading<90){

					loadingPhrase = "Almost Loaded...";

				}else if(loading>=100){
					loading = 100;
					loadSimulator.shutdown();
				}

			}

		}, 50, 50, TimeUnit.MILLISECONDS);	
	}

	@Override
	public void draw(Grafico g) {

		//Set Background Color
		g.setColor(SVGColor.ORANGE_RED);

		//Draw Background
		g.getGraphics().fillRect(0, 0, w, h);

		g.setColor(SVGColor.FOREST_GREEN);

		//Write at center with shadow
		g.drawStringShadowX(100, "Bye World!");

		//Write at center with shadow
		g.drawStringShadowX(300, "Press <BACK SPACE> to go back.");
	}

	@Override
	public GUIEvent updateKeyboard( KeyboardEvent event) {

		if(event.getPressed(Tecla.TSK_BACK_SPACE)){
			returnApplication = new CustomLoadApplication(w,h);
		}

		return GUIEvent	.NONE;
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent	.NONE;
	}

}
