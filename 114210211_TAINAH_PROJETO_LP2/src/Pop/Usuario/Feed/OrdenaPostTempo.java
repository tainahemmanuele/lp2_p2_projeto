package Pop.Usuario.Feed;

import java.util.Collections;
import java.util.List;

import Pop.Post.Post;

public class OrdenaPostTempo implements OrdenaPost {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9110374957443369911L;

	@Override
	public void ordenaListaFeed(List<Post> posts) {
		Collections.sort(posts);
		Collections.reverse(posts);
	}

}
