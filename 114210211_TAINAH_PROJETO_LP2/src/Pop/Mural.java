package Pop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Pop.Exceptions.DataException;
import Pop.Exceptions.PostException;

public class Mural {
    private ArrayList <String> posts;
    private String mensagem;
   SimpleDateFormat data = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
   SimpleDateFormat data1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   private String dataPost;
    
	public Mural(){
		this.posts = new ArrayList<String>();
	}
	
	public void criaPost(String mensagem, String data)throws PostException, ParseException{
		if (mensagem.length() <= 200 ){;
			//this.dataPost = converteData(data);
		    this.mensagem = mensagem + " "+"(" +data+ ")";
		     posts.add(this.mensagem);
		} else{
			throw new PostException ("Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres.");
			
		}
		
	}
	
	public String getPost(int numeroPost){
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
}
