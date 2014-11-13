package sound.capture;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class CaptureHandler{

	private boolean recording = false;
	
	private ByteArrayOutputStream inputBuffer;
	
	private static CaptureHandler instance = null;
	
	public static CaptureHandler getInstance() {
		if(instance==null){
			instance = new CaptureHandler();
		}

		return instance;
	}
	
	private TargetDataLine line;
		
	public synchronized void captureAudio(){
		
		try {
			
			final AudioFormat format = getFormat();
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format);
			line.start();
			
			Runnable record = new Runnable() {
				int bufferSize = (int)format.getSampleRate() * format.getFrameSize();
				byte buffer[] = new byte[bufferSize];

				public void run() {
					inputBuffer = new ByteArrayOutputStream();
					recording = true;
					try {
						while (recording) {
							int count = line.read(buffer, 0, buffer.length);
							if (count > 0) {
								inputBuffer.write(buffer, 0, count);
							}
						}
						
						inputBuffer.close();
						
					} catch (IOException e) {
						System.err.println("I/O problems: " + e);
						System.exit(-1);
					}
				}
			};
			
			Thread captureThread = new Thread(record);
			captureThread.start();
			
		} catch (LineUnavailableException e) {
			System.err.println("Line unavailable: " + e);
		}
		
	}
	
	public synchronized void stopCapture(){
		recording = false;
	}
	
	public synchronized void playAudio() {
		
		try {
			
			byte audio[] = inputBuffer.toByteArray();
			InputStream input = new ByteArrayInputStream(audio);
			final AudioFormat format = getFormat();
			final AudioInputStream ais = new AudioInputStream(input, format, audio.length / format.getFrameSize());
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

			final SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(format);
			line.start();

			Runnable runner = new Runnable() {
				
				int bufferSize = (int) format.getSampleRate() * format.getFrameSize();
				byte buffer[] = new byte[bufferSize];

				public void run() {
					try {
						int count;
						
						while ((count = ais.read(buffer, 0, buffer.length)) != -1) {
							
							if (count > 0) {
								//PlayAudio
								line.write(buffer, 0, count);
							}
							
						}
						
						line.drain();
						
						line.close();
						
					} catch (IOException e) {
						
						System.err.println("I/O problems: " + e);
						
					}
				}
				
			};
			
			Thread playThread = new Thread(runner);
			
			playThread.start();
			
		} catch (LineUnavailableException e) {
			
			System.err.println("Line unavailable: " + e);
			
		}
		
	}
	
	public int[][] getWaveformSamples(){
		
		int[][] waveSamples;
		
		byte[] buffer = inputBuffer.toByteArray();
		AudioInputStream audioInputStream = new AudioInputStream(line);	
		
		int numChannels = audioInputStream.getFormat().getChannels();
		//int frameLength = (int) audioInputStream.getFrameLength();
		int frameLength = buffer.length;
				
		waveSamples = new int[numChannels][frameLength];

		int sampleIndex = 0;

		for (int t = 0; t < buffer.length;) {
			for (int channel = 0; channel < numChannels; channel++) {

				int low = (int) buffer[t];
				t++;
				int high = (int) buffer[t];
				t++;

				int sample = getSixteenBitSample(high, low);
				waveSamples[channel][sampleIndex] = sample;
			}
			sampleIndex++;
		}
	
		return waveSamples;
	}
	
	private int getSixteenBitSample(int high, int low) {
		return (high << 8) + (low & 0x00ff);
	}
	
	private AudioFormat getFormat() {
		
		float sampleRate = 8000;
		int sampleSizeInBits = 8;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = true;
		
		AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		
		return format;
	}

	public ByteArrayOutputStream getInputBuffer() {
		return inputBuffer;
	}

	public void setInputBuffer(ByteArrayOutputStream inputBuffer) {
		this.inputBuffer = inputBuffer;
	}
			
}
