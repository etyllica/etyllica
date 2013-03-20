package br.com.etyllica.gui.icon;

import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.label.Icon;
import br.com.etyllica.layer.ImageLayer;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class ImageIcon extends Icon{

	private ImageLayer camada; 
	
	public ImageIcon(int x, int y, String path){
		super(x, y);
		camada = new ImageLayer(0,0,path);
	}
	
	public ImageIcon(String path){
		super(0, 0);
		camada = new ImageLayer(0,0,path);
	}
	
	@Override
	public void draw(Grafico g) {
		//camada.setOffset(x, y);
		camada.draw(g);
		//camada.setOffset(-x, -y);
	}
	
	//Useful methods to centralize label
	@Override
	public void setX(int x){
		camada.setX(x);
	}
	
	@Override
	public void setY(int y){
		camada.setY(y);
	}
	
	public int getW(){
		return camada.getW();
	}
	
	@Override
	public int getH(){
		return camada.getH();
	}
	
}
