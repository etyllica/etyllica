package br.com.etyllica.gui.base;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.gui.View;
import br.com.etyllica.layer.ImageLayer;

/**
 * ImageView component
 * 
 * @author yuripourre
 *
 */

public class BaseImageView extends View {
	
	private ImageLayer layer;
	
	public BaseImageView(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public BaseImageView(String path) {
		super();
		layer = new ImageLayer(path);
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		return GUIEvent.NONE;
	}

	@Override
	public void updateEvent(GUIEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Graphics g) {
		layer.simpleDraw(g);
	}

	@Override
	public void setBounds(int x, int y, int w, int h) {
		super.setBounds(x, y, w, h);
		layer.setBounds(x, y, w, h);
	}
}
