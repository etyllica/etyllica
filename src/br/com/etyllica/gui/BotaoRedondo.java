package br.com.etyllica.gui;

import br.com.etyllica.camada.CamadaEstatica;

public class BotaoRedondo extends Botao{

	public BotaoRedondo(int x, int y, CamadaEstatica normal,
			CamadaEstatica sobMouse) {
		super(x, y, normal, sobMouse);
	}
	
	public int gerencia(){

		if(mouse.getPressionado()>0){

			if(mouse.sobMouseCircular(this)){
				
				acionado = mouse.getPressionado();
				igualaImagem(click);
				
				return acionado;
			}
		}
		else{
			
			if(mouse.sobMouseCircular(this)){
				
				igualaImagem(sobMouse);
				
				if(!tocou){
					if(som!=null){
						midia.tocaSom(som);
					}
					tocou = true;
				}
			}
			else{
				igualaImagem(normal);
				tocou = false;
			}

			acionado = 0;
		}

		return 0;
		
	}

}
