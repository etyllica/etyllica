package examples.sound.application;

import java.awt.Color;

import sound.capture.CaptureHandler;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.graphics.Graphic;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class SyntheticAudioApplication extends Application {

	private int pitch = 10;
	private byte[][] waveSamples;

	public SyntheticAudioApplication(int w, int h) {
		super(w,h);
	}

	public void load() {
		//Mono samples (Just one channel)
		waveSamples = new byte[1][44000];
		generateWave(0, pitch);
		
		CaptureHandler.getInstance().playAudio(waveSamples[0]);
	}

	private void generateWave(int channel, int pitch) {
		for(int i=0;i<waveSamples[channel].length;i++) {
			byte value = (byte)(-128+pitch*i%256);
			waveSamples[0][i] = value;
			System.out.println(value);
		}
	}
	
	private void generateWaveWithInterval(int channel, int pitch) {
		
		int next = 0;
		int spacing = 4800;
		
		for(int i=0;i<waveSamples[channel].length;i++) {
			
			byte value = (byte)(-128+pitch*i%256);
			
			if(next>spacing) {
				value = 0;
			}
			
			if(next>spacing*2) {
				next = 0;
			}
			
			next++;
			
			waveSamples[0][i] = value;
			System.out.println(value);
		}
	}

	@Override
	public void draw(Graphic g) {

		g.setColor(Color.BLACK);

		//Just Draw the first channel, microphone is mono
		final int channel = 0;

		byte[] samples = waveSamples[channel];

		int x = 0;
		int lastX = 0;
		int lastY = 0;
		int increment = 8;
		int offsetY = 290;

		for (int t=0 ; t < samples.length; t += increment) {
			double scaleFactor = 10;
			double scaledSample = samples[t]/20 * scaleFactor;
			int y = (int) (15 - (scaledSample));
			g.drawLine(lastX, lastY+offsetY, x, y+offsetY);

			x++;

			lastX = x;
			lastY = y;
		}
	}	

}