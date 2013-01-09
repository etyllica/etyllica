package br.com.etyllica.nucleo.sessao;

import br.com.etyllica.camada.Camada;
import br.com.etyllica.camada.CamadaAnimacao;
import br.com.etyllica.camada.CamadaTexto;
import br.com.etyllica.gui.ComponenteGui;

public abstract class SessaoInterna extends Sessao{

	protected int wx;
	protected int wy;
	
	public SessaoInterna() {
		super();
		
		wx = 0;
		wy = 0;
	}
	public void desenha(Camada c){
		c.setOffsetX(wx);
		c.setOffsetY(wy);
		g.desenha(c);
		c.setOffsetX(-wx);
		c.setOffsetY(-wy);
	}
	public void desenha(CamadaAnimacao c){
		c.setOffsetX(wx);
		c.setOffsetY(wy);
		g.desenha(c);
		c.setOffsetX(-wx);
		c.setOffsetY(-wy);
	}
	public void desenha(CamadaTexto c){
		c.setOffsetX(wx);
		c.setOffsetY(wy);
		g.desenha(c);
		c.setOffsetX(-wx);
		c.setOffsetY(-wy);
	}
	public void desenha(ComponenteGui c){
		c.setOffsetX(wx);
		c.setOffsetY(wy);
		g.desenha(c);
		c.setOffsetX(-wx);
		c.setOffsetY(-wy);
	}

}
