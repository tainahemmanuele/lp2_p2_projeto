/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Exceptions;

public class AtualizaUsuarioException extends UsuarioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2603851502356007672L;

	public AtualizaUsuarioException(String string) {
		super(string);
	}

	public AtualizaUsuarioException(Exception e) {
		super("Erro na atualizacao de perfil." + " " + e.getMessage());
	}

	public AtualizaUsuarioException() {
		super("Falha na atualizacao. tente novamente");
	}
}
