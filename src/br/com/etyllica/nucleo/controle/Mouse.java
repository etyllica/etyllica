package br.com.etyllica.nucleo.controle;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.event.MouseInputListener;

import br.com.etyllica.camada.Camada;
import br.com.etyllica.camada.CamadaAnimacao;
import br.com.etyllica.camada.CamadaBuffer;

public class Mouse extends CamadaBuffer implements MouseMotionListener,MouseInputListener
{

	public Mouse(int x, int y) {
		super(x, y);
	}

	public Mouse() {
		super(0, 0);
	}
	

	public static int BOTAO_ESQUERDO = 1;
	public static int BOTAO_MEIO = 2;
	public static int BOTAO_DIREITO = 3;

	private Image cursorNormal;
	private Image cursorClicado;
	private Image cursorCarregando;

	private boolean drag = false;
	private int ativo = -1;
	private int pressionado = 0;


	public void setCursorNormal(Image cursorNormal){
		this.cursorNormal = cursorNormal;
	}
	public void setCursorClicado(Image cursorClicado){
		this.cursorClicado = cursorClicado;
	}
	public void setCursorCarregando(Image cursorCarregando) {
		this.cursorCarregando = cursorCarregando;
	}

	public void resetCursor(){
		igualaImagem(cursorNormal);
	}

	public void setCursor(String custom){
		igualaImagem(custom);
	}

	public int getPressionado(){
		return pressionado;
	}

	public void desPressiona(){
		setPressionado(0);
		drag = false;
	}

	//Based on Tim Wright's post at 
	//http://www.gamedev.net/reference/programming/features/javainput/default.asp

	public synchronized void mouseClicked( MouseEvent me ) {
		this.igualaImagem(cursorClicado);
		setPressionado(me.getButton());
		desPressiona();
	}

	public synchronized void mousePressed( MouseEvent me ) {
		//this.igualaImagem(cursorClicado);
		setPressionado(me.getButton());
	}

	public synchronized void mouseReleased( MouseEvent me ) {
		setEstadoNormal();
		desPressiona();
	}

	public synchronized void mouseEntered( MouseEvent me ) {
		mouseMoved( me );
	}

	public synchronized void mouseExited( MouseEvent me ) {

	}

	public synchronized void mouseDragged( MouseEvent me ) {
		//mousePressed( me );
		mouseMoved( me );
		
	}

	public void mouseMoved( MouseEvent me ) {
		setCoordenadas(me.getX(),me.getY());
	}

	public boolean isDragged(){
		return drag;
	}

	public void setDrag(boolean drag){
		this.drag = drag;
	}

	public void setAtivo(int ativo){
		this.ativo = ativo;
	}

	public int getAtivo(){
		return ativo;
	}

	public void setPressionado(int pressionado){
		//System.out.println(this.getClass().getSimpleName()+" clicado "+pressionado);
		this.pressionado = pressionado;
		drag = true;
	}

	public void setEstadoCarregando(){
		this.igualaImagem(cursorCarregando);
	}
	public void setEstadoNormal(){
		this.igualaImagem(cursorNormal);
	}
	
	public boolean sobMouseCircular(Camada b)
	{
		int raio = b.getXLimite()/2;
		return sobMouseCircular(b.getX()+raio,b.getY()+raio, raio);
	}
	
	public boolean sobMouseCircular(int cx, int cy, int raio)
	{
		int dx = cx - x;
		int dy = cy - y;
		
		if ( ( dx * dx )  + ( dy * dy ) < raio * raio ) 
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
	public boolean sobMouseIso(Camada cam){

		int my = cam.getYLimite()/2;
		int mx = cam.getXLimite()/2;

		int x = this.x-cam.getX();
		int y = this.y-cam.getY();

		if(y>+my)
			y=my-(y-my);

		if(x>mx+1+(2*y))
			return false;
		else if(x<mx-1-(2*y))
			return false;
		else
			return true;

	}

	public boolean sobMouse(int bx, int by, int bxLimite, int byLimite)
	{
		if((x<bx)||(x>bx + bxLimite))
		{
			return false;
		}
		if((y<by)||(y>by + byLimite))
		{
			return false;
		}

		return true;	
	}

	public boolean sobMouse(Camada cam)
	{
		int cx = cam.getX();
		int cy = cam.getY();

		return sobMouse(cx, cy, cam.getXLimite(), cam.getYLimite());	
	}

	public boolean sobMouse(CamadaAnimacao cam)
	{
		//		int cx = cam.getX()*largura/fullLargura;
		//		int cy = cam.getY()*altura/fullAltura;
		//		
		//		int cxt = cam.getXTile()*altura/fullAltura;
		//		int cyt = cam.getYTile()*altura/fullAltura;

		return sobMouse(cam.getX(), cam.getY(), cam.getXTile(), cam.getYTile());	
	}

}
