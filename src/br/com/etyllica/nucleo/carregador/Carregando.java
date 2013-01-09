package br.com.etyllica.nucleo.carregador;

import java.awt.Color;
import java.awt.image.BufferedImage;

import br.com.etyllica.nucleo.sessao.MiniSessao;
import br.com.etyllica.nucleo.sessao.Sessao;
import br.com.etyllica.nucleo.video.Cor;

public class Carregando extends Sessao{

	public Carregando() {
		super();
		carrega();
		carregando = 100;
	}

	MiniSessao retorno;
	
	String porcent;

	String s;

	private int fill;

	private int bxLimite = 400;
	private int byLimite = 30;

	private int bx = (largura/2)-bxLimite/2;

	private int by = 395;

	public void carrega(){

		s = "Carregando...";

		porcent = "0";

	}

	@Override
	public void desenha() {

		g.setColor(Color.BLACK);
		g.getGraphics().fillRect(0,0,largura,altura);

		g.setColor(Color.BLACK);
		g.getGraphics().drawRect(bx,by,bxLimite,byLimite);
		g.setColor(Color.WHITE);
		g.getGraphics().drawRect(bx-1,by-1,bxLimite+2,byLimite+2);


		g.setColor(Color.WHITE);
		g.escreveX(50, s);


		g.setColor(Color.WHITE);
		g.escreveX(400, porcent);

		
		/* Invert Color */
		
		BufferedImage b = g.getSnapShot();

		Cor c = new Cor(0);
		
		for(int j=by+2;j<by+2+byLimite-3;j++){
			for(int i=bx+2;i<bx+fill;i++){

				c.setRGB(b.getRGB(i, j));
				c.invert();

				g.setColor(c);
				g.getGraphics().drawLine(i,j,i,j);

			}

		}
		

	}	

	public MiniSessao gerencia(){	
		
		return this;
	}

	public void setText(int andamento){
		porcent = Integer.toString(andamento)+"%";

		fill = andamento*4;

	}

	public void setRetorno(MiniSessao sessao) {
		this.retorno = sessao;
	}

}