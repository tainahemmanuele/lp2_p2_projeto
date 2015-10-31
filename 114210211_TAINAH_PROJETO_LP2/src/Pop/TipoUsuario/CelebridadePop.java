package Pop.TipoUsuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Pop.Post.Post;

public class CelebridadePop implements Popularidade{
	public static final int POP_PONTOS = 25;
	public static final int POP_PONTOS_EXTRA = 10;
	
	
	
	public CelebridadePop() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int adicionaPop(Post post) {
		if(post.getDataLocalDate().toLocalDate().equals(LocalDate.now())){
			post.curtirPost(POP_PONTOS+POP_PONTOS_EXTRA);
			return POP_PONTOS+POP_PONTOS_EXTRA;
		}else{
			post.curtirPost(POP_PONTOS);
			return POP_PONTOS;
		}
	}

	@Override
	public int diminuiPop(Post post) {
		if(post.getDataLocalDate().toLocalDate().equals(LocalDate.now())){
			post.rejeitaPost(POP_PONTOS+POP_PONTOS_EXTRA);
			return POP_PONTOS+POP_PONTOS_EXTRA;
		}else{
			post.rejeitaPost(POP_PONTOS);
			return POP_PONTOS;
		}
	}

}
