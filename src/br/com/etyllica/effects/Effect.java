package br.com.etyllica.effects;

import br.com.etyllica.layer.AnimatedLayer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class Effect extends AnimatedLayer{

	public Effect(int x, int y, int xTile, int yTile)
	{
		super(x,y,xTile,yTile);
		setVisible(false);
	}
	
	public Effect(int x, int y, int xTile, int yTile, String caminho)
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
		animate();
	}
	
	public void desAnima(){
		stopped = true;
		setVisible(false);
		//desanim();
	}

}