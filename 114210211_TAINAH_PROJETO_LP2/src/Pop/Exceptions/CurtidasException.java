/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Exceptions;

public class CurtidasException extends PopsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3622568450522028833L;

	public CurtidasException(String string) {
		super(string);
	}

	public CurtidasException() {
		super("Erro na consulta de curtidas.");
	}

}
