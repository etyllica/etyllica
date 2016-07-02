package br.com.etyllica.core.ui;

import java.util.List;

import br.com.etyllica.gui.View;

public interface ViewContainer {
	int getX();
	int getY();
	List<View> getViews();
}

