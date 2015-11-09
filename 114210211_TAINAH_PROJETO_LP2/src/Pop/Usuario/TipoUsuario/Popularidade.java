/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Usuario.TipoUsuario;

import java.io.Serializable;

import Pop.Post.Post;

/**
 * Interface criada para permitir que um usuario possa ser Celebridade, Icone
 * Pop ou Normal.
 * 
 * @author Tainah Emmanuele
 *
 */
public interface Popularidade extends Serializable{

	/**
	 * Metodo que adiciona pops a um post.
	 * 
	 * @param post
	 *            : post que tera pops adicionados.
	 * @return: retorna a quantidade de pops adicionados ao post.
	 */
	public int adicionaPop(Post post);

	/**
	 * Metodo que diminui pops de um post.
	 * 
	 * @param post
	 *            : post que tera pops diminuidos.
	 * @return: retorna a quantidade de pops diminuidos do post.
	 */
	public int diminuiPop(Post post);

}
