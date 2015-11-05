package Pop.Exceptions;

public class PopsException extends PostException{

	public PopsException(String string) {
		super(string);
	}

	public PopsException() {
		super("Erro na consulta de Pops.");
	}
}
