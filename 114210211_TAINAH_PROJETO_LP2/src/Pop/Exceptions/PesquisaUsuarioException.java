/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Exceptions;

public class PesquisaUsuarioException extends UsuarioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8221833994803005249L;

	public PesquisaUsuarioException(String string) {
		super(string);
	}

	public PesquisaUsuarioException() {
		super("Nao foi possivel realizar login");
	}
}
