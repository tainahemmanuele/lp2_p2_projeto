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

public class FactoryPost {
	private ArrayList<Arquivo> conteudoPost;
	private String mensagemPost;
	private ArrayList<String> hashtags;
	private String hashtag;




	public FactoryPost() {

	}

	public Post criaPost(String texto, LocalDateTime data)
			throws DataException, PostException {
		this.conteudoPost = new ArrayList<Arquivo>();
		this.hashtags = new ArrayList<String>();
		separaString(texto);
		Post post = new Post (conteudoPost, mensagemPost, data,
				testaHashtag(hashtag));
		return post;
	}

	public ArrayList <String> testaHashtag(String hashtag) throws PostException {
		if (hashtag.isEmpty()){
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
				arquivoFormatado =  formataArquivo(arquivo);
				Arquivo audio = new Audio (arquivoFormatado);
				conteudoPost.add(audio);
			}
		}

	}

	public String separaString(String mensagem) throws PostException {
		String texto = "";
		String arquivo = "";
		String hashtag = "";
		String mensagemPost = "";

		
		if (mensagem.contains("<")) {
			texto = mensagem.substring(0, mensagem.indexOf(" <"));
		    arquivo = mensagem.substring(mensagem.indexOf("<"), mensagem.lastIndexOf(">")+1);
		}
		if (mensagem.contains("#")){
			hashtag = mensagem.substring(mensagem.indexOf("#"), mensagem.length());
			mensagemPost = mensagem.substring(0, mensagem.indexOf(" #"));
		}else{
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

	public String formataArquivo(String arquivo) {
		String arquivoFormatado= arquivo.substring(arquivo.indexOf(">") + 1,arquivo.lastIndexOf("</"));
		return arquivoFormatado;
	}
	


	
   

}
