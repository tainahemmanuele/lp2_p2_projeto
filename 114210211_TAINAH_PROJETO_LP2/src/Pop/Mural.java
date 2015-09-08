package Pop;

import java.util.ArrayList;

import Pop.Exceptions.PostException;

public class Mural {
    private ArrayList <String> posts;
    private String mensagem;
    
	public Mural(){
		this.posts = new ArrayList<String>();
	}
	
	public void criaPost(String mensagem, String data)throws PostException{
		System.out.println(mensagem);
		if (mensagem.length()<= 200){
		this.mensagem = mensagem + " "+"(" +data+ ")";
		     posts.add(this.mensagem);
		} else{
			throw new PostException ("Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres.");
			
		}
		
	}
	
	public String getPost(int numeroPost){
		return posts.get(numeroPost);
	}
}
