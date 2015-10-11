package examples.etyllica.i18n;

import java.util.HashMap;
import java.util.Map;

import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.i18n.Language;
import br.com.etyllica.gui.Button;
import br.com.etyllica.gui.label.MultiLangLabel;
import br.com.etyllica.gui.label.TextLabel;

public class MultiLanguageApplication extends Application {

	private MultiLangLabel label;
	
	public MultiLanguageApplication(int w, int h) {
		super(w, h);
	}
		
	@Override
	public void load() {

		Map<Language, String> texts = new HashMap<Language, String>();
		
		texts.put(Language.ENGLISH_USA, "Good Morning!");
		texts.put(Language.PORTUGUESE_BRAZIL, "Bom dia!");
		texts.put(Language.JAPANESE, "Ohayou!");
		texts.put(Language.FRENCH, "Bonjour!");
		
		label = new MultiLangLabel(120, 150, texts);
		addView(label);
		
		//Enable Accessibility Features
		Configuration.getInstance().setTimerClick(true);
		
		Button portugueseButton = new Button(280, 100, 200, 40);
		portugueseButton.setLabel(new TextLabel("Portuguese"));
		portugueseButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "changeLanguage", Language.PORTUGUESE_BRAZIL));
		
		addView(portugueseButton);
		
		Button japaneseButton = new Button(280, 150, 200, 40);
		japaneseButton.setLabel(new TextLabel("Japanese"));
		japaneseButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "changeLanguage", Language.JAPANESE));
		
		addView(japaneseButton);
		
		Button englishButton = new Button(280, 200, 200, 40);
		englishButton.setLabel(new TextLabel("English"));
		englishButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "changeLanguage", Language.ENGLISH_USA));
		
		addView(englishButton);
		
		Button frenchButton = new Button(280, 250, 200, 40);
		frenchButton.setLabel(new TextLabel("French"));
		frenchButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_UP, new Action(this, "changeLanguage", Language.FRENCH));
		
		addView(frenchButton);
		
		loading = 100;
		
	}	

	@Override
	public void draw(Graphic g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateKeyboard(KeyEvent event) {

		if(event.isKeyDown(KeyEvent.VK_1)) {
			changeLanguage(Language.PORTUGUESE_BRAZIL);
		}
		if(event.isKeyDown(KeyEvent.VK_2)) {
			changeLanguage(Language.JAPANESE);
		}
		if(event.isKeyDown(KeyEvent.VK_3)) {
			changeLanguage(Language.ENGLISH_USA);
		}
	}
}
