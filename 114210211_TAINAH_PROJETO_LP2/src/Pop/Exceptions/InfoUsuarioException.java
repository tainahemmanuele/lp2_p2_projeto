package Pop.Exceptions;

public class InfoUsuarioException extends PesquisaUsuarioException  {
	public InfoUsuarioException (String string) {
		super(string);
	}

	public InfoUsuarioException() {
		super("A senha dx usuarix eh protegida.");
	}
}
