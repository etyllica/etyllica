package br.com.etyllica.util;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

import javax.swing.filechooser.FileSystemView;

import br.com.etyllica.util.io.IOHelper;

public class PathHelper {

	public static String currentDirectory() {
		String path = Paths.get(".").toAbsolutePath().normalize().toString();
		String currentDirectory = IOHelper.FILE_PREFIX+path+File.separator; 
		return currentDirectory;
	}
	
	public static String upperDirectory(String path) {
		
		String separator = File.separator;
		if(!path.contains(separator)) {
			separator = "\\";
		}
		
		return path.substring(0,path.lastIndexOf(separator)+1);
	}
	
	public static String currentPath() {
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
