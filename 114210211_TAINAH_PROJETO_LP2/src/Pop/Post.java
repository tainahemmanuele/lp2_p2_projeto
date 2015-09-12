package Pop;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;

import Pop.Exceptions.PostException;

public class Post {
   private String dataPost;
   private String texto;
   private String hashtag;
   private String arquivo;
   private String [] novaHashtag;
   private String hashtagNova;
   private String mensagem;

	
	public Post (String texto, String arquivo, String hashtag, String dataPost) throws PostException{
		this.texto = texto;
		this.arquivo = arquivo;
		this.hashtag =testaHashtag(hashtag);
		this.dataPost = dataPost;

	}
	
	public String testaHashtag(String hashtag) throws PostException{
		novaHashtag= hashtag.split(" ");
		hashtagNova="";
		for (int i=0 ; i< novaHashtag.length; i++){
			//System.out.println(novaHashtag[i]);
			if(novaHashtag[i].startsWith("#")){
				boolean status = true;
				hashtagNova += novaHashtag[i]+",";
			}else{
				throw new PostException ("Nao eh possivel criar o post. As hashtags devem comecar com '#'. Erro na hashtag: "+ "'" + novaHashtag[i]+"'.");
			}
		}
		return hashtag;
		
	}
	
	

	public String getDataPost() {
		return dataPost;
	}

	public String getTexto() {
		return texto;
	}

	public String getHashtag() {
		return hashtag;
	}

	public String getArquivo() {
		return arquivo;
	}

	public String[] getNovaHashtag() {
		return novaHashtag;
	}

	
	public String getHashtagNova() {
		return hashtagNova.substring(0, hashtagNova.length() -1);
	}

	@Override
	public String toString() {
		return getTexto()+getArquivo()+" "+getHashtag()+" "+ "("+getDataPost()+")";
	}
	
	
}
