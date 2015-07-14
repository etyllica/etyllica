package br.com.etyllica.core.input.keyboard;

import br.com.etyllica.core.Updatable;
import br.com.etyllica.core.input.InputKeyListener;

public interface Keyboard extends Updatable {
	void init();
	void poll(InputKeyListener listener);
}
