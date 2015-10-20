/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import Pop.Exceptions.DataException;
import Pop.Exceptions.PostException;
import Util.Util;

public class Post {
	private ArrayList<String> conteudoPost;
	private String mensagemPost;
	private LocalDateTime data;
	private String hashtags;
	private String texto;
	private int popularidade;

	public Post(String texto, ArrayList<String> conteudoPost,
			String mensagemPost, LocalDateTime data, String hashtags)
			throws PostException, DataException {
		this.mensagemPost = mensagemPost;
		this.data = data;
		this.hashtags = hashtags;
		this.conteudoPost = conteudoPost;
		this.texto = texto;
		this.popularidade = 0;

	}

	public String getPost() {
		return texto + " " + "(" + getData() + ")";
	}

	public ArrayList<String> getConteudoPost() {
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

	public String getHashtags() {
		return hashtags;
	}

	public void curtirPost() {
		this.popularidade += 10;
	}

	public void rejeitaPost() {
		if (this.popularidade >= 10) {
			this.popularidade -= 10;
		} else {
			this.popularidade = 0;
		}
	}

}
