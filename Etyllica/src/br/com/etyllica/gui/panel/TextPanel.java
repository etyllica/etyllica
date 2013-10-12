package br.com.etyllica.gui.panel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.gui.GUIComponent;

public class TextPanel extends GUIComponent{

	private Color backgroundcolor = Color.WHITE;
	private Color bordercolor = Color.BLACK;
	private float borderWidth = 2f;

	private int paddingTop = 5;//pixels;
	private int paddingRight = 4;//pixels;
	
	private int spacing = 0;
	
	//TODO theme.fontSize
	private float fontSize = 20;
	
	private List<String> lines = new ArrayList<String>();

	public TextPanel(int w, int h){
		super(0,0,w,h);
	}
	
	public TextPanel(int x, int y, int w, int h){
		super(x,y,w,h);
	}
	
	public void addLine(String line){
		
		lines.add(line);
		
		//This +1 is because String is rendered from bottom to top 
		if(h<(lines.size()+1)*fontSize){
			h += fontSize;
		}

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

		int i=0;
		
		for(String line: lines){
			
			g.escreve(x+paddingRight, y+paddingTop+(int)(fontSize+(i*fontSize+spacing)), line);
			
			i++;
			
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