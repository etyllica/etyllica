package br.com.etyllica.gui.window;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.event.GUIAction;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.gui.Window;
import br.com.etyllica.gui.button.RoundButton;
import br.com.etyllica.gui.label.ImageLabel;
import br.com.etyllica.gui.label.TextLabel;
import br.com.etyllica.layer.ImageLayer;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class DefaultWindow extends Window{

	//private Panel panel;
	private RoundButton closeButton;
	private RoundButton iconButton;
	private TextLabel titleBar;
		
	public DefaultWindow(int x, int y, int w, int h){
		super(x,y,w,h);
		
		//panel = new Panel(0, 0, w, h);
		//add(panel);
		
		int buttonRadius = 50;
		
		iconButton = new RoundButton(-buttonRadius,-buttonRadius, buttonRadius*2, buttonRadius*2);
		
		closeButton = new RoundButton(w-buttonRadius,-buttonRadius, buttonRadius*2, buttonRadius*2);
		closeButton.setLabel(new ImageLabel(-20,20,"mystic/icons/default/close.png"));
		
		closeButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new GUIAction(this, "closeWindow"));
		
		titleBar = new TextLabel(w/-50,15, "...");
	}
		
	@Override
	public void draw(Graphic g){

		//TODO Change to panel
		g.setColor(Configuration.getInstance().getTheme().getWindowBackgroundColor());
		g.fillRect(x,y,w,h);
		
	}
	
	@Override
	public void restartWindow() {
				
		//Here I get Icon and title to title bar
		if(application.getIcon()!=null){
			
			ImageLayer icon = application.getIcon();
			
			iconButton.setLabel(new ImageLabel(icon.getW()-icon.getW()/3, icon.getH()-icon.getH()/3, application.getIcon()));
		}
		if(!application.getTitle().isEmpty()){
			titleBar.setText(application.getTitle());
		}
		
		add(iconButton);
		add(closeButton);
		add(titleBar);
		
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyboardEvent event) {
		
		if(event.getPressed(Tecla.TSK_0)){
			add(iconButton);
			add(closeButton);
			add(titleBar);
			
			System.out.println("Adicionado");
			System.out.println("Components: "+getComponents().size());
		}
		
		return GUIEvent	.NONE;
	}
}
