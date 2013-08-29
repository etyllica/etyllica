package examples.etyllica.tutorial11.application;

import java.io.ByteArrayOutputStream;

import sound.capture.CaptureHandler;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIAction;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.Button;
import br.com.etyllica.gui.label.TextLabel;

public class CaptureApplication extends Application {
	
	protected boolean running;
	ByteArrayOutputStream out;

	public CaptureApplication(int w, int h) {
		super(w,h);
	}
	
	public void load(){
		
		final Button capture = new Button(20,20,200,30);
		capture.setLabel(new TextLabel("Capture"));
		capture.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new GUIAction(this, "captureAudio"));
		this.add(capture);
		
		final Button stop = new Button(20,60,200,30);
		stop.setLabel(new TextLabel("Stop"));
		stop.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new GUIAction(this, "stopCapture"));
		this.add(stop);
		
		final Button play = new Button(20,100,200,30);
		play.setLabel(new TextLabel("Play"));
		play.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new GUIAction(this, "playAudio"));
		this.add(play);
				
		loading = 100;
	}

	public void stopCapture(){
		CaptureHandler.getInstance().stopCapture();
	}
	
	public void captureAudio() {
		CaptureHandler.getInstance().captureAudio();
	}

	public void playAudio() {
		CaptureHandler.getInstance().playAudio(); 
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyboardEvent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GUIEvent updateMouse(PointerEvent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Grafico g) {
		// TODO Auto-generated method stub
		
	}

}