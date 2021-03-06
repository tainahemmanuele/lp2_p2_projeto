/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Post;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Pop.Post.ArquivosPost.Arquivo;
import Pop.Post.ArquivosPost.Audio;
import Pop.Post.ArquivosPost.Imagem;

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

	/**
	 * Metodo utilizado para mudar a formatacao da data do post.
	 * 
	 * @return: Data do post em nova formatacao.
	 */
	public String getDataExtra() {
		String[] pedacosData = getData().split(" ");
		String[] pedacos = pedacosData[0].split("-");
		return pedacos[2] + "/" + pedacos[1] + "/" + pedacos[0] + " "
				+ pedacosData[1];
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

	/**
	 * Metodo utilizado para retornar as hashtags em formato de String.
	 * 
	 * @return: Hashtags em formato de String.
	 */
	public String hashtag() {
		String hashtagNova = "";
		for (int i = 0; i < getHashtags().size(); i++) {
			if (i == getHashtags().size() - 1) {
				hashtagNova += getHashtags().get(i);

			} else {
				hashtagNova += getHashtags().get(i) + " ";
			}
		}

		return hashtagNova;

	}

	/**
	 * Metodo utilizado para ver a quantidade de pops de um post.
	 * 
	 * @return: Quantidade de pops de um post.
	 */
	public int getPopularidade() {
		return popularidade;
	}

	@Override
	public String toString() {
		return mensagemPost + " " + hashtag() + " " + "(" + getData() + ")";
	}

	/**
	 * toString utilizado para salvar o post em um arquivo.Retorna o post no
	 * formato de String.
	 * 
	 * @return: Post formatado.
	 */
	public String toStringExtra() {
		String post = "";
		final String QUEBRA_LINHA = System.getProperty("line.separator");
		String imagem = "";
		String audio = "";
		for (int i = 1; i < getConteudoPost().size(); i++) {
			if (getConteudoPost().get(i) instanceof Imagem) {
				imagem += "<imagem>" + getConteudoPost().get(i).getArquivo()
						+ "</imagem>" + QUEBRA_LINHA;
			} else if (getConteudoPost().get(i) instanceof Audio) {
				audio += "<audio>" + getConteudoPost().get(i).getArquivo()
						+ "</audio>" + QUEBRA_LINHA;
			}
		}

		if (imagem.equals("") && audio.equals("") && hashtag().equals("")) {
			post = getDataExtra() + QUEBRA_LINHA + "Conteudo:" + QUEBRA_LINHA
					+ conteudoPost.get(0) + QUEBRA_LINHA + "+Pop: "
					+ getPopularidade();
		} else {
			post = getDataExtra() + QUEBRA_LINHA + "Conteudo:" + QUEBRA_LINHA
					+ conteudoPost.get(0) + QUEBRA_LINHA + imagem + audio
					+ hashtag().replace(',', ' ') + QUEBRA_LINHA + "+Pop: "
					+ getPopularidade();
		}
		return post;

	}

	/**
	 * Metodo que retorna a quantidade de curtidas do post.
	 * 
	 * @return: quantidade de curtidas.
	 */
	public int getCurtidas() {
		return curtidas;
	}

	/**
	 * Metodo que retorna a quantidade de rejeicoes do post.
	 * 
	 * @return: quantidade de rejeicoes.
	 */
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

	/**
	 * Metodo utilizado para comparar dois objetos do tipo Post. Um objeto Post
	 * e maior do que o outro se for mais antigo do que o outro Post.
	 */
	public int compareTo(Post post) {
		return data.compareTo(post.getDataLocalDate());

	}

}
