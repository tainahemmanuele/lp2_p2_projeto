/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Usuario.TipoUsuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Pop.Post.Post;

/**
 * Classe que implementa a interface Popularidade.
 * 
 * @author Tainah Emmanuele
 *
 */
public class CelebridadePop implements Popularidade {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1122802401371589508L;
	public static final int POP_PONTOS = 25;
	public static final int POP_PONTOS_EXTRA = 10;
	public static final int POSTS_AMIGO =4;

	/**
	 * Construtor de CelebridadePop.
	 */
	public CelebridadePop() {
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
		if (post.getDataLocalDate().toLocalDate().equals(LocalDate.now())) {
			post.curtirPost(POP_PONTOS + POP_PONTOS_EXTRA);
			return POP_PONTOS + POP_PONTOS_EXTRA;
		} else {
			post.curtirPost(POP_PONTOS);
			return POP_PONTOS;
		}
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
		if (post.getDataLocalDate().toLocalDate().equals(LocalDate.now())) {
			post.rejeitaPost(POP_PONTOS + POP_PONTOS_EXTRA);
			return POP_PONTOS + POP_PONTOS_EXTRA;
		} else {
			post.rejeitaPost(POP_PONTOS);
			return POP_PONTOS;
		}
	}

	@Override
	public String toString() {
		return "Celebridade Pop";
	}

	

}
