package br.com.etyllica.core.application;

import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.effects.TransitionEffect;
import br.com.etyllica.gui.GUIComponent;
import br.com.etyllica.layer.ImageLayer;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public abstract class Application extends GUIComponent implements Runnable{

	private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	private int updateInterval = -1;
	
	protected LoadApplication loadApplication;
	protected Application returnApplication = this;
	protected TransitionEffect effect = TransitionEffect.NONE;
	
	protected int loading = 0;
	protected String loadingPhrase = "Loading...";
	
	protected String title = "Application";
	protected boolean fullscreen = false;
	
	protected boolean apagar = true;
	
	protected SessionMap variaveis;

	//TODO Should be removed 
	protected BufferedImage bufferedImage;
	
	protected ImageLayer icon = null;
	
	public Application(){
		this(640,480);
	}
	
	public Application(int x, int y, int w, int h){
		super(x,y,w,h);
		
		this.loading = 0;
		//TODO Dictionary get "loading"+...
		this.loadingPhrase = "Carregando...";
	}
		
	public Application(int w, int h){
		this(0,0,w,h);
	}

	public abstract void load();
	
	//public abstract GUIEvent gerencia(Event event);
	public abstract void draw(Grafico g);
	
	public void unload(){
		
	}
	
	@Override
	public void update(GUIEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onMouse(int mx, int my) {
		return false;
	}

	public int getLoading(){
		return loading;
	}
	
	public String getLoadingPhrase(){
		return loadingPhrase;
	}

	//Para Uso Externo
	public void setVariaveis(SessionMap variaveis){
		this.variaveis = variaveis;
	}
	
	public SessionMap getTodasVariaveis(){
		return variaveis;
	}
	
	public void setVariavelSessao(String nomeVariavel,Object objeto){
		variaveis.put(nomeVariavel, objeto);
	}
	
	public Object getVariavelSessao(String nomeVariaviel){
		
		if(variaveis.containsKey(nomeVariaviel)){
			return variaveis.get(nomeVariaviel);
		}

		return null;
	}
		
	public boolean isApagar(){
		return apagar;
	}

	public BufferedImage getBimg() {
		return bufferedImage;
	}

	public void setBimg(BufferedImage bimg) {
		this.bufferedImage = bimg;
	}

	public Application getReturnApplication() {
		return returnApplication;
	}

	public void setReturnApplication(Application returnApplication) {
		this.returnApplication = returnApplication;
	}
		
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ImageLayer getIcon() {
		return icon;
	}

	public void setIcon(ImageLayer icon) {
		this.icon = icon;
	}
			
	protected  void updateAtFixedRate(int interval){
		
		updateInterval = interval;
		
		executor.scheduleAtFixedRate(this, 0, updateInterval, TimeUnit.MILLISECONDS);
		
	}

	public void run(){
		timeUpdate();
	}
	
	protected void timeUpdate(){
		
	}
	
}
