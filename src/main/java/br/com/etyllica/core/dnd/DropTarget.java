package br.com.etyllica.core.dnd;

import java.io.File;
import java.util.List;

public interface DropTarget {
	void dragEnter();
	void dragExit();
	void dropFiles(List<File> files);
}
