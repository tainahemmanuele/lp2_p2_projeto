/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Exceptions;

public class InfoUsuarioException extends PesquisaUsuarioException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5860814150109349601L;

	public InfoUsuarioException(String string) {
		super(string);
	}

	public InfoUsuarioException(Exception e) {
		super(e.getMessage() + "Um usuarix ainda esta logadx.");
	}

	public InfoUsuarioException() {
		super("A senha dx usuarix eh protegida.");
	}
}
