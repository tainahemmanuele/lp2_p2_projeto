/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Usuario.TipoUsuario;

import Pop.Post.Post;
/**
 * Classe que implementa a interface Popularidade.
 * @author Tainah Emmanuele
 *
 */
public class Normal implements Popularidade{
	public static final int POP_PONTOS = 10;
	
	/**
	 * Construtor de Normal.
	 */
	public Normal() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * Metodo que adiciona pops a um post.
	 * @param post: post que tera pops adicionados.
	 * @return: retorna a quantidade de pops adicionados ao post.
	 */
	@Override
	public int adicionaPop(Post post) {
		post.curtirPost(POP_PONTOS);
		return POP_PONTOS;
	}

	/**
	 * Metodo que diminui pops de um post.
	 * @param post: post que tera pops diminuidos.
	 * @return: retorna a quantidade de pops diminuidos do post.
	 */
	@Override
	public int diminuiPop(Post post) {
		post.rejeitaPost(POP_PONTOS);
		return POP_PONTOS;
	}


	@Override
	public String toString() {
		return "Normal Pop";
	}
	
	

}
