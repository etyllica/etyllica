package br.com.etyllica.effects;

/**
 * 
 * @author mscythe
 * @license LGPLv3
 *
 */

public class EfeitoSonoro extends Efeito{
	
	private String som;
	private boolean tocando = false;
	
	public EfeitoSonoro(int x, int y, int xTile, int yTile){
		super(x,y,xTile,yTile);
		setVisible(false);
	}
	
	public EfeitoSonoro(int x, int y, int xTile, int yTile, String caminho){
		super(x,y,xTile,yTile,caminho);
		setVisible(false);
		
	}
	
	@Override
	public void anima(){
		frameAtual = 0;
		parado = false;
		once = true;
		tocando = true;
		
		setVisible(true);
		anim();
	}
	
	@Override
	public void desAnima(){
		parado = true;
		tocando = false;
		setVisible(false);
		desanim();
	}
	
	public boolean isTocando(){
		return tocando;
	}
	
	public void setTocando(boolean tocando){
		this.tocando = tocando;
	}
	
	public void setSom(String caminho){
		this.som = caminho;
	}
	
	public String getSom(){
		return som;
	}

}