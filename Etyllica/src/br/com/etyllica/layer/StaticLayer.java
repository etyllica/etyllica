package br.com.etyllica.layer;

import br.com.etyllica.core.loader.ImageLoader;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class StaticLayer extends Layer{
	
	protected String path = "";
	
	public StaticLayer(){
		super();
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public StaticLayer(float x, float y){
		super(x,y);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public StaticLayer(float x, float y, float w, float h){
		super(x,y,w,h);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param path
	 */
	public StaticLayer(float x, float y, float w, float h, String path){
		super(x,y);
		
		this.path = path;
		load();
	}
	
	/**
	 * 
	 * @param path
	 */
	public StaticLayer(String path){
		this.path = path;
		load();
	}
	
	public String getPath(){
		return path;
	}
	
	/**
	 * 
	 * @param path
	 */
	public void setPath(String path){
		this.path = path;
	}
	
	/**
	 * 
	 * @param w
	 * @param h
	 */
	public void setSize(int w , int h) {
		this.w = w;
		this.h = h;
	}
	
	/**
	 * 
	 * @param path
	 */
	public void cloneLayer(String path) {
		this.path = path;
	}
	
	/**
	 * 
	 * @param layer
	 */
	public void cloneLayer(StaticLayer layer) {
		this.path = layer.path;
		w = layer.getW();
		h = layer.getH();
	}

	public void load(){
		StaticLayer cam = ImageLoader.getInstance().loadImage(path);
		this.w = cam.getW();
		this.h = cam.getH();
	}

}
