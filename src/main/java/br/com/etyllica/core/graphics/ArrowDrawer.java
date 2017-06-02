package br.com.etyllica.core.graphics;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.input.mouse.MouseStateListener;
import br.com.etyllica.ui.theme.listener.ArrowThemeListener;


public interface ArrowDrawer extends MouseStateListener, Drawable, ArrowThemeListener {
	void drawTimerArc(Graphics g, int arc);
	void setCoordinates(int mouseX, int mouseY);
}
