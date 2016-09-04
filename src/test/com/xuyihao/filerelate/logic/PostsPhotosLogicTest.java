package test.com.xuyihao.filerelate.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.filerelate.logic.PostsPhotosLogic;
import com.xuyihao.tools.utils.RandomUtils;

import junit.framework.Assert;
import test.com.xuyihao.CommonTest;

public class PostsPhotosLogicTest extends CommonTest {
	@Autowired
	private PostsPhotosLogic postsPhotosLogic;

	public void setPostsPhotosLogic(PostsPhotosLogic postsPhotosLogic) {
		this.postsPhotosLogic = postsPhotosLogic;
	}

	@Test
	public void test() {
		for (int i = 0; i < 10; i++) {
			String Post_ID = RandomUtils.getRandomString(15) + "Post";
			String HeadPhoto_ID = RandomUtils.getRandomString(15) + "Photo";
			List<String> postPhotosList = new ArrayList<String>();
			for (int j = 0; j < 10; j++) {
				postPhotosList.add(RandomUtils.getRandomString(15) + "Photo");
			}
			Assert.assertEquals(true, this.postsPhotosLogic.savePostsPhotos(Post_ID, HeadPhoto_ID, postPhotosList));
			Assert.assertEquals(true, this.postsPhotosLogic.changePostsPhotosInfo(Post_ID, "KJHG", new ArrayList<String>()));
			System.out.println(this.postsPhotosLogic.getPostsPhotosInfo(Post_ID).toJSONString());
			System.out.println(this.postsPhotosLogic.getPostsHeadPhoto(Post_ID));
			this.postsPhotosLogic.getPostsOtherPhotos(Post_ID);
			Assert.assertEquals(true, this.postsPhotosLogic.deletePostsPhotos(Post_ID));
		}
	}
}