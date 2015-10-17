package Pop;

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

import Pop.Exceptions.DataException;
import Pop.Exceptions.PostException;
import Util.Util;

public class Mural {
   private ArrayList <String> posts;
   private FactoryPost factory;
   private Post post;
   private ArrayList<String> postagem;
   private ArrayList<String> mensagens;
   private ArrayList<String> hashtags;
   private ArrayList<ArrayList<String>>conteudoPost;
    private Util util;




    
	public Mural(){
		this.factory = new FactoryPost();
		this.posts = new ArrayList<String>();
		this.hashtags = new ArrayList<String>();
		this.conteudoPost = new ArrayList<ArrayList<String>>();
		this.util = new Util();
		this.postagem = new ArrayList<String>();
	}
	
	public void criaPost(String texto, String data)throws PostException, ParseException{
	  LocalDateTime dataPost =util.converteHoraData(data);
      this.post = factory.criaPost(texto, testaData(dataPost));
   	  posts.add(texto+" "+"("+testaData(dataPost)+")");
	}
	
	public String getPost(int numeroPost){
		return posts.get(numeroPost);
	}
	
	public String getPost (String atributo, int numeroPost){
		if(atributo.equals("Mensagem")){
			return post.getMensagemPost().get(numeroPost);
			
		}
		else if (atributo.equals("Data")){
			return post.getDatas().get(numeroPost);
		}
		else if (atributo.equals("Hashtags")){
			return post.getHashtags().get(numeroPost);
		}
		return null;
	}
	
	
	 public String getConteudoPost(int indicePost, int numeroPost) throws PostException{
	  if(indicePost >= 0){
	     if (!(post.getPostsConteudos().get(numeroPost).size() == indicePost)){
	   		   return post.getPostsConteudos().get(numeroPost).get(indicePost);
	      }else{
	    	  throw new PostException("Item #"+indicePost+" nao existe nesse post, ele possui apenas "+ indicePost +" itens distintos.");

		   
	   }
	  }else{
		 throw new PostException ("Requisicao invalida. O indice deve ser maior ou igual a zero.");
	  }
	 }

	
	public String testaData(LocalDateTime dataPost) {
		if (dataPost.getSecond() ==0){
			return dataPost.toString().replace("T", " ") + ":00";
		}else{
			return dataPost.toString();
		}

	
	}
	
}
