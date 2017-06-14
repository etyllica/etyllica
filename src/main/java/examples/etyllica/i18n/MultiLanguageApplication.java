package examples.etyllica.i18n;

import java.util.HashMap;
import java.util.Map;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.Action;
import br.com.etyllica.commons.event.GUIEvent;
import br.com.etyllica.commons.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.i18n.Language;
import br.com.etyllica.ui.UI;
import br.com.etyllica.ui.Button;
import br.com.etyllica.ui.label.MultiLangLabel;
import br.com.etyllica.ui.label.TextLabel;

public class MultiLanguageApplication extends Application {

	private MultiLangLabel label;
	
	public MultiLanguageApplication(int w, int h) {
		super(w, h);
	}
		
	@Override
	public void load() {

		Map<Language, String> texts = new HashMap<Language, String>();
		
		texts.put(Language.ENGLISH_US, "Good Morning!");
		texts.put(Language.PORTUGUESE_BRAZIL, "Bom dia!");
		texts.put(Language.JAPANESE, "Ohayou!");
		texts.put(Language.FRENCH, "Bonjour!");
		
		label = new MultiLangLabel(120, 150, texts);
		UI.add(label);
		
		//Enable Accessibility Features
		Configuration.getInstance().setTimerClick(true);
		
		Button portugueseButton = new Button(280, 100, 200, 40);
		portugueseButton.setLabel(new TextLabel("Portuguese"));
		portugueseButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "changeLanguage", Language.PORTUGUESE_BRAZIL));

		UI.add(portugueseButton);
		
		Button japaneseButton = new Button(280, 150, 200, 40);
		japaneseButton.setLabel(new TextLabel("Japanese"));
		japaneseButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "changeLanguage", Language.JAPANESE));

		UI.add(japaneseButton);
		
		Button englishButton = new Button(280, 200, 200, 40);
		englishButton.setLabel(new TextLabel("English"));
		englishButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "changeLanguage", Language.ENGLISH_US));

		UI.add(englishButton);
		
		Button frenchButton = new Button(280, 250, 200, 40);
		frenchButton.setLabel(new TextLabel("French"));
		frenchButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "changeLanguage", Language.FRENCH));

		UI.add(frenchButton);
		
		loading = 100;
		
	}	

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateKeyboard(KeyEvent event) {

		if(event.isKeyDown(KeyEvent.VK_1)) {
			UI.changeLanguage(Language.PORTUGUESE_BRAZIL);
		}
		if(event.isKeyDown(KeyEvent.VK_2)) {
			UI.changeLanguage(Language.JAPANESE);
		}
		if(event.isKeyDown(KeyEvent.VK_3)) {
			UI.changeLanguage(Language.ENGLISH_US);
		}
	}
}
