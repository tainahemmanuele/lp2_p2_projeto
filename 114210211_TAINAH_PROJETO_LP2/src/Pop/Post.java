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
   private Util util = new Util();
   private ArrayList<String> conteudoPost;
   private ArrayList<String> mensagemPost;
   private ArrayList<String> datas;
   private ArrayList<String> hashtags;
   private ArrayList <ArrayList<String>> postsConteudos;


   


	
	public Post (ArrayList <ArrayList<String>> postsConteudos,ArrayList<String> mensagemPost,ArrayList<String> datas,ArrayList<String> hashtags) throws PostException, DataException{
		this.mensagemPost = mensagemPost;
		this.datas = datas;
		this.hashtags = hashtags;	
		this.postsConteudos = postsConteudos;

	}
	


	public ArrayList<ArrayList<String>> getPostsConteudos() {
		return postsConteudos;
	}


	public void limpaconteudo(){
		conteudoPost.clear();
	}


	public void testaHashtag(String hashtag) throws PostException{
		String [] novaHashtag= hashtag.split(" ");
		String hashtagNova="";
		for (int i=0 ; i< novaHashtag.length; i++){
			//System.out.println(novaHashtag[i]);
			if(novaHashtag[i].startsWith("#")){
				boolean status = true;
				hashtagNova += novaHashtag[i]+",";
			}else{
				throw new PostException ("Nao eh possivel criar o post. As hashtags devem comecar com '#'. Erro na hashtag: "+ "'" + novaHashtag[i]+"'.");
			}
		}
		hashtags.add(hashtagNova.substring(0, hashtagNova.length() -1));
		
	}
	
	
	public ArrayList<String> getConteudoPost() {
		return conteudoPost;
	}




	public ArrayList<String> getMensagemPost() {
		return mensagemPost;
	}




	public ArrayList<String> getDatas() {
		return datas;
	}




	public ArrayList<String> getHashtags() {
		return hashtags;
	}



	


	
	
	
}
