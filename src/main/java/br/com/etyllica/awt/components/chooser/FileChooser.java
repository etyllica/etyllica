package br.com.etyllica.awt.components.chooser;

import java.awt.Component;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FileChooser extends JFileChooser implements Runnable {
	
	private static final long serialVersionUID = -6559989743288788106L;

	private Component component;
	private SelectFileListener listener;
	
	public FileChooser(Component component) {
		super();
		this.component = component;
		init();
	}
	
	public FileChooser(Component component, String path) {
		super(path);
		this.component = component;
		init();
	}

	private void init() {
		initWithSystemUI();
		
		PreviewPane previewPane = new PreviewPane();
		setAccessory(previewPane);
		addPropertyChangeListener(previewPane);
	}

	private void initWithSystemUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openDialog() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		if(showOpenDialog(this.component)==JFileChooser.APPROVE_OPTION) {
			String path = getSelectedFile().getAbsolutePath();
			notifyListener(path);
		}
	}

	private void notifyListener(String path) {
		if (listener != null) {
			listener.onSelectFile(path);
		}
	}

	public SelectFileListener getListener() {
		return listener;
	}

	public void setListener(SelectFileListener listener) {
		this.listener = listener;
	}

}
