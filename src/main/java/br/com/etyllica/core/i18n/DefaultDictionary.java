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
		Map<Language,String> messageFullscreen = new HashMap<Language,String>();
		messageFullscreen.put(Language.ENGLISH_USA,"Press ESC to exit Fullscreen.");
		messageFullscreen.put(Language.PORTUGUESE_BRAZIL,"Pressione ESC para sair da Tela Cheia.");
		
		vocabulary.put(MESSAGE_FULLSCREEN, messageFullscreen);
		
	}

}
