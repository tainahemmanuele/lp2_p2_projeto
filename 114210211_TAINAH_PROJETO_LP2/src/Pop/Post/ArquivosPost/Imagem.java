/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Post.ArquivosPost;

/**
 * Classe herdada de arquivo. Cria arquivo do tipo imagem.
 * 
 * @author Tainah Emmanuele
 *
 */
public class Imagem extends Arquivo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6658439739087825830L;

	public Imagem(String arquivo) {
		super(arquivo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "$arquivo_imagem:" + super.toString();
	}

}
