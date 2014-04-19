package br.com.etyllica.gui.panel;

import java.awt.Color;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.View;

public class ColoredTextPanel extends View{

	private Color backgroundcolor = Color.GRAY;
	private Color bordercolor = Color.BLACK;
	private float borderWidth = 2f;

	private String text = "Hello my friend stay awile and listen!";

	private int paddingTop = 5;//5 px;
	private int paddingRight = 4;//4px;
	
	private int spacing = 0;
	private float fontSize = 20;

	public ColoredTextPanel(int x, int y, int w, int h){
		super(x,y,w,h);	
	}

	@Override
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphic g) {
		
		g.setColor(backgroundcolor);
		g.fillRect(x,y,w,h);
		
		g.setBasicStroke(borderWidth);
		g.setColor(bordercolor);
		g.drawRect(x,y,w,h);
		
		g.setBasicStroke(1f);
		g.setFont(g.getFont().deriveFont(fontSize));

		for(int i=0;i<h/10;i++){

			switch(i%4){
			case 0:
				g.setColor(Color.BLUE);
				break;
			case 1:
				g.setColor(Color.RED);
				break;
			case 2:
				g.setColor(Color.ORANGE);
				break;
			default:
				g.setColor(Color.GREEN);
				break;
			}

			g.write(paddingRight, y+paddingTop+(int)(fontSize+(i*fontSize+spacing)), text);
			
		}

	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}

}