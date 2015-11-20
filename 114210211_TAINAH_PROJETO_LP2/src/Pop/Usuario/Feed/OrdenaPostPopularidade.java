/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Usuario.Feed;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Pop.Post.Post;

/**
 * Classe que implementa a interface OrdenaPost.
 * 
 * @author Tainah Emmanuele
 *
 */
public class OrdenaPostPopularidade implements OrdenaPost, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5852418309193277692L;

	/**
	 * Metodo que permite a ordenacao da lista de posts os amigos do usuario
	 * pela popularidade dos posts.
	 * 
	 * @param posts
	 *            : lista de posts dos amigos do usuario.
	 */
	@Override
	public void ordenaListaFeed(List<Post> posts) {
		Collections.sort(posts, new Comparator<Post>() {
			@Override
			public int compare(Post post1, Post post2) {
				int valor = Integer.compare(post1.getPopularidade(),
						post2.getPopularidade());
				if (valor == 0) {
					return -1;
				} else {
					return valor;
				}
			}

		});
	}

}
