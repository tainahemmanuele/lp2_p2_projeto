package Pop;

public class Normal implements Popularidade{
	public static final int POP_PONTOS = 10;
	
	public Normal() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public int adicionaPop(Post post) {
		post.curtirPost(POP_PONTOS);
		return POP_PONTOS;
	}

	@Override
	public int diminuiPop(Post post) {
		post.rejeitaPost(POP_PONTOS);
		return POP_PONTOS;
	}

}
