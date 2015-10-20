/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Exceptions;

public class UsuarioException extends Exception{
	
	public UsuarioException(String string) {
		super(string);
	}

	public UsuarioException() {
		super("Usuarix nao esta cadastradx no +Pop");
	}

}
