/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Util;

import Pop.Exceptions.AtualizaUsuarioException;
import Pop.Exceptions.CadastroUsuarioException;
import Pop.Exceptions.InfoUsuarioException;

/**
 * Classe criada com o objetivo de verificar o formato das string nome, email,
 * senha e imagem, utilizadas na construcao de usuario.
 * 
 * @author Tainah Emmanuele
 *
 */
public class Verificacao {
	/**
	 * Construtor de Verificacao.
	 */
	public Verificacao() {

	}

	/**
	 * Metodo utilizado para verificar a string de nome do usuario.
	 * 
	 * @param nome
	 *            : nome do usuario.
	 * @return: retorna a string verificada.
	 * @throws InfoUsuarioException
	 *             : Excecao lancada caso a string esteja vazia.
	 */
	public String verificaNome(String nome) throws InfoUsuarioException {
		if (nome.equals("")) {
			throw new InfoUsuarioException(
					"Nome dx usuarix nao pode ser vazio.");
		} else if (nome.startsWith(" ")) {
			throw new InfoUsuarioException(
					"Nome dx usuarix nao pode ser vazio.");
		} else {
			return nome;
		}
	}

	/**
	 * Metodo utilizado para verificar a string de email do usuario.
	 * 
	 * @param nome
	 *            : email do usuario.
	 * @return: retorna a string verificada.
	 * @throws InfoUsuarioException
	 *             : Excecao lancada caso a string nao esteja no formato
	 *             correto.
	 */
	public String verificaEmail(String email) throws InfoUsuarioException {
		if (((email.endsWith(".com") || (email.endsWith(".com.br"))) && (email
				.matches("(.*)@(.*)")) == true)) {
			return email;
		} else {
			throw new InfoUsuarioException("Formato de e-mail esta invalido.");
		}
	}

	/**
	 * Metodo utilizado para verificar a string de senha do usuario.
	 * 
	 * @param senha
	 *            : nome do usuario.
	 * @return: retorna a string verificada.
	 * @throws InfoUsuarioException
	 *             : Excecao lancada caso a string esteja vazia.
	 */
	public String verificaSenha(String senha) throws InfoUsuarioException {
		if (senha.equals("")) {
			throw new InfoUsuarioException(
					"Senha dx usuarix nao pode ser vazia.");
		} else if (senha.startsWith(" ")) {
			throw new InfoUsuarioException(
					"Senha dx usuarix nao pode ser vazia.");
		} else {
			return senha;

		}
	}

	/**
	 * Metodo utilizado para verificar a string de imagem do usuario.
	 * 
	 * @param imagem
	 *            : imagem do usuario.
	 * @return: retorna a string verificada.
	 * @throws InfoUsuarioException
	 *             : Excecao lancada caso a string esteja vazia.
	 */
	public String verificaImagem(String imagem) throws InfoUsuarioException {
		if (imagem.equals("")) {
			throw new InfoUsuarioException(
					"Imagem dx usuarix nao pode ser vazia.");
		} else if (imagem.startsWith(" ")) {
			throw new InfoUsuarioException(
					"Imagem dx usuarix nao pode ser vazia.");
		} else {
			return imagem;

		}
	}

}
