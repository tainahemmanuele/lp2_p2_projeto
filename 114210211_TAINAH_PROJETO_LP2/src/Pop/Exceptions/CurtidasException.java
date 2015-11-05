package Pop.Exceptions;

public class CurtidasException extends PopsException{
	
	public CurtidasException(String string) {
		super(string);
	}

	public CurtidasException() {
		super("Erro na consulta de curtidas.");
	}

}
