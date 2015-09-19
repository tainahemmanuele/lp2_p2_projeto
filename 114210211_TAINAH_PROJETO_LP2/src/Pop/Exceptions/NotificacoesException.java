package Pop.Exceptions;

public class NotificacoesException extends Exception {

	public NotificacoesException (String string) {
		super(string);
	}

	public NotificacoesException () {
		super("Nao ha mais notificacoes.");
	}
}
