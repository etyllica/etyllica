package br.com.etyllica.gui.window;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.Core;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIAction;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.Window;
import br.com.etyllica.gui.button.IconedRoundButton;
import br.com.etyllica.gui.label.TextLabel;
import br.com.etyllica.layer.ImageLayer;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class DefaultWindow extends Window{

	//private Panel panel;
	private IconedRoundButton closeButton;
	private IconedRoundButton iconButton;
	private TextLabel titleBar;
		
	public DefaultWindow(int x, int y, int w, int h){
		super(x,y,w,h);
		
		//panel = new Panel(0, 0, w, h);
		//add(panel);
		
		restart();
	}
	
	private void restart(){
		
		int buttonRadius = 50;
		
		iconButton = new IconedRoundButton(-buttonRadius,-buttonRadius, buttonRadius*2, buttonRadius*2);
		add(iconButton);
		
		closeButton = new IconedRoundButton(w-buttonRadius,-buttonRadius, buttonRadius*2, buttonRadius*2);
		closeButton.setIcon(new ImageLayer(-20,20,"mystic/icons/default/close.png"));
		add(closeButton);
		
		closeButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new GUIAction(this, "closeWindow"));
		
		titleBar = new TextLabel(200,15, "...");
		add(titleBar);
	}
	
	public void closeWindow(){
		
		Core.getInstance().requestClose(this);
		
	}
	
	@Override
	protected void close() {
				
		if(oldApplications.size()>1){
		
			//Clean up current application
			remove(application);
			oldApplications.remove(application);
			
			//Load old application
			this.application = oldApplications.get(oldApplications.size()-1);
					
			if(application.getIcon()!=null){
				iconButton.setIcon(application.getIcon());
			}
			
			add(application);
			
			stillWantClose = false;
		}else{
			stillWantClose = true;
		}
		
	}
	
	@Override
	public void draw(Grafico g){

		//TODO Change to panel
		g.setColor(Configuration.getInstance().getTheme().getWindowBackgroundColor());
		g.fillRect(x,y,w,h);
		
	}
	
	@Override
	public void setApplication(Application application) {
		
		clearComponents();
		
		oldApplications.add(application);
		
		this.application = application;
		//It should be a thread with load
		if(application.getLoading()!=100){
			application.load();
		}
		
		//Here I get Icon and title to title bar
		if(application.getIcon()!=null){
			iconButton.setIcon(application.getIcon());
		}
		if(!application.getTitle().isEmpty()){
			System.out.println("Set titleBar to "+application.getTitle());
			titleBar.setText(application.getTitle());
		}
		
		add(application);
		restart();
				
	}
	
}
