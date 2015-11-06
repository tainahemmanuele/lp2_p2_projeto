/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Exceptions;

public class PopsException extends PostException{

	public PopsException(String string) {
		super(string);
	}

	public PopsException() {
		super("Erro na consulta de Pops.");
	}
}
