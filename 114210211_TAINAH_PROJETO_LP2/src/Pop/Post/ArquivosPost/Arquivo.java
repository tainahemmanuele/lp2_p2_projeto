/* 114210211 - Tainah Emmanuele Silva: Projeto : +Pop - Turma 3 */
package Pop.Post.ArquivosPost;

public abstract class Arquivo {
	private String arquivo;
	
	public Arquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	@Override
	public String toString() {
		return arquivo;
	}

	public String getArquivo() {
		return arquivo;
	}
	
	
	
	

}
