/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Post;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Pop.tagPost;
import Pop.Exceptions.DataException;
import Pop.Exceptions.PostException;
import Pop.Post.ArquivosPost.Arquivo;
import Pop.Post.ArquivosPost.Audio;
import Pop.Post.ArquivosPost.Imagem;
import Util.FormataData;

/**
 * Classe criada com o objetivo de criar um objeto do tipo Post.
 * 
 * @author Tainah Emmanuele
 *
 */
public class Post implements Serializable, Comparable<Post> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8491555101033953415L;
	private ArrayList<Arquivo> conteudoPost;
	private String mensagemPost;
	private LocalDateTime data;
	private ArrayList<String> hashtags;
	private int popularidade;
	private int curtidas;
	private int rejeicoes;

	/**
	 * Construtor de Post.
	 * 
	 * @param conteudoPost
	 *            : conteudo do post (texto, arquivos);
	 * @param mensagemPost
	 *            : mensagem de texto do post, sem as hashtags;
	 * @param data
	 *            : data do post;
	 * @param hashtags
	 *            : lista de hashtags do post.
	 */
	public Post(ArrayList<Arquivo> conteudoPost, String mensagemPost,
			LocalDateTime data, ArrayList<String> hashtags) {
		this.mensagemPost = mensagemPost;
		this.data = data;
		this.hashtags = hashtags;
		this.conteudoPost = conteudoPost;
		this.popularidade = 0;
		this.curtidas = 0;
		this.rejeicoes = 0;

	}

	public ArrayList<Arquivo> getConteudoPost() {
		return conteudoPost;
	}

	public String getMensagemPost() {
		return mensagemPost;
	}

	public LocalDateTime getDataLocalDate() {
		return data;
	}

	public String getData() {
		if (data.getSecond() == 0) {
			return data.toString().replace("T", " ") + ":00";
		} else {
			return data.toString().replace("T", " ");
		}

	}

	public ArrayList<String> getHashtags() {
		return hashtags;
	}

	public String hashtagPost() {
		return hashtag().replace(" ", ",");
	}

	/**
	 * Metodo utilizado para adicionar pontos (pops) ao post e curtir o post.
	 * 
	 * @param pontos
	 *            : pontos pops do post.
	 */
	public void curtirPost(int pontos) {
		this.curtidas += 1;
		this.popularidade += pontos;
	}

	/**
	 * Metodo utilizado para diminuir pontos (pops) ao post e curtir o post.
	 * 
	 * @param pontos
	 *            : pontos pops do post.
	 */
	public void rejeitaPost(int pontos) {
		this.rejeicoes += 1;
		this.popularidade -= pontos;
	}

	/**
	 * Metodo utilizado para adicionar a hashtag ao post, ao curtir/descurtir o
	 * mesmo. Metodo valido para usuario que curtem o post que sao do tipo Icone
	 * Pop.
	 * 
	 * @param hashtag
	 *            : hashtag a ser adicionada ao post.
	 */
	public void adicionaHashtag(String hashtag) {
		hashtags.add(hashtag);

	}

	public String hashtag() {
		String hashtagNova = "";
		for (int i = 0; i < getHashtags().size(); i++) {
			hashtagNova += getHashtags().get(i) + " ";
		}

		return hashtagNova.substring(0, hashtagNova.length() - 1);

	}

	public int getPopularidade() {
		return popularidade;
	}

	@Override
	public String toString() {
		return mensagemPost + " " + hashtag() + " " + "(" + getData() + ")";
	}

	public String toStringExtra() {
		final String QUEBRA_LINHA = System.getProperty("line.separator");
		String imagem = "";
		String audio = "";
		for (int i = 1; i < getConteudoPost().size(); i++) {
			if (getConteudoPost().get(i) instanceof Imagem) {
				imagem += getConteudoPost().get(i).getArquivo()+QUEBRA_LINHA;
			} else if (getConteudoPost().get(i) instanceof Audio) {
				audio += getConteudoPost().get(i).getArquivo()+QUEBRA_LINHA;
			}
		}

		return getData() + QUEBRA_LINHA + "Conteudo:" + QUEBRA_LINHA
				+ mensagemPost + QUEBRA_LINHA + imagem + audio + hashtag().replace(',', ' ')+ QUEBRA_LINHA + "+Pop: "+ getPopularidade();

	}

	public int getCurtidas() {
		return curtidas;
	}

	public int getRejeicoes() {
		return rejeicoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((mensagemPost == null) ? 0 : mensagemPost.hashCode());
		return result;
	}

	/**
	 * Equals de post. Dois posts sao iguais se possuem a mesma mensagem e a
	 * mesma data.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Post) {

			Post post = (Post) obj;
			if (post.getMensagemPost().equals(this.getMensagemPost())
					&& post.getData() == this.getData()) {
				return true;

			} else {

				return false;
			}
		}
		return false;
	}

	public int compareTo(Post post) {
		return data.compareTo(post.getDataLocalDate());

	}

}
