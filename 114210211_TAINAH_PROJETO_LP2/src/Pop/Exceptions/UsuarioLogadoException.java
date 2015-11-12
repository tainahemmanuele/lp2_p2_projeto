/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Exceptions;

public class UsuarioLogadoException extends UsuarioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4369983830075680037L;

	public UsuarioLogadoException(String string) {
		super(string);
	}

	public UsuarioLogadoException() {
		super("Nenhum usuarix esta logadx no +pop.");
	}
}
