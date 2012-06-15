import jogo.OlaMundo;
import br.com.etyllica.Etyllica;

public class Tutorial1 extends Etyllica {

	private static final long serialVersionUID = 1L;

	public Tutorial1() {
		super(640, 480);
		
		setPrimeiraSessao(new OlaMundo());
	}
	
}
