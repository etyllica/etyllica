package br.com.etyllica.core.input.keyboard;

import br.com.etyllica.commons.Updatable;
import br.com.etyllica.commons.event.KeyEventListener;

public interface Keyboard extends Updatable {
	void init();
	void poll(KeyEventListener listener);
}
