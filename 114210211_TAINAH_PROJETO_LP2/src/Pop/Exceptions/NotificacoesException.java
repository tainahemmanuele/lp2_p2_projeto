/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Exceptions;

public class NotificacoesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1299005347326110870L;

	public NotificacoesException(String string) {
		super(string);
	}

	public NotificacoesException() {
		super("Nao ha mais notificacoes.");
	}
}
