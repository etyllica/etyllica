package br.com.etyllica.util;

import javax.swing.filechooser.FileSystemView;

import br.com.etyllica.util.io.IOHelper;

public class PathHelper {

	public static String currentDirectory() {
		
		String currentDirectory = IOHelper.FILE_PREFIX+ClassLoader.getSystemClassLoader().getResource(".").getPath();
		
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
