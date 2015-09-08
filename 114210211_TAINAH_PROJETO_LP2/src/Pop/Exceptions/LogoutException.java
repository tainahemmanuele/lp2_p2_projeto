package Pop.Exceptions;

public class LogoutException extends LoginException {
	
	public LogoutException (String string) {
		super(string);
	}

	public LogoutException () {
		super("Nao eh possivel realizar logout. Nenhum usuarix esta logadx no +pop.");
	}
}
