package br.com.etyllica.nucleo.sessao;


import java.awt.Container;
import java.util.Map;

import br.com.etyllica.camada.Camada;
import br.com.etyllica.camada.EfeitoSonoro;
import br.com.etyllica.nucleo.Gerenciador;
import br.com.etyllica.nucleo.controle.Mouse;
import br.com.etyllica.nucleo.controle.Teclado;
import br.com.etyllica.nucleo.midia.Multimidia;
import br.com.etyllica.nucleo.video.Grafico;

public abstract class Sessao implements MiniSessao{

	protected boolean externa;
	
	protected int carregando;

	protected int largura;
	protected int altura;
	protected boolean fullscreen;

	protected Multimidia m;
	protected Grafico g;
	//protected String url;
	protected Mouse mouse;
	protected Teclado teclado;

	protected Container painel;

	protected MapaSessao variaveis;

	public Sessao(){
		
		Gerenciador gr = Gerenciador.getInstancia(); 

		this.m = gr.getMultimidia();
		this.g = gr.getGrafico();

		mouse = gr.getControle().getMouse();
		teclado = gr.getControle().getTeclado();

		painel = gr.getContentPane();

		largura = gr.getLargura();
		altura = gr.getAltura();

		externa = false;
		
		carregando = 0;
	}

	public int getCarregando(){
		return carregando;
	}
	protected Camada carregaImagem(String caminho){
		return g.novaCamada(caminho);
	}

	private boolean musica = false;

	protected void tocaMusicaStream(String caminho){
		if(musica)
			m.tocaMusicaStream(caminho);
	}
	protected void tocaSom(EfeitoSonoro efeito){
		if(efeito.isTocando()){
			m.tocaSom(efeito.getSom());
			efeito.setTocando(false);
		}
	}

	//Para Uso Externo
	public void setVariaveis(MapaSessao variaveis){
		this.variaveis = variaveis;
	}
	
	public Map<String,Object> getTodasVariaveis(){
		return variaveis;
	}
	
	public void setVariavelSessao(String nomeVariavel,Object objeto){
		variaveis.put(nomeVariavel, objeto);
	}
	
	public Object getVariavelSessao(String nomeVariaviel){
		
		if(variaveis.containsKey(nomeVariaviel)){
			return variaveis.get(nomeVariaviel);
		}

		return null;
	}

	public boolean isExterna() {
		return externa;
	}

	public void setExterna(boolean externa) {
		this.externa = externa;
	} 

}
