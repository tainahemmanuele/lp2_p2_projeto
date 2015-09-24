package Pop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import Pop.Exceptions.DataException;
import Pop.Exceptions.PostException;

public class Mural {
   private ArrayList <Post> posts;
   private Post mensagem;
   private String data;
   SimpleDateFormat data1 = new SimpleDateFormat("dd/MM/yyyy");
   SimpleDateFormat data2 = new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat horaPost = new SimpleDateFormat("HH:mm:ss");
   private String dataPost;
   private String texto;
   private String hashtag;
   private String arquivo;
   private Factory factory;
   private Post post;
   private String mensagemPost;
   private ArrayList<String> mensagens;
   private String mensagemPostada;
   private ArrayList<String> hashtags;
   private String hashtagPostada;
   private HashMap<Integer,HashMap <Integer , String>> conteudoPosts;
   //private ArrayList<String> conteudoPostsArquivo;
   private HashMap <Integer , String> conteudos;
   private int numeroPost;
   private int indice;
   private String arquivoFormatado ;
   String [] separaArquivo;


    
	public Mural(){
		this.posts = new ArrayList<Post>();
		this.factory = new Factory();
		this.mensagens = new ArrayList<String>();
		this.hashtags = new ArrayList<String>();
		this.conteudoPosts = new HashMap<Integer,HashMap <Integer , String>> ();
		this.numeroPost = 0;
		this.indice = 0;
	}
	
	public void criaPost(String mensagem, String data)throws PostException, ParseException{
		this.texto = "";
		this.hashtag ="";
		this.arquivo = "";
		this.mensagemPost ="";
        this.data = converteData(data);
		this.conteudos = new HashMap<Integer , String>();
		String resultadoString = separaString(mensagem);
		int tamanhoString = tamanhoString(mensagem, texto, hashtag,arquivo);


		//System.out.println(numeroPost);
		if (tamanhoString<=200){
			this.mensagem = factory.criaPost(this.texto, this.arquivo, this.hashtag, this.data);
			posts.add(this.mensagem);
			mensagens.add(mensagemPost);
			hashtags.add(this.mensagem.getHashtagNova());
		} else{
			throw new PostException ("Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres.");
			
		}
		adicionaConteudo(this.mensagem);
		this.conteudoPosts.put(numeroPost, conteudos);
		//System.out.println(this.conteudoPosts.toString());
		numeroPost+=1;
		

	}
	
	public Post getPost(int numeroPost){
		return posts.get(numeroPost);
	}
	
	public String getPost (String atributo, int numeroPost){
	    post = posts.get(numeroPost);
	    mensagemPostada = mensagens.get(numeroPost);
	    //System.out.println(mensagens.get(numeroPost).g);
	    hashtagPostada = hashtags.get(numeroPost);
		if(atributo.equals("Mensagem")){
			return post.getTexto() + post.getArquivo();
			//return mensagemPostada;
			
		}
		if(atributo.equals("Data")){
			return post.getDataPost();
		}
		if(atributo.equals("Hashtags")){
			return hashtagPostada;
		}
		if(atributo.equals("Arquivo")){
			return post.getArquivo();
		}
		return null;
	}
	

	
	 public String separaString(String mensagem){
		 int contador = 0;
		 if(mensagem.contains("<") && mensagem.contains("#")){
		 for (int i =0; i < mensagem.indexOf(" <") ; i++){
				texto += mensagem.charAt(i);
			}
		 }
		 if(!(mensagem.contains("<")) && mensagem.contains("#")){
			 for (int i =0; i < mensagem.indexOf(" #") ; i++){
					texto += mensagem.charAt(i);
				}
			 }
		 if(mensagem.contains("<")){
				int tamanho = mensagem.length() - texto.length();
				 for (int i =mensagem.indexOf(" <"); i < mensagem.indexOf(" #"); i++){
						arquivo += mensagem.charAt(i);
					} 
			 
			}
		if(mensagem.contains("#")){
			int tamanho = mensagem.length() - (texto.length()+arquivo.length());
			 for (int i = mensagem.indexOf("#"); i < mensagem.lastIndexOf("") ; i++){
					hashtag += mensagem.charAt(i);
				} 
			 //System.out.println(tamanho +" "+mensagem.indexOf("#") );
		 }
		

		if(mensagem.contains("#")){
			int tamanho = mensagem.length() - (texto.length()+arquivo.length());
			 for (int i = 0; i < mensagem.indexOf(" #") ; i++){
					mensagemPost += mensagem.charAt(i);
				} 
			 //System.out.println(tamanho +" "+mensagem.indexOf("#") );
		 }
		

		//System.out.println(hashtag);
		//System.out.println(texto);
		 //System.out.println(mensagem.indexOf("<"));

		//System.out.println(tamanhoString(mensagem, novaString, novaString2));
		 return texto;
	 
	 }
	 
	


	 public String getConteudoPost(int indicePost, int numeroPost) throws PostException{
		 //System.out.println(conteudoPosts.get(numeroPost).containsKey(indice));
		 //System.out.println(conteudoPosts.get(numeroPost).get(indice));
	  if(indicePost >= 0){
	   if (conteudoPosts.get(numeroPost).containsKey(indicePost) == true){
		   return conteudoPosts.get(numeroPost).get(indicePost);  
	   }else{
		   throw new PostException("Item #"+indicePost+" nao existe nesse post, ele possui apenas "+ indicePost +" itens distintos.");
	   }
	  }else{
		 throw new PostException ("Requisicao invalida. O indice deve ser maior ou igual a zero.");
	  }
	 }


	public int tamanhoString(String mensagem, String texto, String hashtag,String arquivo){
	      return mensagem.length() - (hashtag.length()+arquivo.length());
	 }
	
	 public String converteData(String dataNascimento) throws ParseException {
		    data1.setLenient(true);
			data1.parse(dataNascimento);
			String[] s1 = dataNascimento.split(" ");
			String[] s =s1[0].split("/");
			String hora = validaHora(s1[1]);
			if ((s[0].length() == 2) && (s[1].length() == 2) && (s[2].length() == 4)){
				if ((s[0].matches("\\d+")) == true && (s[1].matches("\\d+")) == true && (s[2].matches("\\d+")) == true){
					validaData(s1[0]);
				}
				else{
					throw new DataException("Erro na atualizacao de perfil. Formato de data esta invalida.", 2);
				}
			}else{
				throw new DataException("Erro na atualizacao de perfil. Formato de data esta invalida.", 2);
			}
		return data2.format(data1.parse(s1[0]))+ " "+ hora;	
	}

	public String validaData(String dataNascimento) throws ParseException{
		try {  
		    Calendar dataValida = Calendar.getInstance();
		    dataValida.setLenient(true);
		    data1.setLenient(false);
			dataValida.setTime(data1.parse(dataNascimento));
	     } catch (ParseException e){  
	         throw new DataException("Erro na atualizacao de perfil. Data nao existe.",1);
	        }
		return dataNascimento;
	}
	
	public String validaHora(String hora) throws ParseException{
		try{
			horaPost.setLenient(false);
			horaPost.parse(hora);
		}catch(DataException e){
			throw new DataException("Hora invalida!",1);
		}
		return hora;
	}
	
	public void adicionaConteudo(Post post){
			conteudos.put(indice, post.getTexto());
			this.separaArquivo = post.getArquivo().split(" ");
			for (int i = 1; i<separaArquivo.length; i ++){
				indice+=1;
				arquivoFormatado ="";
				if(separaArquivo[i].contains("<imagem>")){
					String arquivo = separaArquivo[i];
					arquivoFormatado += "$arquivo_imagem:";
					for (int j = arquivo.indexOf(">")+1; j < arquivo.lastIndexOf("</") ; j++){
						arquivoFormatado += arquivo.charAt(j);
					}
					conteudos.put(indice, arquivoFormatado);
				}
				if(separaArquivo[i].contains("<audio>")){
					String arquivo = separaArquivo[i];
					arquivoFormatado += "$arquivo_audio:";
					for (int j = arquivo.indexOf(">")+1; j < arquivo.lastIndexOf("</") ; j++){
						arquivoFormatado += arquivo.charAt(j);
					}
					conteudos.put(indice, arquivoFormatado);
				}
	
		}

		
	}
	
	
	
	
}
