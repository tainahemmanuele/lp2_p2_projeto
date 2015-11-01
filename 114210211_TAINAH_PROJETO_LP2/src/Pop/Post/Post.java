/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Post;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import Pop.Exceptions.DataException;
import Pop.Exceptions.PostException;
import Pop.Post.ArquivosPost.Arquivo;
import Util.FormataData;

public class Post {
	private ArrayList<Arquivo> conteudoPost;
	private String mensagemPost;
	private LocalDateTime data;
	private ArrayList<String>hashtags;
	private int popularidade;
	private int curtidas;

	public Post(ArrayList<Arquivo> conteudoPost,
			String mensagemPost, LocalDateTime data, ArrayList<String> hashtags)
			throws PostException, DataException {
		this.mensagemPost = mensagemPost;
		this.data = data;
		this.hashtags = hashtags;
		this.conteudoPost = conteudoPost;
		this.popularidade = 0;
		this.curtidas = 0;

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
			return data.toString();
		}

	}
	
	public ArrayList<String> getHashtags() {
		return hashtags;
	}

	public String hashtagPost(){
		return hashtag().replace(" ", ",");
	}
	
	public void curtirPost(int pontos) {
		this.curtidas +=1;
		this.popularidade += pontos;
	}

	public void rejeitaPost(int pontos) {
		this.curtidas -=1;
		this.popularidade -=pontos;
	}

	public void adicionaHashtag(String hashtag) {
		hashtags.add(hashtag);
		
	}

	public String hashtag(){
		String hashtagNova = "";
		for (int i = 0; i < getHashtags().size(); i++) {
			hashtagNova+= getHashtags().get(i)+" ";
		}
			
		return hashtagNova.substring(0, hashtagNova.length() - 1);

	}

	public int getPopularidade() {
		return popularidade;
	}



	@Override
	public String toString() {
		return mensagemPost +" " + hashtag()+ " " + "(" + getData() + ")";
	}

	
}
