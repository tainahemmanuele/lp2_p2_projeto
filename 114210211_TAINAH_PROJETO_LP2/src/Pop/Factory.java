package Pop;

import java.time.LocalDateTime;

import Pop.Exceptions.DataException;
import Pop.Exceptions.PostException;

public class Factory {
	//Tainah, isso nao eh responsabilidade de Factory. Ela nao deve compor
	// e funcionar como uma criacao e um acesso... ela deve apenas criar.
	// Pode remover essa composicao, e usar o Mural para compor uma lista de posts.
	// Depois que o post eh criado, passa para o Mural, e jah coloca ele na lista.
	// Qualquer acesso a esse post criado deve ser feito na lista e nao pela Factory.
	private Post post;
	
	public Factory(){
		
	}
	
	public Post criaPost(String texto, String arquivo, String hashtag, LocalDateTime data) throws PostException, DataException{
		this.post = new Post(texto, arquivo, hashtag, data);
		return post;
	}
	

}
