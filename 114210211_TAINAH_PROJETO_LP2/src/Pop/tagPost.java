/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop;

import java.io.Serializable;

/**
 * Classe criada com o objetivo de criar um objeto do tipo tagPost. Possui o
 * construtor de tagPost.
 * 
 * @author Tainah Emmanuele
 *
 */
public class tagPost implements Comparable<tagPost>, Serializable {
	private String hashtag;
	private int ocorrencia;

	/**
	 * Construtor de tagPost. Recebe uma string que representa a hashtag de um
	 * post. Possui a ocorrencia que a hashtag aparece no sistema +Pop. Nesse
	 * caso, o valor da ocorrencia se inicia em 1.
	 * 
	 * @param hashtag
	 *            : hashtag do post.
	 */
	public tagPost(String hashtag) {
		this.hashtag = hashtag;
		this.ocorrencia = 1;
	}

	public String getHashtag() {
		return hashtag;
	}

	public int getOcorrencia() {
		return ocorrencia;
	}

	/**
	 * Adiciona a quantidade de vezes (ocorrencia) que a hashtag aparece no
	 * sistema +Pop. Toda vez que o meto e chamado , ele adiciona ao total de
	 * ocorrencias da tag, mais um.
	 */
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

	/**
	 * Equals de tagPost. Duas tags sao iguais se possuem a mesma String.
	 */
	public boolean equals(Object obj) {
		if (obj instanceof tagPost) {

			tagPost tagPost = (tagPost) obj;
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
		return hashtag + ": " + ocorrencia;
	}

	/**
	 * Metodo utilizado para comparar dois objetos do tipo tagPost. Um objeto
	 * tagPost e maior do que o outro se possuirem ocorrencias distintas, e a
	 * ocorrencia de a for maior que a de b. Se as ocorrencias forem iguais, o
	 * criterio de desempate e a hashtag (String) do objeto tagPost.
	 */
	public int compareTo(tagPost tag) {
		if (Integer.compare(ocorrencia, tag.getOcorrencia()) == 0) {
			return -hashtag.compareToIgnoreCase(tag.getHashtag());
		}
		return -Integer.compare(ocorrencia, tag.getOcorrencia());

	}

}
