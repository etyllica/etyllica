package br.com.etyllica.effects;

import java.awt.Color;

import br.com.etyllica.animation.scripts.OpacityAnimationScript;
import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.i18n.DefaultDictionary;

/**
 * 
 * @author yuripourre
 * @license LGPLv3
 *
 */

public class GenericFullScreenEffect extends GlobalEffect{
	
	private DefaultDictionary dictionary;
		
	public GenericFullScreenEffect(int x, int y, int w, int h) {
		super(x, y, w, h);
			
		dictionary = new DefaultDictionary();
		
		//3 seconds animation
		script = new OpacityAnimationScript(this, 3000);
		script.setInterval(255, 0);
		
	}
	
	private final int rectW = 360;
	private final int rectH = 50;
	private final int rectX = w/2-rectW/2;
	private final int rectY = h/2-h/4;
	
	@Override
	public void draw(Grafico g) {
		
		g.setOpacity(opacity);
				
		g.setColor(Color.BLACK);
		g.fillArc(rectX-rectH/2, rectY, rectH, rectH,90,180);
		g.fillRect(rectX, rectY, rectW, rectH);
		g.fillArc(rectX+rectW-rectH/2, rectY, rectH, rectH,270,180);
		
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(20f));
		
		String sentence = dictionary.getTranslatedWord(DefaultDictionary.MESSAGE_FULLSCREEN,Configuration.getInstance().getLanguage());
		
		g.drawStringShadow(rectX, rectY, rectW, rectH, sentence, Color.BLACK);
		
		g.setOpacity(255);
				
	}
		
}

