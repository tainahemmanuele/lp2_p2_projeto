package Pop.Post;

public class Hashtag {
	private String hashtag;
	private int relevancia;

	
	public Hashtag(String hashtag) {
		this.hashtag = hashtag;
		this.relevancia = 1;
	}
	
	public void adicionaRelevancia(){
		this.relevancia +=1;
	}

	public int getRelevancia() {
		return relevancia;
	}

	@Override
	public String toString() {
		return "Hashtag [hashtag=" + hashtag + ", relevancia=" + relevancia
				+ "]";
	}
	
	
	
}
