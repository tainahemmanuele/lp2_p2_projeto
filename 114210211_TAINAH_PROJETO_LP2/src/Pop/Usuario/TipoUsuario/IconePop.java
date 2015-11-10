/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Usuario.TipoUsuario;

import Pop.Post.Post;

/**
 * Classe que implementa a interface Popularidade.
 * 
 * @author Tainah Emmanuele
 *
 */
public class IconePop implements Popularidade {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6582642118430981549L;
	public static final int POP_PONTOS = 50;
	public static final String HASHTAG_CURTIR = "#epicwin";
	public static final String HASHTAG_DESCURTIR = "#epicfail";
	public static final int POSTS_AMIGO = 6;

	/**
	 * Construtor de IconePop.
	 */
	public IconePop() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Metodo que adiciona pops a um post.
	 * 
	 * @param post
	 *            : post que tera pops adicionados.
	 * @return: retorna a quantidade de pops adicionados ao post.
	 */
	@Override
	public int adicionaPop(Post post) {
		post.curtirPost(POP_PONTOS);
		if (!post.getHashtags().contains(HASHTAG_CURTIR)) {
			post.adicionaHashtag(HASHTAG_CURTIR);
		}
		return POP_PONTOS;
	}

	/**
	 * Metodo que diminui pops de um post.
	 * 
	 * @param post
	 *            : post que tera pops diminuidos.
	 * @return: retorna a quantidade de pops diminuidos do post.
	 */
	@Override
	public int diminuiPop(Post post) {
		post.rejeitaPost(POP_PONTOS);
		if (!post.getHashtags().contains(HASHTAG_DESCURTIR)) {
			post.adicionaHashtag(HASHTAG_DESCURTIR);
		}
		return POP_PONTOS;
	}

	@Override
	public String toString() {
		return "Icone Pop";
	}

}
