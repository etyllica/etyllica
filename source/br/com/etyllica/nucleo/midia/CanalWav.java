package br.com.etyllica.nucleo.midia;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class CanalWav extends Thread{ 

	private boolean vazio = true;
	
	private final int EXTERNAL_BUFFER_SIZE = 512*1024; // 128Kb

	private File arquivo;
	
	public CanalWav(File arquivo) {
		this.arquivo = arquivo;
	} 
	
	public boolean isVazio(){
		return vazio;
	}

	public void run(){
		toca(arquivo);
	}

	private AudioInputStream getArquivo(File arquivo){
		AudioInputStream audioInputStream = null;
		
		try { 
			audioInputStream = AudioSystem.getAudioInputStream(arquivo);
		} catch (UnsupportedAudioFileException e1) { 
			e1.printStackTrace();
		} catch (IOException e1) { 
			e1.printStackTrace();
		}
		
		return audioInputStream;
	}
	
	private void toca(File arquivo){
		
		AudioInputStream audioInputStream = getArquivo(arquivo);
		
		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		Info info = new Info(SourceDataLine.class, format);

		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (LineUnavailableException e) { 
			e.printStackTrace();
			return;
		} catch (Exception e) { 
			e.printStackTrace();
			return;
		}  

		auline.start();
		int nBytesRead = 0;
		byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
		
		try { 
			while (nBytesRead != -1) { 
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0){
					auline.write(abData, 0, nBytesRead);
				}
			}
			
		} catch (IOException e) { 
			e.printStackTrace();
			return;
		} finally {
			auline.drain();
			auline.close();
		}	
		
		vazio = false;
		
	}
	
	public void tocaSom(File arquivo){
		this.arquivo = arquivo;
		vazio = false;
		start();
	}

}
