package br.com.etyllica.camada;


public class EfeitoMovel extends CamadaMovel
{

	public EfeitoMovel(int x, int y, int xTile, int yTile)
	{
		super(x,y,xTile,yTile);
		aparecendo = false;
		setIncrementoX(-3);
	}
	
	public void anima()
	{
		parado = false;
		once = true;
		reset();
		
		aparecendo = true;
		anim();
		moviment();
	}
	
	public void desAnima(){
		parado = true;
		aparecendo = false;
		desanim();
	}

}