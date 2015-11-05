/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Exceptions;

public class InfoUsuarioException extends PesquisaUsuarioException {
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
