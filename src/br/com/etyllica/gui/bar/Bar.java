package br.com.etyllica.gui.bar;

import java.awt.Polygon;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.control.mouse.Mouse;
import br.com.etyllica.core.control.mouse.MouseButton;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyState;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.gui.Button;
import br.com.etyllica.layer.ImageLayer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public abstract class Bar extends Button{

	protected int startX;
	protected int startY;

	//protected int windowWidth = 0;
	//protected int windowHeight = 0;

	protected Polygon p;
	protected ImageLayer iconColision = null;
	//protected int barSize = 16;
	protected boolean opened = false;
	protected boolean closed = true;

	protected boolean openning = false;
	protected boolean closing = false;	

	protected ImageLayer icon;

	protected BarApplication application;

	private boolean mouseUP = true;

	protected int velocidade = 5;

	public Bar(int x, int y, int w, int h){
		super(x,y,w,h);

		startX = x;
		startY = y;

		p = new Polygon();

		reset();
	}

	public abstract void reset();

	public abstract void open();

	public abstract void close();

	public boolean onMouse(Mouse mouse){

		if(iconColision!=null){

			if((iconColision.colideCircularPonto(mouse.getX(), mouse.getY()))
					//if((iconColision.colideRetangular(mouse.getX(), mouse.getY(), 1, 1))
					||(p.contains(mouse.getX(), mouse.getY()))){
				mouseOver = true;
			}

			mouseOver = false;

		}else{

			if(p.contains(mouse.getX(), mouse.getY())){
				mouseOver = true;
			}else{
				mouseOver = false;
			}
		}

		return mouseOver;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(BarApplication application) {
		this.application = application;
		application.load();
		add(application);

		//translateGui();	
	}

	//protected abstract void translateGui(); 

	@Override
	public GUIEvent updateMouse(PointerEvent event){

		GUIEvent retorno = GUIEvent.NONE;

		if(onMouse(event)){

			mouseOver = true;

			if(application!=null){
				application.onMouse = true;
			}

			if(event.getState()==KeyState.PRESSED){
				
				if(event.isKey(MouseButton.MOUSE_BUTTON_LEFT)){

					retorno = GUIEvent.MOUSE_LEFT_BUTTON_DOWN;

				}else if(event.isKey(MouseButton.MOUSE_BUTTON_RIGHT)){

					retorno = GUIEvent.MOUSE_RIGHT_BUTTON_DOWN;

				}else if(event.isKey(MouseButton.MOUSE_BUTTON_MIDDLE)){

					retorno = GUIEvent.MOUSE_MIDDLE_BUTTON_DOWN;
				}
			}
			else if(event.getState()==KeyState.RELEASED){

				if(event.isKey(MouseButton.MOUSE_BUTTON_LEFT)){
					retorno = GUIEvent.MOUSE_LEFT_BUTTON_UP;
					
				}else if(event.isKey(MouseButton.MOUSE_BUTTON_RIGHT)){
					retorno = GUIEvent.MOUSE_RIGHT_BUTTON_UP;
					
				}else if(event.isKey(MouseButton.MOUSE_BUTTON_MIDDLE)){
					retorno = GUIEvent.MOUSE_MIDDLE_BUTTON_UP;
				}else{
					retorno = GUIEvent.MOUSE_OVER;

				}

			}

		}else{

			mouseOver = false;
			if(application!=null){
				application.onMouse = false;
			}
			retorno = GUIEvent.NONE;

		}

		if(openning){
			open();
		}else if(closing){
			close();
		}

		if(opened){

			//application.gerencia(controle);
			//application.getGui().gerencia(controle);

			//TODO Verificar se é isso mesmo
			//if(application.getGui().isMouseOver()){
			//	retorno = GUIEvent.MOUSE_OVER;
			//}

		}

		return retorno;

	}

	@Override
	public void update(GUIEvent event){

		switch (event) {

		case MOUSE_LEFT_BUTTON_DOWN:

			if(mouseUP){

				if(application!=null){

					//TODO isOpenning
					if(closed){
						openning = true;
						closed = false;
						visible = true;
					}
					if(opened){
						closing = true;
						opened = false;
					}

				}

				mouseUP = false;
			}
			break;

		case MOUSE_LEFT_BUTTON_UP:

			mouseUP = true;

			break;
		}
	}

	public ImageLayer getIcon() {
		return icon;
	}

	public void setIcon(ImageLayer icon) {
		this.icon = icon;
		reset();
	}

}
