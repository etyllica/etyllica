package examples.etyllica.tutorial11;

import java.awt.Color;

import sound.capture.AudioHandler;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.Button;
import br.com.etyllica.gui.label.TextLabel;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class CaptureAudioApplication extends Application {

	private Button stop;
	private Button play;
	
	private boolean canDraw = false;

	private int[][] waveSamples;

	public CaptureAudioApplication(int w, int h) {
		super(w,h);
	}

	public void load(){

		final Button capture = new Button(20,20,200,30);
		capture.setLabel(new TextLabel("Capture"));
		capture.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new Action(this, "captureAudio"));
		this.add(capture);

		stop = new Button(20,60,200,30);
		stop.setLabel(new TextLabel("Stop Capture"));
		stop.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new Action(this, "stopCapture"));
		stop.setDisabled(true);
		this.add(stop);

		play = new Button(20,100,200,30);
		play.setLabel(new TextLabel("Play"));
		play.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new Action(this, "playAudio"));
		play.setDisabled(true);
		this.add(play);

	}

	public void stopCapture(){
		
		stop.setDisabled(true);
		play.setDisabled(false);
		AudioHandler.getInstance().stopCapture();
						
		waveSamples = AudioHandler.getInstance().getWaveformSamples();
		canDraw = true;
	}

	public void captureAudio() {
		stop.setDisabled(false);
		AudioHandler.getInstance().captureAudio();
	}

	public void playAudio() {
		AudioHandler.getInstance().playAudio(); 
	}

	@Override
	public void draw(Graphic g) {

		g.setColor(Color.BLACK);

		if(canDraw){

			//Just Draw the first channel, microphone is mono
			final int channel = 0;

			int[] samples = waveSamples[channel];

			int x = 0;
			int lastX = 0;
			int lastY = 0;
			int increment = 8;
			int offsetY = 290;
						
			for (int t=0 ; t < samples.length; t += increment) {
				double scaleFactor = 0.1;
				double scaledSample = samples[t]/20 * scaleFactor;
				int y = (int) (15 - (scaledSample));
				g.drawLine(lastX, lastY+offsetY, x, y+offsetY);

				x++;
				
				lastX = x;
				lastY = y;
			}
		}
	}	

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

}