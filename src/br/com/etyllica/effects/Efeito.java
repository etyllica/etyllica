package br.com.etyllica.effects;

import br.com.etyllica.layer.AnimatedImageLayer;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class Efeito extends AnimatedImageLayer
{

	public Efeito(int x, int y, int xTile, int yTile)
	{
		super(x,y,xTile,yTile);
		setVisible(false);
	}
	
	public Efeito(int x, int y, int xTile, int yTile, String caminho)
	{
		super(x,y,xTile,yTile,caminho);
		setVisible(false);
		once = true;
	}
	
	public void anima()
	{
		//frameAtual = 0;
		//parado = false;
		
		setVisible(true);
		anim();
	}
	
	public void desAnima(){
		parado = true;
		setVisible(false);
		desanim();
	}

}