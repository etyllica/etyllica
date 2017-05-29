package br.com.etyllica.core.ui;


import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.Updatable;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;

public interface UIComponent extends Drawable, Updatable {

    void updateMouse(PointerEvent event);

    void updateKeyboard(KeyEvent event);

}
