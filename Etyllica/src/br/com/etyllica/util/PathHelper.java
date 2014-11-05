package br.com.etyllica.util;

import javax.swing.filechooser.FileSystemView;

public class PathHelper {

	public static String currentDirectory() {
		return "file:"+ClassLoader.getSystemClassLoader().getResource(".").getPath();
	}
	
	public static String desktopDirectory() {
		
		FileSystemView filesys = FileSystemView.getFileSystemView();

		return filesys.getHomeDirectory().getAbsolutePath();
	}
	
	public static String programFilesDirectory() {

		return System.getenv("ProgramFiles");	
	}
	
}
