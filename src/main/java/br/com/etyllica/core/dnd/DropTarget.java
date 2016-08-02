package br.com.etyllica.core.dnd;

import java.io.File;
import java.util.List;

public interface DropTarget {
	void dragEnter();
	void dragExit();
	void dropFiles(int x, int y, List<File> files);
}
