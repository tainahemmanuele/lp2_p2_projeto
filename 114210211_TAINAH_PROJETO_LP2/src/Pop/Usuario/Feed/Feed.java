package Pop.Usuario.Feed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Pop.Post.Post;
import Pop.Usuario.Usuario;
import Pop.Usuario.TipoUsuario.CelebridadePop;
import Pop.Usuario.TipoUsuario.IconePop;
import Pop.Usuario.TipoUsuario.Normal;

public class Feed implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2915614674606805591L;
	List<Post> postAmigos;
	OrdenaPost ordena;

	public Feed() {
		this.postAmigos = new ArrayList<Post>();
		this.ordena = new OrdenaPostTempo();
	}

	public void atualiza(List<Usuario> amigos) {
		for (Usuario amigo : amigos) {
			Collections.sort(amigo.getPosts());
			Collections.reverse(amigo.getPosts());

			if (amigo.getPopularidade() instanceof Normal) {
				for (int i = 0; i < Normal.POSTS_AMIGO; i++) {
					postAmigos.add(amigo.getPosts().get(i));
				}

			} else if (amigo.getPopularidade() instanceof CelebridadePop) {
				for (int i = 0; i < CelebridadePop.POSTS_AMIGO; i++) {
					postAmigos.add(amigo.getPosts().get(i));
				}
			} else if (amigo.getPopularidade() instanceof IconePop) {
				for (int i = 0; i < IconePop.POSTS_AMIGO; i++) {
					postAmigos.add(amigo.getPosts().get(i));
				}
			}
		}
	}

	public void ordenaTempo() {
		this.ordena = new OrdenaPostTempo();
	}

	public void ordenaPopularidade() {
		this.ordena = new OrdenaPostPopularidade();
	}

	public void ordena() {
		this.ordena.ordenaListaFeed(postAmigos);
	}
}
