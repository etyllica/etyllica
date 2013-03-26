package br.com.etyllica.effects;

import br.com.etyllica.layer.MovementedLayer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class EffectMovel extends MovementedLayer
{

	public EffectMovel(int x, int y, int xTile, int yTile)
	{
		super(x,y,xTile,yTile);
		visible = false;
		setIncrementoX(-3);
	}
	
	public void anima()
	{
		stopped = false;
		once = true;
		reset();
		
		visible = true;
		anim();
		moviment();
	}
	
	public void desAnima(){
		stopped = true;
		visible = false;
		desanim();
	}

}