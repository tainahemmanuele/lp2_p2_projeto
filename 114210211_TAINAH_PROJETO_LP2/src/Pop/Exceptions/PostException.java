package Pop.Exceptions;

public class PostException extends Exception {
	
	public PostException(String string) {
		super(string);
	}

	public PostException() {
		super("N�o e possivel criar o post. Tente novamente");
	}

}
