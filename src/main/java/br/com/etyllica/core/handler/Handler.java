package br.com.etyllica.core.handler;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.Updatable;
import br.com.etyllica.core.context.Context;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;

public interface Handler extends Drawable, Updatable {

    /**
     * Update pointer events
     * @param event
     * @return
     */
    void updateMouse(PointerEvent event);

    /**
     * Update keyboard events
     * @param event
     * @return
     */
    void updateKeyboard(KeyEvent event);

    void updateGuiEvent(GUIEvent event);

    /**
     * Handles the context
     * @param context
     * @return
     */
    void init(Context context);

    void dispose(Context context);
}
