package br.com.etyllica.layer;

import br.com.etyllica.core.loader.ImageLoader;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class StaticLayer extends Layer{
	
	protected String caminho = "";
	
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
		
		this.caminho = path;
		
		StaticLayer cam = ImageLoader.getInstance().carregaImagem(path);
		this.w = cam.getW();
		this.h = cam.getH();
	}
	
	
	public StaticLayer(String caminho){
		this.caminho = caminho;
		StaticLayer cam = ImageLoader.getInstance().carregaImagem(caminho);
		this.w = cam.getW();
		this.h = cam.getH();
	}
	
	public String getCaminho(){
		return caminho;
	}
	
	public void setCaminho(String caminho){
		this.caminho = caminho;
	}
	
	public void setCoordLimite(int w , int h) {
		this.w = w;
		this.h = h;
	}
	
	public void igualaImagem(String caminho) {
		this.caminho = caminho;
	}
	
	public void igualaImagem(StaticLayer b) {
		this.caminho = b.caminho;
		w = b.getW();
		h = b.getH();
	}

}
