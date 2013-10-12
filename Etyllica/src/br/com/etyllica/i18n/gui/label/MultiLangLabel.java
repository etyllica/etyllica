package br.com.etyllica.i18n.gui.label;

import java.util.Map;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.gui.label.TextLabel;
import br.com.etyllica.i18n.Language;

/**
 * 
 * @author yuripourre
 * @license GPLv3
 *
 */

public class MultiLangLabel extends TextLabel{
	
	private Map<Language,String> texts;
	
	public MultiLangLabel(Map<Language,String> texts) {
		this(0, 0, texts);
	}
	
	public MultiLangLabel(int x, int y, Map<Language,String> texts) {
		this(x,y,0,texts);
	}
	
	public MultiLangLabel(int x, int y, int w, Map<Language,String> texts) {
		super(x, y, w);
		
		this.texts = texts;
		
		Language lang = Configuration.getInstance().getLanguage();
				
		reloadText(lang);
	}
		
	@Override
	public void update(GUIEvent event){
				
		if(event==GUIEvent.LOST_FOCUS){
			
			onFocus = false;
			
		}else if(event==GUIEvent.GAIN_FOCUS){
			
			onFocus = true;
			
		}else if(event==GUIEvent.LANGUAGE_CHANGED){
			
			reloadText(Configuration.getInstance().getLanguage());
			
		}
		
	}
	
	public Map<Language, String> getTexts() {
		return texts;
	}

	public void setTexts(Map<Language, String> texts) {
		this.texts = texts;
	}
	
	private void reloadText(Language lang){
		if(texts.containsKey(lang)){
			setText(texts.get(lang));
		}else{
			setText(texts.get(Language.ENGLISH_USA));
		}
		
	}
		
}
