package examples.jogo;

import java.awt.Color;

import br.com.etyllica.nucleo.controle.Tecla;
import br.com.etyllica.nucleo.sessao.MiniSessao;
import br.com.etyllica.nucleo.sessao.Sessao;

public class OlaMundo extends Sessao{

	private int alturaTexto = 100;
	
	@Override
	public void carrega() {
		carregando = 100;
	}

	@Override
	public void desenha() {

		g.setColor(new Color(0xDD,0xDD,0xDD));
		g.getGraphics().fillRect(0, 0, largura, altura);
		
		g.setColor(Color.BLACK);
		g.escreveX(alturaTexto, "Olá Mundo!");
	}

	@Override
	public MiniSessao gerencia() {
				
		if(teclado.getTecla(Tecla.TSK_SETA_CIMA)){
			alturaTexto--;
		}
		else if(teclado.getTeclaOnce(Tecla.TSK_SETA_BAIXO)){
			alturaTexto+=10;
		}
		
		if(teclado.getTeclaOnce(Tecla.TSK_ENTER)){
			return new TchauMundo();
		}

		return this;
	}
	
}
