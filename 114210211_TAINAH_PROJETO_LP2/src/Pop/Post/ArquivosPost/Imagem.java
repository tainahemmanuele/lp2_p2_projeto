/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Post.ArquivosPost;

/**
 * Classe herdada de arquivo. Cria arquivo do tipo imagem.
 * @author Tainah Emmanuele
 *
 */
public class Imagem extends Arquivo {

	public Imagem(String arquivo) {
		super(arquivo);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "$arquivo_imagem:"+super.toString();
	}

}
