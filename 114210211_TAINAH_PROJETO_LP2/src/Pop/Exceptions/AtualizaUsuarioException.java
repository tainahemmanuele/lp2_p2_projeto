package Pop.Exceptions;

public class AtualizaUsuarioException extends UsuarioException {
	public AtualizaUsuarioException(String string) {
		super(string);
	}

	public AtualizaUsuarioException() {
		super("Falha na atualiza��o. tente novamente");
	}
}
