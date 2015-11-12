/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Exceptions;

public class PostException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3412329798024585984L;

	public PostException(String string) {
		super(string);
	}

	public PostException() {
		super("N�o e possivel criar o post. Tente novamente");
	}

}
