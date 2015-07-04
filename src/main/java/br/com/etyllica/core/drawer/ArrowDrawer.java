package br.com.etyllica.core.drawer;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseStateListener;
import br.com.etyllica.theme.listener.ArrowThemeListener;


public interface ArrowDrawer extends MouseStateListener, Drawable, ArrowThemeListener {

	public void drawTimerArc(Graphic g, int arc);
	
	public void setCoordinates(int mouseX, int mouseY);
}
