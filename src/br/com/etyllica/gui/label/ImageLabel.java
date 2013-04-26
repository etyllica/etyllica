package br.com.etyllica.gui.label;

import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.layer.ImageLayer;

/**
 * 
 * ImageLayer as label
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ImageLabel extends Icon{

	private ImageLayer layer; 
	
	public ImageLabel(int x, int y, String path){
		super(x, y);
		layer = new ImageLayer(0,0,path);
	}
	
	public ImageLabel(String path){
		super(0, 0);
		layer = new ImageLayer(0,0,path);
	}
	
	public ImageLabel(ImageLayer layer){
		super(0, 0);
		this.layer = layer;
	}
	
	@Override
	public void draw(Grafico g) {
		//camada.setOffset(x, y);
		layer.draw(g);
		//camada.setOffset(-x, -y);
	}
	
	//Useful methods to centralize label
	@Override
	public void setX(int x){
		layer.setX(x);
	}
	
	@Override
	public void setY(int y){
		layer.setY(y);
	}
	
	@Override
	public int getW(){
		return layer.getW();
	}
	
	@Override
	public int getH(){
		return layer.getH();
	}
	
}