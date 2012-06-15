package jogo;

import java.awt.Color;

import br.com.etyllica.nucleo.controle.Tecla;
import br.com.etyllica.nucleo.sessao.MiniSessao;
import br.com.etyllica.nucleo.sessao.Sessao;

public class TchauMundo extends Sessao{
	
	@Override
	public void carrega() {
		
		carregando = 100;
	}

	@Override
	public void desenha() {
		g.setColor(new Color(0xDD,0xDD,0));
		g.getGraphics().fillRect(0, 0, largura, altura);
		
		g.setColor(Color.BLACK);
		g.escreveX(100, "Tchau Mundo!");
	}

	@Override
	public MiniSessao gerencia() {
		
		if(teclado.getTeclaOnce(Tecla.TSK_ENTER)){
			return new OlaMundo();
		}
		
		return this;
	}

}
