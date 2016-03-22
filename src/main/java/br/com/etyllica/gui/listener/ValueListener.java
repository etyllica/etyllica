package br.com.etyllica.gui.listener;

public interface ValueListener<T extends Number> {
	
	void onChange(T value);
	
}
