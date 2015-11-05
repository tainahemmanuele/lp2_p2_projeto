package Pop.Usuario.TipoUsuario;

import Pop.Post.Post;

public class IconePop implements Popularidade{
	public static final int POP_PONTOS = 50;
	public static final String HASHTAG_CURTIR = "#epicwin";
	public static final String HASHTAG_DESCURTIR= "#epicfail";
	

	public IconePop() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int adicionaPop(Post post) {
		post.curtirPost(POP_PONTOS);
		if (!post.getHashtags().contains(HASHTAG_CURTIR)){
		post.adicionaHashtag(HASHTAG_CURTIR);
		}
		return POP_PONTOS;
	}

	@Override
	public int diminuiPop(Post post) {
		post.rejeitaPost(POP_PONTOS);
		if (!post.getHashtags().contains(HASHTAG_DESCURTIR)){
		post.adicionaHashtag(HASHTAG_DESCURTIR);
		}
		return POP_PONTOS;
	}

	@Override
	public String toString() {
		return "Icone Pop";
	}

	
}
