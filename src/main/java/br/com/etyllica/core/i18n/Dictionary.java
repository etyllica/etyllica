package br.com.etyllica.core.i18n;

import java.util.HashMap;
import java.util.Map;

import br.com.etyllica.core.i18n.Language;

/**
 * 
 * @author yuripourre
 * @license GPLv3
 *
 */

public abstract class Dictionary {

	protected Map<String,Map<Language,String>> vocabulary;
	
	protected Language defaultLanguage = Language.ENGLISH_USA;

	public Dictionary(){

		vocabulary = new HashMap<String,Map<Language,String>>();

		populateVocabulary();
	}

	protected abstract void populateVocabulary();

	public Map<Language, String> getWord(String word){
		return vocabulary.get(word);
	}

	public String getTranslatedWord(String word, Language language){

		Map<Language, String> words = vocabulary.get(word);

		if(words.containsKey(language)){
			return words.get(language);
		}else{
			return words.get(defaultLanguage);
		}

	}

}
