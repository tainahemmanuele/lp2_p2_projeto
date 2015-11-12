package Pop.Usuario.Feed;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Pop.Post.Post;

public class OrdenaPostPopularidade implements OrdenaPost{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5852418309193277692L;

	@Override
	public void ordenaListaFeed(List<Post> posts) {
		Collections.sort(posts, new Comparator<Post>(){
			@Override
			public int compare(Post post1, Post post2) {
				return Integer.compare(post1.getPopularidade(),post2.getPopularidade());
			}
		});
		Collections.reverse(posts);
		
	}

}
