package Pop.Usuario.Feed;

import java.io.Serializable;
import java.util.List;

import Pop.Post.Post;

public interface OrdenaPost extends Serializable {

	
	public void ordenaListaFeed(List <Post> posts);
}
