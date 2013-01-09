package br.com.etyllica.nucleo;

import java.awt.Container;
import java.net.URL;

import br.com.etyllica.nucleo.controle.PrincipalControle;
import br.com.etyllica.nucleo.midia.Multimidia;
import br.com.etyllica.nucleo.video.Grafico;

public class Gerenciador{

	private static Gerenciador instancia;

	public static Gerenciador getInstancia(int largura, int altura) {
		if(instancia==null){
			instancia = new Gerenciador(largura,altura);
		}

		return instancia;
	}
	
	public static Gerenciador getInstancia() {
		if(instancia==null){
			instancia = new Gerenciador();
		}

		return instancia;
	}
	
	public Grafico getGrafico(){
		return tela;
	}

	private Grafico tela;
	//private Map<String, String> idioma = new HashMap<String, String>();
	//private Perfil perfil;
	
	private int largura = 1;
	private int altura = 1;
	
	private Multimidia midia;

	PrincipalControle controle;
	
	public Gerenciador(int largura, int altura){

		this.largura = largura;
		this.altura = altura;
		
		controle = new PrincipalControle();
		
		midia = new Multimidia(8);
		
		//perfil = new Perfil();

		tela = new Grafico(largura, altura);
		
	}
	
	public Gerenciador(){
		
		this(1,1);
	
	}
	
	public void tocaMusicaStream(String caminho){
		midia.tocaMusicaStream(caminho);
	}
	
	public Multimidia getMultimidia(){
		return midia;
	}

	public void setSize(int largura, int altura){
		this.largura = largura;
		this.altura = altura;		
	}

	public int getLargura() {
		return largura;
	}
	public int getAltura() {
		return altura;
	}	
	
	public PrincipalControle getControle(){
		return controle;
	}

	//SWING
	Container painel;
	public void setContentPane(Container painel){
		this.painel = painel;
		this.painel.setLayout(null);
	}
	public Container getContentPane(){
		return painel;
	}
	
	public URL getCodeBase() {
		return tela.getUrl();
	}
	
	public void setUrl(String s){
		this.tela.setUrl(s);
	}	
	
}