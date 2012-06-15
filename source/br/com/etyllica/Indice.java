package br.com.etyllica;
import br.com.etyllica.nucleo.Componente;
import br.com.etyllica.nucleo.Gerenciador;
import br.com.etyllica.nucleo.carregador.Carregador;
import br.com.etyllica.nucleo.carregador.Carregando;
import br.com.etyllica.nucleo.sessao.MapaSessao;
import br.com.etyllica.nucleo.sessao.MiniSessao;

public class Indice implements Componente{

	private Gerenciador g;

	private MiniSessao sessao;
	
	private MapaSessao variaveis;
	
	
	public Indice(Gerenciador g, MiniSessao sessao) {
		
		this.g = g;
				
		variaveis = new MapaSessao();
		
		load = new Carregando();

		this.sessao = null;
		setSessao(sessao);
	}

	private MiniSessao m;
	private MiniSessao atual;
	private Carregando load;
	private Carregador c;
	
	public void setSessao(MiniSessao sessao){

		if(sessao!=this.sessao)
		{
			//g.getControle().getMouse().desPressiona();
			//tick.tocaSom();
			g.getContentPane().removeAll();
				    
			m = sessao;
			
			this.sessao = sessao;
			
			recarrega();

		}
	}

	private void recarrega(){
		
		c = new Carregador(m);
		m.setVariaveis(variaveis);

		load.setRetorno(sessao);
		atual = load;
		loading();
	}

	private void loading(){

		//TODO criar classe separada
		new Thread(){
			public void run()
			{
				while(c.getLoaded()<100){
					load.setText(c.getLoaded());
				}
				atual = m;
			}
		}.start();

		c.start();
	}

	public int gerencia(){

		setSessao(atual.gerencia());
		
		return 0;

	}

	public void desenha() {
		
		if(atual!=null){
			atual.desenha();
		}

	}

}
