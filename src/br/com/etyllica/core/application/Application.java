package br.com.etyllica.core.application;

import java.awt.image.BufferedImage;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.loader.MultimediaLoader;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.effects.EfeitoSonoro;
import br.com.etyllica.effects.TransitionEffect;
import br.com.etyllica.gui.GUIComponent;
import br.com.etyllica.layer.ImageLayer;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public abstract class Application extends GUIComponent{

	protected LoadApplication loading;
	protected Application returnApplication = this;
	protected TransitionEffect effect = TransitionEffect.NONE;
	
	protected int carregando = 0;
	protected String carregandoFrase = "Loading...";
	
	protected String title = "Application";
	protected boolean fullscreen = false;
	
	protected boolean apagar = true;
	
	protected SessionMap variaveis;

	protected BufferedImage bimg;
	
	protected ImageLayer icon = null;
	
	public Application(){
		this(640,480);
	}
	
	public Application(int x, int y, int w, int h){
		super(x,y,w,h);
		
		this.carregando = 0;
		//TODO Dictionary get "loading"+...
		this.carregandoFrase = "Carregando...";
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

	public int getCarregando(){
		return carregando;
	}
	
	public String getCarregandoFrase(){
		return carregandoFrase;
	}
	
	private boolean musica = false;

	protected void tocaMusicaStream(String caminho){
		if(musica){
			MultimediaLoader.getInstancia().tocaMusicaStream(caminho);
		}
	}
	protected void tocaSom(EfeitoSonoro efeito){
		if(efeito.isTocando()){
			MultimediaLoader.getInstancia().tocaSom(efeito.getSom());
			efeito.setTocando(false);
		}
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
		return bimg;
	}

	public void setBimg(BufferedImage bimg) {
		this.bimg = bimg;
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
		
}
