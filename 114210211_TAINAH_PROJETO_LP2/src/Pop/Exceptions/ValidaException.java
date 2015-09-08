package Pop.Exceptions;

import java.text.ParseException;

public class ValidaException extends ParseException{

	public ValidaException(String s, int errorOffset) {
		super(s, errorOffset);
		// TODO Auto-generated constructor stub
	}

	public ValidaException() {
		super("Erro no cadastro de Usuarios. Data nao existe.", 0);
	}
}
