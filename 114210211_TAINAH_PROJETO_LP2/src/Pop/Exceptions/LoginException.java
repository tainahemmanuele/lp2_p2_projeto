/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Exceptions;

public class LoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1137375785640488625L;

	public LoginException(String string) {
		super(string);
	}

	public LoginException() {
		super("Nao foi possivel realizar login. Um usuarix ja esta logadx.");
	}
}
