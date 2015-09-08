package Pop.Exceptions;

public class PesquisaUsuarioException extends UsuarioException {

	public PesquisaUsuarioException (String string) {
		super(string);
	}

	public PesquisaUsuarioException() {
		super("Nao foi possivel realizar login");
	}
}
