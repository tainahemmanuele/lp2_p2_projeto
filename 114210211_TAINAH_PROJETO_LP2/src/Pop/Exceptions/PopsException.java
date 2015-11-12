/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Exceptions;

public class PopsException extends PostException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3392590179074847465L;

	public PopsException(String string) {
		super(string);
	}

	public PopsException() {
		super("Erro na consulta de Pops.");
	}
}
