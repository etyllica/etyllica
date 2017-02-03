package br.com.etyllica.core.i18n;

import java.util.HashMap;
import java.util.Map;

import br.com.etyllica.core.i18n.Language;

/**
 * 
 * @author yuripourre
 *
 */

public abstract class Dictionary {

	protected Map<Language, Map<String, String>> vocabulary;
	
	protected Language defaultLanguage = Language.ENGLISH_US;

	public Dictionary() {
		vocabulary = new HashMap<Language,Map<String, String>>();
		populateVocabulary();
	}

	protected abstract void populateVocabulary();

	public Map<String, String> getDictionary(Language language) {
		return vocabulary.get(language);
	}

	public String getTranslatedWord(String word, Language language) {
		Map<String, String> words;
		
		if (vocabulary.containsKey(language)) {
			words = vocabulary.get(language);
		} else {
			words = vocabulary.get(defaultLanguage);
		}
		
		return words.get(word);
	}

}
