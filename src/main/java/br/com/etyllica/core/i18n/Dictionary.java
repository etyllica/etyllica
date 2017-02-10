package br.com.etyllica.core.i18n;

import java.util.HashMap;
import java.util.Map;

import br.com.etyllica.core.i18n.Language;

/**
 * 
 * @author yuripourre
 *
 */

public class Dictionary {
	
	protected Language defaultLanguage = Language.ENGLISH_US;
	protected Map<Language, Map<String, String>> vocabularies;

	public Dictionary() {
		vocabularies = new HashMap<Language, Map<String, String>>();
	}

	public Map<String, String> getDictionary(Language language) {
		return vocabularies.get(language);
	}

	public String getTranslatedWord(String word, Language language) {
		Map<String, String> words;
		
		if (vocabularies.containsKey(language)) {
			words = vocabularies.get(language);
		} else {
			words = vocabularies.get(defaultLanguage);
		}
		
		return words.get(word);
	}

	public void addLanguage(Language language) {
		addLanguage(language, new HashMap<String, String>());
	}
	
	public void addLanguage(Language language, Map<String, String> vocabulary) {
		vocabularies.put(language, vocabulary);
	}
	
	public void setDefaultLanguage(Language language) {
		this.defaultLanguage = language;
	}

	public Map<String, String> getVocabulary(Language language) {
		return vocabularies.get(language);
	}

}
