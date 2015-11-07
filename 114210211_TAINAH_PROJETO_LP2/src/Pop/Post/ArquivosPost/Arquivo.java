/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Post.ArquivosPost;

import java.io.Serializable;

/**
 * Classe criada com o objetivo de criar o arquivo do post.
 * 
 * @author Tainah Emmanuele
 *
 */
public abstract class Arquivo implements Serializable {
	private String arquivo;

	public Arquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	@Override
	public String toString() {
		return arquivo;
	}

	public String getArquivo() {
		return arquivo;
	}

}
