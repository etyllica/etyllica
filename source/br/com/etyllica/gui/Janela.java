package br.com.etyllica.gui;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Janela extends ComponenteMouse{
	
	protected BufferedImage tela;
	
	protected boolean ativa;
	protected boolean drag;

	public Janela() {
		this(0,0);
	}

	public Janela(int x, int y){
		super(x,y);
		
		xLimite = 200;
		yLimite = 200;
		
		ativa = true;

		tela = new BufferedImage(xLimite,yLimite,BufferedImage.TYPE_INT_ARGB);
	}
	public Image getTela(){
		return tela.getScaledInstance(xLimite,yLimite,BufferedImage.SCALE_REPLICATE);
	}

	@Override
	public void desenha() {
		Graphics g = tela.getGraphics();
		
		if(aparecendo){
			g.setColor(Color.WHITE);
			g.fillRect(x,y,x+xLimite,yLimite);
		}
	}


	protected int difX;
	protected int difY;

	@Override
	public int gerencia(boolean evento){		
		if(aparecendo){

			if(mouse.getPressionado()==1){

				if(mouse.sobMouse(this)){
					
					if(!drag){
						difX = mouse.getX() - this.getX();
						difY = mouse.getY() - this.getY();
						drag = true;
					}
				}
				if(drag)
					this.setCoordenadas(mouse.getX() - difX, mouse.getY() - difY);
				
			}

			if(mouse.getPressionado()==0){
				drag = false;
			}

			return controle.getMouse().getPressionado();
		}
		else{
			return 0;
		}
	}


}
