package br.com.etyllica.animation.scripts.text;

import br.com.etyllica.animation.scripts.AnimationScript;
import br.com.etyllica.layer.TextLayer;

public class DialogScript extends AnimationScript {

	private String fullText;
	
	private TextLayer target;
	
	public DialogScript(TextLayer target, long time) {
		super(time);
		
		this.target = target;
		
		fullText = target.getText();
	}
	
	@Override
	protected void calculate(double factor) {
		
		int lastChar = (int)(fullText.length()*factor);
		
		target.setText(fullText.substring(0, lastChar));
	}
	
}
