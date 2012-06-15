package br.com.etyllica.camada;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class Bloco extends Camada{

	Map<String, CamadaBuffer> bloco;

	//Se as duas imagens forem iguais?
	//CamadaManipulavel...
	//Ideal seria vetor de tamanho fixo.
	CamadaBuffer[] imagens;
	
	Map<Integer, Vector<String>>script;

	int frameAtual;
	int numeroFrames;
	int loop;

	String dir;

	private Timer timer;
	private int velocidade = 200;

	public Bloco(int x, int y) {
		super(x, y);

		bloco = new HashMap<String, CamadaBuffer>();
		script = new HashMap<Integer,Vector<String>>();

		setScript();
	}
	public void setScript(){
		//openfile
		//readline...
		numeroFrames = 2;
		velocidade = 200;
		dir = "imagens/";

		//bloco.put("star", new CamadaBuffer("star.png"));
		//...
		String getLine = "";

		int indice = 0;

		Vector<String> comandos = new Vector<String>();

		//Precisa melhorar
		if(getLine.startsWith("frame")){

			String linha = getLine.replace(":","").split(" ")[1];
			indice = Integer.parseInt(linha);

			if(indice>1)//Come�ando do frame 1
			{
				script.put(indice, comandos);
				comandos.removeAllElements();
			}
		}
		else{
			if(!getLine.isEmpty())
				comandos.add(getLine);
		}

	}

	public Map<String, CamadaBuffer> getBloco(){
		return bloco;
	}

	class Movimentacao extends TimerTask {
		public void run() {
			preAnima();
			//tymerAnimacao.schedule(new Animacao(), velocidadeAnimacao);
		}
	}
	public void aciona(){
		timer = new Timer();
		timer.scheduleAtFixedRate(new Movimentacao(), velocidade, velocidade);
	}
	
	public Vector<CamadaBuffer> getCamadas(){
		
		return null;
		
	}

	public void preAnima(){

		if(frameAtual < numeroFrames-1){

			frameAtual+= 1;			
		}
		else{

			frameAtual = 0;

			loop++;

		}

		proximoFrame();
	}

	private void proximoFrame(){

		//Come�a do Frame1
		//String line = "move bloco 30 90";

		for(int i=0;i<script.get(frameAtual).size();i++){
			String line = script.get(frameAtual).elementAt(i);

			String[] comando;

			comando = line.split(" ");
			String action = comando[0];

			if(action.equalsIgnoreCase("move")){

				int x = Integer.parseInt(comando[2]);
				int y = Integer.parseInt(comando[3]);

				if(comando[1].equalsIgnoreCase("all")){
					setCoordenadas(x, y);
				}
				else{
					bloco.get(comando[1]).setCoordenadas(x, y);
				}
			}
			else if(action.equalsIgnoreCase("gira")){

				double angulo = Double.parseDouble(comando[2]);

				bloco.get(comando[1]).girar(angulo);
			}
			else if(action.equalsIgnoreCase("reset")){
				bloco.get(comando[1]).resetaImagem();
			}
		}
	}

}
