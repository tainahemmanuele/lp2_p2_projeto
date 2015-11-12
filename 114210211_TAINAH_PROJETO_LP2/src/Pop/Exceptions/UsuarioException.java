/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Exceptions;

public class UsuarioException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7581637596395759071L;

	public UsuarioException(String string) {
		super(string);
	}

	public UsuarioException() {
		super("Usuarix nao esta cadastradx no +Pop");
	}

}
