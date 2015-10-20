/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Exceptions;

public class NotificacoesException extends Exception {

	public NotificacoesException (String string) {
		super(string);
	}

	public NotificacoesException () {
		super("Nao ha mais notificacoes.");
	}
}
