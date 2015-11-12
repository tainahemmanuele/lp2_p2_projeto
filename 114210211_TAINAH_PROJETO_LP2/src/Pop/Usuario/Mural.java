/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Usuario;

import java.io.Serializable;

import java.time.LocalDateTime;

import java.util.ArrayList;


import Pop.Exceptions.CurtidasException;
import Pop.Exceptions.DataException;
import Pop.Exceptions.PostException;
import Pop.Post.FactoryPost;
import Pop.Post.Post;

import Util.FormataData;

/**
 * Classe criada com o objetivo de criar o post,e gerencia-lo. A classe Mural
 * armazena todas as informacoes dos posts do usuario.
 * 
 * @author Tainah Emmanuele
 *
 */
public class Mural implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3090356912365039204L;
	private ArrayList<Post> posts;
	private FactoryPost factory;
	private Post post;
	private FormataData formataData;

	/**
	 * Construtor da classe Mural.
	 */
	public Mural() {
		this.factory = new FactoryPost();
		this.posts = new ArrayList<Post>();
		this.formataData = new FormataData();
	}

	/**
	 * Metodo utilizado para criar um post.
	 * 
	 * @param texto
	 *            : texto do post.
	 * @param data
	 *            : data do post.
	 * @throws PostException
	 *             : Excecao lancada caso a mensagem do Post apresente algum
	 *             problema: hashtags sem # , por exemplo.
	 * @throws DataException
	 *             : Excecao lancada caso a data do post apresente problemas de
	 *             validacao e formatacao.
	 */
	public void criaPost(String texto, String data) throws PostException,
			DataException {
		LocalDateTime dataPost = formataData.converteHoraData(data);
		this.post = factory.criaPost(texto, dataPost);
		posts.add(post);
	}

	/**
	 * Metodo utilizado para ver um determinado post criado no +Pop.
	 * 
	 * @param numeroPost
	 *            : numero do Post que se deseja ver.
	 * @return retorna um objeto do tipo Post com o conteudo do post.
	 */
	public Post getPost(int numeroPost) {
		return posts.get(numeroPost);
	}

	/**
	 * Metodo utilizado para pehar as hashtags mais recentes do post.
	 * 
	 * @return: um ArrayList do tipo String com as hashtags.
	 */
	public ArrayList<String> getHashtags() {
		ArrayList<String> hashtags = posts.get(posts.size() - 1).getHashtags();
		return hashtags;
	}

	/**
	 * Metodo utilizado para ver determinadas partes de um post. Ex: data e
	 * mensagem do post.
	 * 
	 * @param atributo
	 *            : parte do post que se deseja ver. Ex: data.
	 * @param numeroPost
	 *            : numero do post que possui as informacoes.
	 * @return: retorna uma string com a informacao solicitada.
	 */
	public String getPost(String atributo, int numeroPost) {
		Post post = posts.get(numeroPost);
		if (atributo.equals("Mensagem")) {
			return post.getMensagemPost();

		} else if (atributo.equals("Data")) {
			return post.getData().toString();
		} else if (atributo.equals("Hashtags")) {
			return post.hashtagPost();
		}
		return null;
	}

	/**
	 * Metodo utilizado para ver o conteudo de um post. Ex: arquivo e o texto do
	 * post sem as hashtags.
	 * 
	 * @param indice
	 *            : indice da lista de conteudos do post;
	 * @param numeroPost
	 *            : numero do post que possui as informacoes.
	 * @return: retorna uma string com a informacao solicitada.
	 * @throws PostException
	 *             : Excecao lancada caso o indice seja maior que o tamanho da
	 *             lista de conteudos ou indice menor que zero.
	 */
	public String getConteudoPost(int indicePost, int numeroPost)
			throws PostException {
		Post post = posts.get(numeroPost);
		if (indicePost >= 0) {
			if (!(post.getConteudoPost().size() == indicePost)) {
				return post.getConteudoPost().get(indicePost).toString();
			} else {

				throw new PostException("Item #" + indicePost
						+ " nao existe nesse post, ele possui apenas "
						+ indicePost + " itens distintos.");
			}
		} else {
			throw new PostException(
					"Requisicao invalida. O indice deve ser maior ou igual a zero.");
		}
	}

	/**
	 * Metodo utilizado para ver quantidade de pops de um post.
	 * 
	 * @param numeroPost
	 *            : numero do post que se deseja ver a quantidade de pops.
	 * @return: retorna a quantidade de pops do post.
	 */
	public int getPopsPost(int numeroPost) {
		return posts.get(numeroPost).getPopularidade();
	}

	/**
	 * Metodo utilizado para ver a quantidade de curtidas de um post.
	 * 
	 * @param numeroPost
	 *            : numero do post que se deseja ver as curtidas.
	 * @return: retorna o numero de curtidas do post.
	 * @throws CurtidasException
	 *             :Excecao lancada caso se deseje ver as curtidas de um post
	 *             que nao existe.
	 */
	public int qtdCurtidasDePost(int numeroPost) throws CurtidasException {
		if (numeroPost >= posts.size()) {
			throw new CurtidasException("Post #" + numeroPost
					+ " nao existe. Usuarix possui apenas " + posts.size()
					+ " post(s).");
		}
		return posts.get(numeroPost).getCurtidas();
	}

	/**
	 * Metodo utilizado para ver a quantidade de rejeicoes de um post.
	 * 
	 * @param numeroPost
	 *            : numero do post que se deseja ver as rejeicoes.
	 * @return: retorna o numero de rejeicoes do post.
	 * @throws CurtidasException
	 *             :Excecao lancada caso se deseje ver as rejeicoes de um post
	 *             que nao existe.
	 */
	public int qtdRejeicoesDePost(int numeroPost) throws CurtidasException {
		if (numeroPost >= posts.size()) {
			throw new CurtidasException("Post #" + numeroPost
					+ " nao existe. Usuarix possui apenas " + posts.size()
					+ " post(s).");
		}
		return posts.get(numeroPost).getRejeicoes();
	}

	public ArrayList<Post> getPosts() {
		return posts;
	}

	
}
