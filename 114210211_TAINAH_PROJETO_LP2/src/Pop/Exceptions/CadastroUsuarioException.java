/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Exceptions;

public class CadastroUsuarioException extends UsuarioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8217151999770243332L;

	public CadastroUsuarioException(String string) {
		super(string);
	}

	public CadastroUsuarioException(Exception e) {
		super("Erro no cadastro de Usuarios." + " " + e.getMessage());
	}

	public CadastroUsuarioException() {
		super("Falha no cadastro. tente novamente");
	}
}
