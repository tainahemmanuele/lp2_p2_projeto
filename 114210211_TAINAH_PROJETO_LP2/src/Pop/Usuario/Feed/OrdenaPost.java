/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Usuario.Feed;

import java.io.Serializable;
import java.util.List;

import Pop.Post.Post;

/**
 * Interface criada para permitir que a ordenacao da lista de posts dos amigos
 * do usuario seja feita de forma distinta.
 * 
 * @author Tainah Emmanuele
 *
 */
public interface OrdenaPost extends Serializable {

	/**
	 * Metodo que permite a ordenacao da lista de posts.
	 * 
	 * @param posts
	 *            : lista de posts dos amigos do usuario.
	 */
	public void ordenaListaFeed(List<Post> posts);
}
