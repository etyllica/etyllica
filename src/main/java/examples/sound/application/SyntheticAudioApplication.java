package examples.sound.application;

import java.awt.Color;

import javax.sound.sampled.AudioFormat;

import sound.capture.AudioHandler;
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
		float sampleRate = 44000;
		int sampleSizeInBits = 8;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = true;
		
		AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		
		waveSamples = new byte[1][(int)sampleRate*4];
		generateWave(0, pitch);
		
		AudioHandler.getInstance().playAudio(waveSamples[0], format);
	}

	private void generateWave(int channel, int pitch) {
		for(int i=0;i<waveSamples[channel].length;i++) {
			byte value = (byte)(-128+(pitch*(i/10))%256);
			waveSamples[0][i] = value;
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