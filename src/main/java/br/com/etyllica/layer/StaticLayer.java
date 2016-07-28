package br.com.etyllica.layer;

import br.com.etyllica.loader.image.ImageLoader;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class StaticLayer extends Layer {
	
	protected String path = "";
	
	public StaticLayer() {
		super();
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public StaticLayer(int x, int y) {
		super(x,y);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public StaticLayer(int x, int y, int w, int h) {
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
	public StaticLayer(int x, int y, int w, int h, String path) {
		super(x,y);
		
		this.path = path;
		load();
		
		this.w = w;
		this.h = h;
	}
	
	/**
	 * 
	 * @param path
	 */
	public StaticLayer(String path) {
		this.path = path;
		load();
	}
	
	public StaticLayer(String path, boolean absolute) {
		this.path = path;
		load(absolute);
	}

	public String getPath() {
		return path;
	}
	
	/**
	 * 
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
		load();
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

	public StaticLayer load() {
		return load(false);
	}
	
	public StaticLayer load(boolean absolute) {
		StaticLayer layer = ImageLoader.getInstance().loadImage(path, absolute);
		this.w = layer.getW();
		this.h = layer.getH();
		
		return layer;
	}

}
