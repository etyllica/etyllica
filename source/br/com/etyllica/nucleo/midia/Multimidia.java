package br.com.etyllica.nucleo.midia;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.Line.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Multimidia{

	private Map<String, File>som = new HashMap<String, File>();
	private Map<String, FileInputStream>musica = new HashMap<String, FileInputStream>();
	
	//Precisa necessariamente ser uma thread
		//Cria um ThreadPlayer
	private Map<String, ThreadPlayer> player = new HashMap<String, ThreadPlayer>();

	//Thread musicaFundo;

	//Toca MP3
	//Parte sonora
	//Requer uma Jukebox com threads

	private Info linfo;
	
	//
	protected int numeroPistas;
	private List<CanalWav> pistas;
	private List<String> sonsQueue;
	
	public Multimidia(int numeroPistas){
		linfo = new Info(Clip.class);
		
		this.numeroPistas = numeroPistas;
		pistas = new ArrayList<CanalWav>();
		
		for(int i=0;i<numeroPistas;i++){
			//TODO Ajeitar isso
			//Controle de slots
			pistas.add(new CanalWav(null));
		}
		
		//Inicializa multipista
	}
	
	//Tamb�m serve para mp3
	public Musica carregaMusica(String caminho){

		//String diretorio = caminho.toLowerCase();
		String diretorio = caminho;

		if(!musica.containsKey(diretorio)){

			FileInputStream arquivo = null;

			try {
				arquivo = new FileInputStream(diretorio);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Arquivo de som: "+caminho+" nao encontrado.");
				return null;
			}
			finally{

				//TODO Associar tambem o tamanho
				musica.put(diretorio,arquivo);

			}

		}

		Musica musica = new Musica(diretorio);

		return musica;

	}


	public void tocaMusica(Musica musica){
		tocaMusica(musica.getCaminho());
	}

	//TODO
	//Preciso testar se Player gera threads sozinho
	//Caso precise novas threads, verificar se precisa um player por thread
	public void tocaMusica(String caminho){

		ThreadPlayer tplayer = player.get(caminho);

		if(tplayer!=null){
			if(tplayer.isTocando()){
				return;
			}
			else{
				player.remove(caminho);
				tocaMusica(caminho);
			}
		}

		FileInputStream fis = musica.get(carregaMusica(caminho).getCaminho());

		BufferedInputStream bis = new BufferedInputStream(fis);

		tplayer = new ThreadPlayer(bis);


		player.put(caminho, tplayer);

		tplayer.start();

	}
	public void paraMusica(String caminho){
		player.get(caminho).para();
	}
	public void paraMusica(Musica musica){
		paraMusica(musica.getCaminho());
	}

	private String pastaSons = "sons/";

	private File carregaSom(String caminho){

		String diretorio = pastaSons+caminho;

		if(!som.containsKey(diretorio)){
			
			File arquivo = new File(diretorio);

			som.put(diretorio, arquivo);
			
		}
		
		return som.get(diretorio);

	}

	public void tocaSom(String caminho){
		
		File arquivo = carregaSom(caminho);
		
		boolean cheio = true;
		for(CanalWav pista: pistas){
			if(pista.isVazio()){
				pista.tocaSom(arquivo);
				cheio = false;
				break;
			}
		}
		
		if(cheio){
			sonsQueue.add(caminho);
		}
		
	}
	
	public void tocaSomOld(String caminho){
		
		File arquivo = carregaSom(caminho);
		
		Line line;
		try {
			
			line = AudioSystem.getLine(linfo);
			Clip clip = (Clip) line;
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(arquivo);
		    clip.open(ais);
		    clip.start();
		    
		    clip.flush();
		    
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}	
	
	private StreamPlayer online = null;

	public void tocaMusicaStream(String caminho) {

		if(online!=null){
			online.stopPlayer();
		}

		if(!caminho.isEmpty()){
			online = new StreamPlayer(caminho);
			online.start();
		}
		
	}

}
