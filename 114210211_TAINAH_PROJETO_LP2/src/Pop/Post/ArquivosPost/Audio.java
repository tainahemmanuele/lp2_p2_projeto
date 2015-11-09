/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Post.ArquivosPost;

/**
 * Classe herdada de arquivo. Cria arquivo do tipo audio.
 * 
 * @author Tainah Emmanuele
 *
 */
public class Audio extends Arquivo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6231570138459104706L;

	public Audio(String arquivo) {
		super(arquivo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "$arquivo_audio:" + super.toString();
	}
}
