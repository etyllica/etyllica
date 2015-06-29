package br.com.etyllica.util;

import java.net.URL;

import javax.swing.filechooser.FileSystemView;

import br.com.etyllica.util.io.IOHelper;

public class PathHelper {

	public static String currentDirectory() {
		URL location = PathHelper.class.getProtectionDomain().getCodeSource().getLocation();
		String currentDirectory = IOHelper.FILE_PREFIX+location.getPath();
		return currentDirectory;
	}
	
	public static String desktopDirectory() {
		FileSystemView filesys = FileSystemView.getFileSystemView();
		return filesys.getHomeDirectory().getAbsolutePath();
	}
	
	public static String programFilesDirectory() {
		return System.getenv("ProgramFiles");	
	}
	
}
