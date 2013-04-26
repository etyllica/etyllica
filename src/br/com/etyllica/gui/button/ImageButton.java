package br.com.etyllica.gui.button;

import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.Button;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.StaticLayer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class ImageButton extends Button{

	protected String normal;
	protected String sobMouse;
	protected String click;

	protected String som = null;	
	
	protected ImageLayer layer;

	public ImageButton(int x, int y, int w, int h, String normal){
		this(x,y,w,h, normal, normal, normal);
	}

	public ImageButton(int x, int y, int w, int h, String normal, String sobMouse){
		this(x,y,w,h,normal, sobMouse, sobMouse);
	}
	
	public ImageButton(int x, int y, int w, int h, String normal, String sobMouse, String click){
		super(x,y,w,h);
		this.normal = normal;
		this.sobMouse = sobMouse;
		this.click = click;
		
		this.layer = new ImageLayer(x,y,normal);
	}

	public ImageButton(int x, int y, StaticLayer normal, StaticLayer sobMouse) {
		this(x,y,normal.getW(),normal.getH(),normal.getPath(),sobMouse.getPath());
	}

	public ImageButton(int x, int y, StaticLayer normal, StaticLayer sobMouse, StaticLayer click) {
		this(x,y,normal.getW(),normal.getH(),normal.getPath(),sobMouse.getPath(),click.getPath());
	}

	public void setSom(String som){
		this.som = som;
	}

	public void draw(Grafico g){
		
		layer.draw(g);
	
		drawLabel(g);

	}
		
	@Override
	protected void leftClick(){
		layer.cloneLayer(click);
	}
	
	@Override
	protected void rightClick(){
		layer.cloneLayer(click);
	}

	@Override
	protected void middleClick(){
		layer.cloneLayer(click);
	}

	@Override
	protected void justOnMouse(){
		layer.cloneLayer(sobMouse);
	}
	
	@Override
	protected void mouseOut(){
		layer.cloneLayer(normal);
	}

	@Override
	public void setX(int x){
		this.x = x;

		centralizaLayer();		
	}

	@Override
	public void setY(int y){
		this.y = y;

		centralizaLayer();		
	}

	public void setCoordenadas(int x, int y){
		this.x = x;
		this.y = y;

		centralizaLayer();
	}

	private void centralizaLayer(){
		layer.setX(x);
		layer.setY(y);
	}

}
