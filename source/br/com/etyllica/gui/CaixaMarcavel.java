package br.com.etyllica.gui;

public class CaixaMarcavel extends ComponenteMouse{

	private String normal;
	private String marcada;

	private boolean mudou = false;
	private boolean marca = false;

	public CaixaMarcavel(int x, int y, String normal, String marcada){
		super(x,y);
		this.normal = normal;
		this.marcada = marcada;
		igualaImagem(normal);
	}

	public void desenha(){
		g.desenha(this);
	}
	/*public int gerencia(){

		if(mouse.getPressionado()==1){

			if(mouse.sobMouse(this)){
				if(!mudou){
					if(!marca){
						this.igualaImagem(marcada);
						marca = true;
					}
					else{
						this.igualaImagem(normal);
						marca = false;	
					}
					
					mudou = true;
				}
			}
		}
		if(mouse.getPressionado()==0){
			
			mudou = false;
		}

		return 0;
		
	}*/
	
	public int gerencia(boolean evento){

		if(mouse.sobMouse(this)){
			
			if(mouse.getPressionado()>0){
				
				acionado = mouse.getPressionado();
				
					setMarcacao();
					
					mouse.desPressiona();
					
			}
			else{
								
				acionado = -1;
				
				if(evento){
					
					setMarcacao();
					
					acionado = 1;
				}
			
			}
			
		}
		else{
			
			acionado = -2;
		}

		return acionado;
		
	}
	
	public void setMarcacao(){
		if(!marca){
			this.igualaImagem(marcada);
			marca = true;
		}
		else{
			this.igualaImagem(normal);
			marca = false;	
		}
	}
	
	public boolean marcada(){
	
		if(marca&&mudou){
			marca = false;
			return true;
		}
		return false;
	}
	

}
