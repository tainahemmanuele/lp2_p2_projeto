/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;

import Pop.Controller;
import Pop.Exceptions.DataException;
import Pop.Exceptions.PostException;
import Pop.Post.ArquivosPost.Arquivo;
import Pop.Post.ArquivosPost.Audio;
import Pop.Post.ArquivosPost.Imagem;
import Pop.Post.ArquivosPost.Texto;
import Util.FormataData;

/**
 * Classe com o objetivo de fatorar o post, testar as hashtags, a data. Separar
 * a string de texto em texto, arquivo e hashtags.
 * 
 * @author Tainah Emmanuele
 *
 */
public class FactoryPost {
	private ArrayList<Arquivo> conteudoPost;
	private String mensagemPost;
	private ArrayList<String> hashtags;
	private String hashtag;

	/**
	 * Construtor da classe FactoryPost.
	 */
	public FactoryPost() {

	}

	/**
	 * Metodo utilizado para criar o Post. Instancia um objeto do tipo Post.
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
	public Post criaPost(String texto, LocalDateTime data)
			throws DataException, PostException {
		this.conteudoPost = new ArrayList<Arquivo>();
		this.hashtags = new ArrayList<String>();
		separaString(texto);
		Post post = new Post(conteudoPost, mensagemPost, data,
				testaHashtag(hashtag));
		return post;
	}

	/**
	 * Metodo utilizado para testar as hashtags do post e adicionar na lista de
	 * hashtags do pot.
	 * 
	 * @param hashtag
	 *            : hashtag do post.
	 * @return: retorna um ArrayList de string com as hashtags.
	 * @throws PostException
	 */
	public ArrayList<String> testaHashtag(String hashtag) throws PostException {
		if (hashtag.isEmpty()) {
			return hashtags;
		}
		String[] novaHashtag = hashtag.split(" ");
		for (int i = 0; i < novaHashtag.length; i++) {
			if (novaHashtag[i].startsWith("#")) {
				hashtags.add(novaHashtag[i]);
			} else {
				throw new PostException(
						"Nao eh possivel criar o post. As hashtags devem comecar com '#'. Erro na hashtag: "
								+ "'" + novaHashtag[i] + "'.");
			}
		}
		return hashtags;

	}

	public ArrayList<String> getHashtags() {
		return hashtags;
	}

	/**
	 * Metodo utilizado para adicionar o conteudo dentro do ArrayList de
	 * conteudos do Post. Chama o metodo formataArquivo para formatar o arquivo
	 * e instancia um objeto do tipo imagem, caso seja arquivo de imagem, e
	 * audio, caso seja arquivo de audio.
	 * 
	 * @param arquivoMensagem
	 *            : Arquivo do post que sera formatado e adicionado na lista.
	 */
	public void adicionaConteudo(String arquivoMensagem) {
		String[] separaArquivo = arquivoMensagem.split(" ");
		for (int i = 0; i < separaArquivo.length; i++) {
			String arquivoFormatado = "";
			if (separaArquivo[i].contains("<imagem>")) {
				String arquivo = separaArquivo[i];
				arquivoFormatado = formataArquivo(arquivo);
				Arquivo imagem = new Imagem(arquivoFormatado);
				conteudoPost.add(imagem);
			}
			if (separaArquivo[i].contains("<audio>")) {
				String arquivo = separaArquivo[i];
				arquivoFormatado = formataArquivo(arquivo);
				Arquivo audio = new Audio(arquivoFormatado);
				conteudoPost.add(audio);
			}
		}

	}

	/**
	 * Metodo que separa a mensagem do texto em : texto, arquivo, hashtags.
	 * Chama o metodo que calcula o tamanho do post. Instancia um objeto do tipo
	 * texto.
	 * 
	 * @param mensagem
	 *            : mensgaem do post, sem a data.
	 * @return: retorna o texto do post.
	 * @throws PostException
	 *             : retorna uma excecao caso o post possua mais que 200
	 *             caracteres.
	 */
	public String separaString(String mensagem) throws PostException {
		String texto = "";
		String arquivo = "";
		String hashtag = "";
		String mensagemPost = "";

		if (mensagem.contains("<")) {
			texto = mensagem.substring(0, mensagem.indexOf(" <"));
			arquivo = mensagem.substring(mensagem.indexOf("<"),
					mensagem.lastIndexOf(">") + 1);
		}
		if (mensagem.contains("#")) {
			hashtag = mensagem.substring(mensagem.indexOf("#"),
					mensagem.length());
			mensagemPost = mensagem.substring(0, mensagem.indexOf(" #"));
		} else {
			mensagemPost = mensagem.substring(0, mensagem.length());
		}
		this.hashtag = hashtag;

		this.mensagemPost = mensagemPost;
		Arquivo textoPost = new Texto(texto);
		tamanhoString(mensagem, hashtag, arquivo);
		conteudoPost.add(textoPost);
		adicionaConteudo(arquivo);
		return texto;

	}

	/**
	 * Metodo que calcula o tamanho do post.
	 * 
	 * @param mensagem
	 *            : mensagem do post;
	 * @param hashtag
	 *            : hashtag do post;
	 * @param arquivo
	 *            : arquivo do post.
	 * @return: retorna o tamanho do post.
	 * @throws PostException
	 *             : Se a quantidade de caractres for mais que 200, o post nao
	 *             sera criado.
	 */
	public int tamanhoString(String mensagem, String hashtag, String arquivo)
			throws PostException {
		int tamanhoString = mensagem.length()
				- (hashtag.length() + arquivo.length());
		if ((tamanhoString > 200)) {
			throw new PostException(
					"Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres.");
		} else {
			return tamanhoString;
		}

	}

	/**
	 * Metodo que formata a string de arquivo.
	 * 
	 * @param arquivo
	 *            : arquivo a ser formatado.
	 * @return: retorna a string formatada.
	 */
	public String formataArquivo(String arquivo) {
		String arquivoFormatado = arquivo.substring(arquivo.indexOf(">") + 1,
				arquivo.lastIndexOf("</"));
		return arquivoFormatado;
	}

}
