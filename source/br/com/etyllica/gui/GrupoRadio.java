package br.com.etyllica.gui;

import java.util.ArrayList;


public class GrupoRadio extends ComponenteGui{

	public GrupoRadio() {
		super();
	}
	private ArrayList<BotaoRadio> radio = new ArrayList<BotaoRadio>();
	private int ativado = 0;

	public void insereRadio(BotaoRadio botaoRadio){
		radio.add(botaoRadio);
	}
	public void desenha(){
		for(int i=0;i<radio.size();i++){
			radio.get(i).desenha();
		}
	}
	public int gerencia(boolean evento){

		for(int i=0;i<radio.size();i++){
			radio.get(i).gerencia();
			if(radio.get(i).getAcionado()>0){
				if(ativado!=i)
				{
					getBotaoAtivo().setAcionado(0);
					getBotaoAtivo().desmarca();
					ativado = i;
				}				
			}
		}
		
		return 0;
	}

	public BotaoRadio getBotaoAtivo(){
		return radio.get(ativado);
	}
	public void setAtivo(int ativado){
		this.ativado = ativado;
		radio.get(ativado).marca();
	}
	public int getAtivo(){
		return ativado;
	}

}
