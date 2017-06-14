package br.com.etyllica.commons.module;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.commons.context.Context;
import br.com.etyllica.commons.event.KeyEvent;
import br.com.etyllica.commons.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;

public class ModuleHandler {

    private List<Module> modules = new ArrayList<Module>();

    public void add(Module module) {
        modules.add(module);
    }

    public void remove(Module module) {
        modules.remove(module);
    }

    public void init(Context application) {
        for (Module module : modules) {
            module.init(application);
        }
    }

    public void dispose(Context application) {
        for (Module module : modules) {
            module.dispose(application);
        }
    }

    public void update(long now) {
        for (Module module : modules) {
            module.update(now);
        }
    }

    public void draw(Graphics graphics) {
        for (Module module : modules) {
            module.draw(graphics);
        }
    }

    public void updateMouse(PointerEvent event) {
        for (Module module : modules) {
            module.updateMouse(event);
        }
    }

    public void updateKeyboard(KeyEvent event) {
        for (Module module : modules) {
            module.updateKeyboard(event);
        }
    }
}
