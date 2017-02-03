package br.com.etyllica.core.i18n;

import java.util.HashMap;
import java.util.Map;

public class DefaultDictionary extends Dictionary{
	
	public static final String MESSAGE_FULLSCREEN = "fullscreen";
	
	public DefaultDictionary() {
		super();
	}
	
	@Override
	protected void populateVocabulary() {
		Map<String,String> usVocabulary = new HashMap<String,String>();
		usVocabulary.put(MESSAGE_FULLSCREEN, "Press ESC to exit Fullscreen.");
		
		Map<String,String> brVocabulary = new HashMap<String,String>();
		brVocabulary.put(MESSAGE_FULLSCREEN, "Pressione ESC para sair da Tela Cheia.");
		
		vocabulary.put(Language.ENGLISH_US, usVocabulary);
		vocabulary.put(Language.PORTUGUESE_BRAZIL, brVocabulary);
	}

}
