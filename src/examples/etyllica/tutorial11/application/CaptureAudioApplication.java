package examples.etyllica.tutorial11.application;

import java.awt.Color;

import javax.sound.sampled.AudioInputStream;

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

	private AudioInputStream audioInputStream;

	private Button stop;
	private Button play;
	
	private boolean canDraw = false;

	private int[][] channels;

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

	}

	public void stopCapture(){
		
		stop.setDisabled(true);
		play.setDisabled(false);
		CaptureHandler.getInstance().stopCapture();
		
		byte[] buffer = CaptureHandler.getInstance().getInputBuffer().toByteArray();
		audioInputStream = CaptureHandler.getInstance().getStream();		
		
		int numChannels = audioInputStream.getFormat().getChannels();
		//int frameLength = (int) audioInputStream.getFrameLength();
		int frameLength = buffer.length;
				
		channels = new int[numChannels][frameLength];

		int sampleIndex = 0;

		for (int t = 0; t < buffer.length;) {
			for (int channel = 0; channel < numChannels; channel++) {

				int low = (int) buffer[t];
				t++;
				int high = (int) buffer[t];
				t++;

				int sample = getSixteenBitSample(high, low);
				channels[channel][sampleIndex] = sample;
			}
			sampleIndex++;
		}

		canDraw = true;
	}

	private int getSixteenBitSample(int high, int low) {
		return (high << 8) + (low & 0x00ff);
	}

	public void captureAudio() {
		stop.setDisabled(false);
		CaptureHandler.getInstance().captureAudio();
	}

	public void playAudio() {
		CaptureHandler.getInstance().playAudio(); 
	}

	@Override
	public void draw(Graphic g) {

		g.setColor(Color.BLACK);

		if(canDraw){

			final int channel = 0;

			int[] samples = channels[channel];

			int xIndex = 0;
			int oldX = 0;
			int oldY = 0;
			int increment = 10;
			int offsetY = 290;
						
			for (int t=0 ; t < samples.length; t += increment) {
				double scaleFactor = 0.1;
				double scaledSample = samples[t]/20 * scaleFactor;
				int y = (int) (15 - (scaledSample));
				g.drawLine(oldX, oldY+offsetY, xIndex, y+offsetY);

				xIndex++;
				oldX = xIndex;
				oldY = y;
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