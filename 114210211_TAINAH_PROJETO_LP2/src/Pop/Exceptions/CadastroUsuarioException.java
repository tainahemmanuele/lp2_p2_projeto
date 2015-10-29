/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Exceptions;

public class CadastroUsuarioException extends UsuarioException {

	public CadastroUsuarioException(String string) {
		super(string);
	}

	public CadastroUsuarioException(Exception e) {
		super("Erro no cadastro de Usuarios."+ " "+e.getMessage());
	}
	
	public CadastroUsuarioException() {
		super("Falha no cadastro. tente novamente");
	}
}
