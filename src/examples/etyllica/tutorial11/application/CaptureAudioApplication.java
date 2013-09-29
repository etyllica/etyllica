package examples.etyllica.tutorial11.application;

import java.awt.Color;
import java.io.ByteArrayOutputStream;

import sound.capture.CaptureHandler;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIAction;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.gui.Button;
import br.com.etyllica.gui.label.TextLabel;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class CaptureAudioApplication extends Application {

	protected boolean running;
	ByteArrayOutputStream out;

	private Button stop;
	private Button play; 

	public CaptureAudioApplication(int w, int h) {
		super(w,h);
	}

	public void load(){

		final Button capture = new Button(20,20,200,30);
		capture.setLabel(new TextLabel("Capture"));
		capture.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new GUIAction(this, "captureAudio"));
		this.add(capture);

		stop = new Button(20,60,200,30);
		stop.setLabel(new TextLabel("Stop Capture"));
		stop.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new GUIAction(this, "stopCapture"));
		stop.setDisabled(true);
		this.add(stop);

		play = new Button(20,100,200,30);
		play.setLabel(new TextLabel("Play"));
		play.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new GUIAction(this, "playAudio"));
		play.setDisabled(true);
		this.add(play);

		loading = 100;
	}

	public void stopCapture(){
		stop.setDisabled(true);
		play.setDisabled(false);
		CaptureHandler.getInstance().stopCapture();
	}

	public void captureAudio() {
		stop.setDisabled(false);
		CaptureHandler.getInstance().captureAudio();
	}

	public void playAudio() {
		CaptureHandler.getInstance().playAudio(); 
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GUIEvent updateMouse(PointerEvent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	int lastLength = 0;
	
	@Override
	public void draw(Graphic g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		
		if(CaptureHandler.getInstance().getInputBuffer()!=null){
			
			byte[] buffer = CaptureHandler.getInstance().getInputBuffer().toByteArray();
			
			for(int i=lastLength;i<buffer.length;i++){
				//System.out.println(buffer[i]);
				g.drawRect(i-lastLength, 200+buffer[i], 1, 1);
			}
			
			lastLength = buffer.length;
		}

	}

}