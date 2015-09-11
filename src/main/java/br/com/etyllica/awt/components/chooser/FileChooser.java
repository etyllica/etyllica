package br.com.etyllica.awt.components.chooser;

import java.awt.Component;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FileChooser implements Runnable {
	
	private String path = "";
	private JFileChooser chooser;
	private Component component;
	private SelectFileListener listener;
	
	public FileChooser(Component component) {
		super();		
		this.component = component;
		init();
	}
	
	public FileChooser(Component component, String path) {
		super();
		this.path = path;
		this.component = component;
		init();
	}

	private void init() {
		initWithSystemUI();		
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
		
		chooser = new JFileChooser(path);
		
		PreviewPane previewPane = new PreviewPane();
		chooser.setAccessory(previewPane);
		chooser.addPropertyChangeListener(previewPane);
		chooser.setVisible(true);
		
		new Thread(this).start();
	}

	@Override
	public void run() {
		if(chooser.showOpenDialog(this.component) == JFileChooser.APPROVE_OPTION) {
			String path = chooser.getSelectedFile().getAbsolutePath();
			notifyListener(path);
		}
		
		chooser = null;
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
