package br.com.etyllica.layer;

import br.com.etyllica.core.loader.ImageLoader;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class StaticLayer extends Layer{
	
	protected String path = "";
	
	public StaticLayer(){
		super();
	}
	
	public StaticLayer(int x, int y){
		super(x,y);
	}
	
	public StaticLayer(int x, int y, int w, int h){
		super(x,y,w,h);
	}
	
	public StaticLayer(int x, int y, int w, int h, String path){
		super(x,y);
		
		this.path = path;
		
		StaticLayer cam = ImageLoader.getInstance().loadImage(path);
		this.w = cam.getW();
		this.h = cam.getH();
	}
	
	
	public StaticLayer(String path){
		this.path = path;
		StaticLayer cam = ImageLoader.getInstance().loadImage(path);
		this.w = cam.getW();
		this.h = cam.getH();
	}
	
	public String getPath(){
		return path;
	}
	
	public void setPath(String path){
		this.path = path;
	}
	
	public void setCoordLimite(int w , int h) {
		this.w = w;
		this.h = h;
	}
	
	public void cloneLayer(String path) {
		this.path = path;
	}
	
	public void cloneLayer(StaticLayer b) {
		this.path = b.path;
		w = b.getW();
		h = b.getH();
	}

}
