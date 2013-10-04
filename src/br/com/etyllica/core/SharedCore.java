package br.com.etyllica.core;

import br.com.etyllica.core.loader.FontLoader;
import br.com.etyllica.core.loader.ImageLoader;
import br.com.etyllica.core.loader.MultimediaLoader;
import br.com.etyllica.core.video.FullScreenWindow;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.effects.GenericFullScreenEffect;

public class SharedCore {
	
	private int w;
	private int h;

	private String path = "";
	
	private Graphic graphic;
	
	private Core core;
	
	private FullScreenWindow telaCheia = null;
	
	private boolean fullScreenEnable;

	public SharedCore(int w, int h){
		super();
		this.w = w;
		this.h = h;
		
		this.graphic = new Graphic(w,h);
		this.core = new Core();
	}
	
	public Core getCore() {
		return core;
	}

	public void setCore(Core core) {
		this.core = core;
	}

	public Graphic getGraphic() {
		return graphic;
	}

	public void setGraphic(Graphic graphic) {
		this.graphic = graphic;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public void initDefault(){
		ImageLoader.getInstance().setUrl(path);
		FontLoader.getInstance().setUrl(path);
	}
	
	public void initSound(){
		MultimediaLoader.getInstance().setUrl(path);
	}
	
	public void enableFullScreen(){
		telaCheia = new FullScreenWindow(core);
		core.addEffect(new GenericFullScreenEffect(0, 0, w, h));
		
		fullScreenEnable = true;
	}
	
	public void disableFullScreen(){
		telaCheia.dispose();
		telaCheia = null;
		
		fullScreenEnable = false;
	}

	public void drawFullScreen(){
		if(fullScreenEnable){
			telaCheia.draw(graphic.getBimg());
		}
	}

	public boolean isFullScreenEnable() {
		return fullScreenEnable;
	}
		
}
