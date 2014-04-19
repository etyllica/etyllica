package br.com.etyllica.gui.label;

import br.com.etyllica.core.graphics.Graphic;
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
		this(0, 0, path);
	}
	
	public ImageLabel(int x, int y, ImageLayer layer){
		super(x, y);
		this.layer = layer;
	}
	
	public ImageLabel(ImageLayer layer){
		this(0, 0, layer);
	}
		
	@Override
	public void draw(Graphic g) {
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
