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
	}
	
	@Override
	public void animate(int frame){
				
		setFrame(frame);
		
		/*if(frame==frames){
			setVisible(false);
		}*/
		
	}
	
}