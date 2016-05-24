package br.com.etyllica.awt.components.chooser;

import java.awt.Component;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FileChooser implements Runnable {
	
	protected String path = "";
	protected JFileChooser chooser;
	protected Component component;
	protected SelectFileListener listener;
	protected boolean opened = false;
	
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

	protected void init() {
		initWithSystemUI();
	}

	protected void initWithSystemUI() {
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
		if(opened) {
			return;
		}
			
		chooser = new JFileChooser(path);
		chooser.setVisible(true);
		opened = true;
		
		new Thread(this).start();
	}

	@Override
	public void run() {
		if(chooser.showOpenDialog(this.component) == JFileChooser.APPROVE_OPTION) {
			String path = chooser.getSelectedFile().getAbsolutePath();
			notifyListener(path);
			opened = false;
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