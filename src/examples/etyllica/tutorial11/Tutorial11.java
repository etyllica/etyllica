package examples.etyllica.tutorial11;
import br.com.etyllica.Etyllica;
import examples.etyllica.tutorial11.application.CaptureApplication;

public class Tutorial11 extends Etyllica{

	private static final long serialVersionUID = 1L;

	public Tutorial11() {
		super(640, 480);
	}

	@Override
	public void startGame() {
		setMainApplication(new CaptureApplication(640, 480));
	}

}
