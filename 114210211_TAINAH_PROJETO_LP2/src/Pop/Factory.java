package Pop;

import Pop.Exceptions.PostException;

public class Factory {
	private Post post;
	
	public Factory(){
		
	}
	
	public Post criaPost(String texto, String arquivo, String hashtag, String dataPost) throws PostException{
		this.post = new Post(texto, arquivo, hashtag, dataPost);
		return post;
	}

	
	public String getDataPost() {
		return post.getDataPost();
	}

	public String getTexto() {
		return post.getTexto();
	}

	public String getHashtag() {
		return post.getHashtag();
	}

	public String getArquivo() {
		return post.getArquivo();
	}

	public String[] getNovaHashtag() {
		return post.getNovaHashtag();
	}
}
