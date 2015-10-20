/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Exceptions;

public class PostException extends Exception {

	public PostException(String string) {
		super(string);
	}

	public PostException() {
		super("Não e possivel criar o post. Tente novamente");
	}

}
