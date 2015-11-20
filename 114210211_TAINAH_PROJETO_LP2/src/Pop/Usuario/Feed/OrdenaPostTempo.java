/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Usuario.Feed;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import Pop.Post.Post;

/**
 * Classe que implementa a interface OrdenaPost.
 * 
 * @author Tainah Emmanuele
 *
 */
public class OrdenaPostTempo implements OrdenaPost, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9110374957443369911L;

	/**
	 * Metodo que permite a ordenacao da lista de posts os amigos do usuario
	 * pela data dos posts.
	 * 
	 * @param posts
	 *            : lista de posts dos amigos do usuario.
	 */
	@Override
	public void ordenaListaFeed(List<Post> posts) {
		Collections.sort(posts);

	}

}
