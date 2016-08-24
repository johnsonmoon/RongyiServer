package test.com.xuyihao.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.dao.PostsDao;
import com.xuyihao.entity.LikePost;
import com.xuyihao.entity.Posts;
import com.xuyihao.logic.LikePostLogic;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

import test.com.xuyihao.CommonTest;

public class LikePostLogicTest extends CommonTest {
	private static List<String> Like_IDList = new ArrayList<>();
	private static String Post_ID = null;

	@Autowired
	private LikePostLogic likePostLogic;

	@Autowired
	private PostsDao postsDao;

	public void setLikePostLogic(LikePostLogic likePostLogic) {
		this.likePostLogic = likePostLogic;
	}

	public void setPostsDao(PostsDao postsDao) {
		this.postsDao = postsDao;
	}

	@Test
	public void test() {
		Post_ID = RandomUtils.getRandomString(15) + "Post";
		Posts post = new Posts();
		post.setAcc_ID(RandomUtils.getRandomString(15) + "Acc");
		post.setAuthor_ID(RandomUtils.getRandomString(15) + "Acc");
		post.setPost_addTime(DateUtils.currentDateTime());
		post.setPost_ID(Post_ID);
		post.setPost_name(RandomUtils.getRandomString(6));
		post.setPost_route(RandomUtils.getRandomString(33));
		this.postsDao.savePosts(post);
		for (int i = 0; i < 5; i++) {
			LikePost likePost = new LikePost();
			likePost.setAcc_ID(RandomUtils.getRandomString(15) + "Acc");
			likePost.setLike_ryb(10);
			likePost.setPost_ID(Post_ID);
			likePost.setRep_ID(RandomUtils.getRandomString(15) + "Acc");
			String Like_ID = this.likePostLogic.saveLikePost(likePost);
			Assert.assertNotSame("", Like_ID);
			Like_IDList.add(Like_ID);
		}
		Posts po = this.postsDao.queryById(Post_ID);
		System.out.println(po.getPost_like());
		for (String id : Like_IDList) {
			LikePost likePost = this.likePostLogic.getLikePostInfo(id);
			Assert.assertNotSame(0, likePost.get_id());
			System.out.println(likePost.toJSONString());
		}
	}
}
