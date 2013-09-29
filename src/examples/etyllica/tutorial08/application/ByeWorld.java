package examples.etyllica.tutorial08.application;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.Key;
import br.com.etyllica.core.video.Graphic;
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
					loadSimulator.shutdownNow();
				}

			}

		}, 50, 50, TimeUnit.MILLISECONDS);	
	}

	@Override
	public void draw(Graphic g) {

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
	public GUIEvent updateKeyboard( KeyEvent event) {

		if(event.onKeyDown(Key.TSK_BACK_SPACE)){
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
