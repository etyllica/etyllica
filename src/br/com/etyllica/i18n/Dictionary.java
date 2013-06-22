package br.com.etyllica.i18n;

import java.util.HashMap;
import java.util.Map;

import br.com.etyllica.i18n.Language;

/**
 * 
 * @author yuripourre
 * @license GPLv3
 *
 */

public class Dictionary {

	protected Map<String,Map<Language,String>> vocabulary;
	
	private Language defaultLanguage = Language.ENGLISH_USA;

	public Dictionary(){

		vocabulary = new HashMap<String,Map<Language,String>>();

		populateVocabulary();
	}

	protected void populateVocabulary(){

	}

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
