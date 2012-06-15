package br.com.etyllica;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.VolatileImage;

import javax.swing.JApplet;

import br.com.etyllica.nucleo.Gerenciador;
import br.com.etyllica.nucleo.controle.Mouse;
import br.com.etyllica.nucleo.controle.Tecla;
import br.com.etyllica.nucleo.controle.Teclado;
import br.com.etyllica.nucleo.sessao.MiniSessao;
import br.com.etyllica.nucleo.video.TelaCheia;

public abstract class Etyllica extends JApplet implements Runnable{

	private static final long serialVersionUID = 4588303747276461888L;

	private Indice indice;

	private Gerenciador grrr;

	private TelaCheia telaCheia = null;
	private boolean fullScreen = false;

	private int largura = 640;
	private int altura = 480;

	//Controle
	private Mouse mouse;
	private Teclado teclado;

	private boolean isRunning = true;

	private String cursorNormal = "";
	private String cursorClicado = "";
	private String cursorCarregando = "";

	private VolatileImage volatileImg;

	public Etyllica(int largura, int altura){
						
		grrr = Gerenciador.getInstancia(largura, altura);
		
		grrr.setContentPane(getContentPane());

		mouse = grrr.getControle().getMouse();
		teclado = grrr.getControle().getTeclado();
		
		addMouseMotionListener( mouse );
		addMouseListener( mouse );
		addKeyListener( teclado );
		
	}

	public void setPrimeiraSessao(MiniSessao sessao){
		this.indice = new Indice(grrr, sessao);
	}

	public void init() {

		String s = getCodeBase().toString();
		//System.out.println(s);

		grrr.setUrl(s);
		//grrr.iniciaCliente();
		
		defineTamanho(largura,altura);
		

		if(!cursorNormal.isEmpty()){
			carregaCursor();
		}
		
		//requisitaFoco();
		setFocusable(true);

	}

	public void setTamanho(int largura, int altura){
		this.largura = largura;
		this.altura = altura;
		
		grrr.setSize(largura, altura);
	}

	private void desenhaPrincipal(){
		indice.desenha();
		grrr.getContentPane().paintComponents(grrr.getGrafico().getGraphics());
		grrr.getGrafico().desenha(mouse);
	}

	//Gerenciando os eventos
	//Alt+Enter para entrar em Fullscreen
	//E o esc para sair do fullscreen
	private int gerencia(){
		
		if(teclado.getTecla(Tecla.TSK_ESC)){
			if(fullScreen){
				setFullScreen(false);
				defineJanela();
				teclado.despressionaTecla(Tecla.TSK_ESC);
			}
			
		}
		if((teclado.getTecla(Tecla.TSK_ALT))&&(teclado.getTecla(Tecla.TSK_ENTER))){
			if(!fullScreen){
				setFullScreen(true);
				defineJanela();
				teclado.despressionaTecla(Tecla.TSK_ALT);
				teclado.despressionaTecla(Tecla.TSK_ENTER);
			}
			
		}

		indice.gerencia();
		//mouse.desPressiona();

		return 0;

	}

	private void defineJanela(){

		if(fullScreen==true){

			telaCheia = new TelaCheia();

		}else{

			if(telaCheia!=null){

				telaCheia.dispose();
				telaCheia = null;

			}

		}

	}

	private void defineTamanho(int largura, int altura){

		defineJanela();

		this.largura = largura;
		this.altura = altura;


		GraphicsConfiguration gc = getGraphicsConfiguration();
		volatileImg = gc.createCompatibleVolatileImage(largura, altura);

		grrr.getGrafico().setGraphics(volatileImg);
		grrr.setSize(largura, altura);


		setSize(largura, altura);

	}

	@Override
	public void start(){
		
		Thread t = new Thread(this);
		t.start();

	}

	private final int FRAME_DELAY = 20; // 20ms. implies 50fps (1000/20) = 50

	private void desenha(){
		repaint();
	}

	@Override
	public void paint( Graphics g ) {

		GraphicsConfiguration gc = this.getGraphicsConfiguration();
		int valCode = volatileImg.validate(gc);

		// This means the device doesn't match up to this hardware accelerated image.
		if(valCode==VolatileImage.IMAGE_INCOMPATIBLE){
			createBackBuffer(); // recreate the hardware accelerated image.
		}

		Graphics offscreenGraphics = volatileImg.getGraphics();

		offscreenGraphics.setColor(Color.WHITE);
		offscreenGraphics.fillRect(0,0,largura,altura);	

		grrr.getGrafico().setGraphics(volatileImg.getGraphics());


		desenhaPrincipal();

		if(!fullScreen){
			g.drawImage(volatileImg, 0, 0, this);
		}
		else{
			if(telaCheia!=null)
				telaCheia.desenha(volatileImg);
		}

	}

	@Override
	public void update(Graphics g) {

		paint(g);

	}

	private void createBackBuffer() {
		GraphicsConfiguration gc = getGraphicsConfiguration();
		volatileImg = gc.createCompatibleVolatileImage(largura, altura);
	} 

	@Override
	public void run() {
		long cycleTime = System.currentTimeMillis();
		while(isRunning) {

			teclado.poll();

			gerencia();
			desenha();

			cycleTime = cycleTime + FRAME_DELAY;
			long difference = cycleTime - System.currentTimeMillis();

			try {
				Thread.sleep(Math.max(0, difference));
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void setFullScreen(boolean fullScreen){
		this.fullScreen = fullScreen;
	}

	
	
	public void setCursor(String cursorNormal){
		this.cursorNormal = cursorNormal;
		this.cursorClicado = cursorNormal;
	}
	public void setCursor(String cursorNormal, String cursorClicado){
		this.cursorNormal = cursorNormal;
		this.cursorClicado = cursorClicado;
	}

	private void carregaCursor(){
		mouse.setCursorNormal(grrr.getGrafico().carregaImagem(cursorNormal));
		mouse.setCursorClicado(grrr.getGrafico().carregaImagem(cursorClicado));
		mouse.setCursorCarregando(grrr.getGrafico().carregaImagem(cursorCarregando));
		mouse.resetCursor();
		escondeCursor();
	}

	private void escondeCursor(){
		int[] pixels = new int[16 * 16];
		Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				Toolkit.getDefaultToolkit().createImage( new MemoryImageSource(16, 16, pixels, 0, 16))
				, new Point(0, 0), "invisibleCursor");
		setCursor( transparentCursor );
	}

}