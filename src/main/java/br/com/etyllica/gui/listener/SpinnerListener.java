package br.com.etyllica.gui.listener;

public interface SpinnerListener<T extends Number> {
	
	void onChange(T value);
	
}
