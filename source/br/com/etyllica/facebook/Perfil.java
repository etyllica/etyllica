package br.com.etyllica.facebook;

import java.util.Hashtable;
import java.util.Vector;

public class Perfil {
	
	Hashtable<String, String> dados;
	
	Vector<Perfil> amigos;
	
	public Perfil(){
		dados = new Hashtable<String, String>();
		
		//Etyllic id
		/*
		eid;
		nome;
		sobreNome;
		dataNascimento;//String?
		sexo;
		idioma;
		private String[] amigos;
		
		//Dados espec�ficos
		corFavorita;
		*/
		amigos = new Vector<Perfil>();
	}
	public String set(String campo, String valor){
		return dados.put(campo, valor);
	}
	public String set(String campo, int valor){
		return dados.put(campo, Integer.toString(valor));
	}
	public String get(String campo){
		return dados.get(campo);
	}
	public int getInt(String campo){
		return Integer.parseInt(dados.get(campo));
	}

	/*
	public String getID(){
		return eid;
	}
	*/
	public boolean importarDadosFB(){
		//http://graphs.facebook.com/
		//id = http.../info.php;
		set("id","10098456790");
		
		String fbdata = "http://graphs.facebook.com/id";
		
		String[] info = fbdata.split("/");
		
		set("Nome",info[0]);
		//....
		
		if(!get("id").equals("1")){
			return true;
		}
		return false;
	}
	
	public Vector<Perfil>getAmigos(){
		return amigos;		
	}

}
