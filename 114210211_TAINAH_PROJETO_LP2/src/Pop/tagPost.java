/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop;



public class tagPost implements Comparable<tagPost> {
	private String hashtag;
	private int ocorrencia;
	
	public tagPost(String hashtag, int ocorrencia) {
		this.hashtag = hashtag;
		this.ocorrencia = ocorrencia;
	}

	public String getHashtag() {
		return hashtag;
	}

	public int getOcorrencia() {
		return ocorrencia;
	}
	
	

	public void adicionaOcorrencia() {
		this.ocorrencia += 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hashtag == null) ? 0 : hashtag.hashCode());
		result = prime * result + ocorrencia;
		return result;
	}

	public boolean equals(Object obj) {
		if (obj instanceof tagPost) {

			tagPost tagPost= (tagPost) obj;
			if (tagPost.getHashtag().equals(this.getHashtag())) {
				return true;

			} else {

				return false;
			}
		}
		return false;
	}


	@Override
	public String toString() {
		return hashtag+": "+ ocorrencia;
	}
	 
	public int compareTo(tagPost tag){
		if(Integer.compare(ocorrencia, tag.getOcorrencia())==0){
			return -hashtag.compareTo(tag.hashtag);
		}
			return -Integer.compare(ocorrencia, tag.getOcorrencia());
		
	}
	

}
