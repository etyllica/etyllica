package br.com.etyllica.commons.ui;


import br.com.etyllica.commons.Drawable;
import br.com.etyllica.commons.Updatable;
import br.com.etyllica.commons.event.KeyEvent;
import br.com.etyllica.commons.event.PointerEvent;

public interface UIComponent extends Drawable, Updatable {

    void updateMouse(PointerEvent event);

    void updateKeyboard(KeyEvent event);

}
