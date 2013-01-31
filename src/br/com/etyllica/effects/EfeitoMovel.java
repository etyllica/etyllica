package br.com.etyllica.effects;

import br.com.etyllica.layer.MovementedLayer;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class EfeitoMovel extends MovementedLayer
{

	public EfeitoMovel(int x, int y, int xTile, int yTile)
	{
		super(x,y,xTile,yTile);
		visible = false;
		setIncrementoX(-3);
	}
	
	public void anima()
	{
		parado = false;
		once = true;
		reset();
		
		visible = true;
		anim();
		moviment();
	}
	
	public void desAnima(){
		parado = true;
		visible = false;
		desanim();
	}

}