package br.com.etyllica.core;

import br.com.etyllica.awt.core.AWTCore;
import br.com.etyllica.commons.event.GUIEvent;
import br.com.etyllica.loader.Loader;

public interface Engine {

	AWTCore getCore();

	void init();

	void addLoader(Loader loader);

	void hideCursor();

	void updateSuperEvent(GUIEvent event);

}
