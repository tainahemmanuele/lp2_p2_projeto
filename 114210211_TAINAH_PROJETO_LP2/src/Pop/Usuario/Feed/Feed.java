/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
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

/**
 * Classe criada com o objetivo de permitir que o usuario gerencie os posts dos
 * seus amigos. Permite que a lista de posts dos amigos do usuario seja
 * atualizada e ordenada de acordo com o tempo (data do post) ou com a
 * popularidade do mesmo.
 * 
 * @author Tainah Emmanuele
 *
 */
public class Feed implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2915614674606805591L;
	private List<Post> postAmigos;
	private OrdenaPost ordena;

	/**
	 * Construtor de Feed.
	 */
	public Feed() {
		this.ordena = new OrdenaPostTempo();
	}

	/**
	 * Metodo utilizado para atualizar a lista de posts de amigos do usuario. A
	 * partir do tipo de usuario do amigo(Normal, IconePop, CelebridadePop), e
	 * definida a quantidade de posts que o usuario pode ver do mesmo.
	 * 
	 * @param amigos
	 *            :
	 */
	public void atualiza(List<Usuario> amigos) {
		this.postAmigos = new ArrayList<Post>();
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

	/**
	 * Metodo que atualiza a lista de posts dos amigos pelo tempo.
	 */
	public void ordenaTempo() {
		this.ordena = new OrdenaPostTempo();
	}

	/**
	 * Metodo que atualiza a lista de posts dos amigos pela popularidade.
	 */
	public void ordenaPopularidade() {
		this.ordena = new OrdenaPostPopularidade();
	}

	/**
	 * Metodo que ordena a lista de posts.
	 */
	public void ordena() {
		this.ordena.ordenaListaFeed(postAmigos);
	}

	/**
	 * Retorna um post da lista de posts dos amigos do usuario,com base na
	 * ordenacao por tempo.
	 * 
	 * @param post
	 *            : numero do post quer o usuario que ver.
	 * @return: post.
	 */
	public Post getPostFeedNoticiasRecentes(List<Usuario> amigos, int post) {
		atualiza(amigos);
		ordenaTempo();
		ordena();
		return getPostAmigos().get(post);

	}

	/**
	 * Retorna um post da lista de posts dos amigos do usuario,com base na
	 * ordenacao por popularidade.
	 * 
	 * @param post
	 *            : numero do post que o usuario quer ver.
	 * @return: post.
	 */
	public Post getPostFeedNoticiasMaisPopulares(List<Usuario> amigos, int post) {
		atualiza(amigos);
		ordenaPopularidade();
		ordena();
		return getPostAmigos().get(post);

	}

	public List<Post> getPostAmigos() {
		return postAmigos;
	}
}
