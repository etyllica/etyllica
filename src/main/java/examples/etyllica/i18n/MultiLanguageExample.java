package examples.etyllica.i18n;

import br.com.etyllica.Etyllica;
import br.com.etyllica.commons.context.Application;

public class MultiLanguageExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public MultiLanguageExample() {
		super(800, 600);
	}

	public static void main(String[] args){
		MultiLanguageExample example = new MultiLanguageExample();
		example.init();
	}

	@Override
	public Application startApplication() {

		return new MultiLanguageApplication(w,h);
	}

}
