package br.com.etyllica.ui.listener;

public interface ValueListener<T extends Number> {
	
	void onChange(T value);
	
}
