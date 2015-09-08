package Pop.Exceptions;

public class LoginException extends Exception {

	public LoginException (String string) {
		super(string);
	}

	public LoginException () {
		super("Nao foi possivel realizar login. Um usuarix ja esta logadx.");
	}
}
