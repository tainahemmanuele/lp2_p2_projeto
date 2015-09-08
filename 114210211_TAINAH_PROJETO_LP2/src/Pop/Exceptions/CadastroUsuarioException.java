package Pop.Exceptions;

public class CadastroUsuarioException extends UsuarioException {


	public CadastroUsuarioException(String string) {
		super(string);
	}

	public CadastroUsuarioException() {
		super("Falha no cadastro. tente novamente");
	}
}
