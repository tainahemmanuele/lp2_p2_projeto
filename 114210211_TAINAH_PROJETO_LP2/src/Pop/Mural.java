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
   private ArrayList <Post> posts;
   private FactoryPost factory;
   private Post post;
   private Util util;




    
	public Mural(){
		this.factory = new FactoryPost();
		this.posts = new ArrayList<Post>();;
		this.util = new Util();
	}
	
	public void criaPost(String texto, String data)throws PostException, ParseException{
	  LocalDateTime dataPost =util.converteHoraData(data);
      this.post = factory.criaPost(texto, dataPost);
   	  posts.add(post);
	}
	
	public String getPost(int numeroPost){
		return posts.get(numeroPost).getPost();
	}
	
	public String getPost (String atributo, int numeroPost){
		Post post = posts.get(numeroPost);
		if(atributo.equals("Mensagem")){
			return post.getMensagemPost();
			
		}
		else if (atributo.equals("Data")){
			return post.getData().toString();
		}
		else if (atributo.equals("Hashtags")){
			return post.getHashtags();
		}
		return null;
	}
	
	
	 public String getConteudoPost(int indicePost, int numeroPost) throws PostException{
			Post post = posts.get(numeroPost);
	  if(indicePost >= 0){
	     if (!(post.getConteudoPost().size() == indicePost)){
	    	 return  post.getConteudoPost().get(indicePost);
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
