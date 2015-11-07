/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Usuario;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omg.Messaging.SyncScopeHelper;

import Pop.Exceptions.CurtidasException;
import Pop.Exceptions.DataException;
import Pop.Exceptions.PostException;
import Pop.Post.FactoryPost;
import Pop.Post.Post;
import Pop.Post.ArquivosPost.Arquivo;
import Util.FormataData;

public class Mural implements Serializable{
	private ArrayList<Post> posts;
	private FactoryPost factory;
	private Post post;
	private FormataData formataData;

	public Mural() {
		this.factory = new FactoryPost();
		this.posts = new ArrayList<Post>();
		this.formataData = new FormataData();
	}

	public void criaPost(String texto, String data) throws PostException,
			DataException {
		LocalDateTime dataPost = formataData.converteHoraData(data);
		this.post = factory.criaPost(texto, dataPost);
		posts.add(post);
	}

	public Post getPost(int numeroPost) {
		return posts.get(numeroPost);
	}
	
	public ArrayList<String> getHashtags(){
		ArrayList<String> hashtags = posts.get(posts.size()-1).getHashtags();
		return hashtags;
	}

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

	public int getPopsPost(int numeroPost){
		return posts.get(numeroPost).getPopularidade();
	}
	
	public int qtdCurtidasDePost(int numeroPost) throws CurtidasException{
		if(numeroPost>=posts.size()){
			throw new CurtidasException("Post #"+numeroPost+" nao existe. Usuarix possui apenas "+ posts.size() +" post(s).");
		}
		return posts.get(numeroPost).getCurtidas();
	}
	
	public int qtdRejeicoesDePost(int numeroPost) throws CurtidasException{
		if(numeroPost>=posts.size()){
			throw new CurtidasException("Post #"+numeroPost+" nao existe. Usuarix possui apenas "+ posts.size() +" post(s).");
		}
		return posts.get(numeroPost).getRejeicoes();
	}
	

}
