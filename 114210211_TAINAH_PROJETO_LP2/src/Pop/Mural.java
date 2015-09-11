package Pop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Pop.Exceptions.DataException;
import Pop.Exceptions.PostException;

public class Mural {
    private ArrayList <Post> posts;
   private Post mensagem;
   SimpleDateFormat data = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
   SimpleDateFormat data1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   private String dataPost;
   private String texto;
   private String hashtag;
   private String arquivo;
   private Factory factory;


    
	public Mural(){
		this.posts = new ArrayList<Post>();
		this.factory = new Factory();
	}
	
	public void criaPost(String mensagem, String data)throws PostException, ParseException{
		this.texto = "";
		this.hashtag ="";
		this.arquivo = "";
	
		String resultadoString = separaString(mensagem);
		int tamanhoString = tamanhoString(mensagem, texto, hashtag,arquivo);

		if (tamanhoString<=200){
			//System.out.println(hashtag);
			this.mensagem = factory.criaPost(this.texto, this.arquivo, this.hashtag, data);
			//System.out.println(this.mensagem);
			posts.add(this.mensagem);

			System.out.println(posts.toString());
		} else{
			throw new PostException ("Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres.");
			
		}
		
	}
	
	public Post getPost(int numeroPost){
		return posts.get(numeroPost);
	}
	
	 public String converteData(String dataPost) throws ParseException {
		    data.setLenient(true);
			data.parse(dataPost);
			String[] s1 =dataPost.split(" ");
			String[] s = s1[0].split("/");
			String[] s3 = s1[1].split(":");
			if ((s[0].length() == 2) && (s[1].length() == 2) && (s[2].length() == 4) && (s3[0].length() ==2) && (s3[1].length()==2) && (s3[2].length()==2)){
				if ((s[0].matches("\\d+")) == true && (s[1].matches("\\d+")) == true && (s[2].matches("\\d+")) == true && (s3[0].matches("\\d+")) == true&& (s3[1].matches("\\d+")) == true && (s[2].matches("\\d+")) == true){
					String data2 = s[0]+"/"+s[1]+"/"+s[2];
					System.out.println(validaData(data2));
					validaData(data2);
				}
				else{
					throw new DataException("Erro na atualizacao de perfil. Formato de data esta invalida.", 2);
				}
			}else{
				throw new DataException("Erro na atualizacao de perfil. Formato de data esta invalida.", 2);
			}
		
		return data1.format(data.parse(dataPost));	
	}

	 public String validaData(String data3) throws ParseException{
			try {  
			    Calendar dataValida = Calendar.getInstance();
			    dataValida.setLenient(true);
			    data.setLenient(false);
				dataValida.setTime(data.parse(data3));
		     } catch (ParseException e){  
		         throw new DataException("Erro na atualizacao de perfil. Data nao existe.",1);
		        }
		return data3;
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
				 for (int i =mensagem.indexOf(" <"); i < mensagem.indexOf("#"); i++){
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
		


		//System.out.println(hashtag);
		//System.out.println(texto);
		 //System.out.println(mensagem.indexOf("<"));

		//System.out.println(tamanhoString(mensagem, novaString, novaString2));
		 return texto;
	 
	 }
	 
	


	

	public int tamanhoString(String mensagem, String texto, String hashtag,String arquivo){
	      return mensagem.length() - (hashtag.length()+arquivo.length());
	 }
}
