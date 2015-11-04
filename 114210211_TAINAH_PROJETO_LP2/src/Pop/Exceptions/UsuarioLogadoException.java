package Pop.Exceptions;

public class UsuarioLogadoException extends UsuarioException{

	public UsuarioLogadoException(String string) {
		super(string);
	}

	public UsuarioLogadoException() {
		super("Nenhum usuarix esta logadx no +pop.");
	}
}
