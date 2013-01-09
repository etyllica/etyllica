package br.com.etyllica.camada;


public class Efeito extends CamadaAnimacao
{

	public Efeito(int x, int y, int xTile, int yTile)
	{
		super(x,y,xTile,yTile);
		setAparecendo(false);
	}
	
	public Efeito(int x, int y, int xTile, int yTile, String caminho)
	{
		super(x,y,xTile,yTile,caminho);
		setAparecendo(false);
		once = true;
	}
	
	public void anima()
	{
		//frameAtual = 0;
		//parado = false;
		
		setAparecendo(true);
		anim();
	}
	
	public void desAnima(){
		parado = true;
		setAparecendo(false);
		desanim();
	}

}