package br.com.etyllica.awt.core;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import br.com.etyllica.awt.FullScreenWindow;
import br.com.etyllica.core.graphics.Monitor;

public class FullScreenHelper {

	public static FullScreenWindow enableFullScreen(AWTCore core, Monitor selectedMonitor) {
		GraphicsDevice gd = selectedMonitor.getDevice();
		
		FullScreenWindow frame = new FullScreenWindow(core, selectedMonitor);

		gd.setFullScreenWindow(frame);
		frame.setVisible(true);

		return frame;
	}

	public static void disableFullScreen(AWTCore core) {
		core.getFullScreenWindow().dispose();
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gs = ge.getDefaultScreenDevice();

		gs.setFullScreenWindow(null);
	}

}
