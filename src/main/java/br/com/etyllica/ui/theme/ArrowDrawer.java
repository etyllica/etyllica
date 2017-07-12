package br.com.etyllica.ui.theme;

import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.commons.Drawable;
import br.com.etyllica.core.input.mouse.MouseStateListener;
import br.com.etyllica.ui.theme.listener.ArrowThemeListener;


public interface ArrowDrawer extends MouseStateListener, Drawable, ArrowThemeListener {
	void drawTimerArc(Graphics g, int arc);
	void setLocation(int mouseX, int mouseY);
}
