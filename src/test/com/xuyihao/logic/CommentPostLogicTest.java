package test.com.xuyihao.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.dao.PostsDao;
import com.xuyihao.entity.CommentPost;
import com.xuyihao.entity.Posts;
import com.xuyihao.logic.CommentPostLogic;
import com.xuyihao.tools.utils.RandomUtils;

import test.com.xuyihao.CommonTest;

public class CommentPostLogicTest extends CommonTest {
	private static List<String> Comm_IDList = new ArrayList<>();
	private static String Post_ID = null;

	@Autowired
	private CommentPostLogic commentPostLogic;

	@Autowired
	private PostsDao postsDao;

	public void setCommentPostLogic(CommentPostLogic commentPostLogic) {
		this.commentPostLogic = commentPostLogic;
	}

	public void setPostsDao(PostsDao postsDao) {
		this.postsDao = postsDao;
	}

	@Before
	public void setUp() throws Exception {
		Comm_IDList.clear();
		Post_ID = RandomUtils.getRandomString(15) + "Crs";
		Posts post = new Posts();
		post.setPost_ID(Post_ID);
		post.setPost_name(RandomUtils.getRandomString(12));
		this.postsDao.savePosts(post);
		for (int i = 0; i < 10; i++) {
			CommentPost commentPost = new CommentPost();
			commentPost.setAcc_ID(RandomUtils.getRandomString(15) + "Acc");
			commentPost.setComm_desc(RandomUtils.getRandomString(23));
			commentPost.setPost_ID(Post_ID);
			commentPost.setRep_ID(RandomUtils.getRandomString(15) + "Acc");
			String Comm_ID = this.commentPostLogic.saveCommentPost(commentPost);
			Comm_IDList.add(Comm_ID);
		}
	}

	@Test
	public void testSaveCommentPost() {
		for (int i = 0; i < 10; i++) {
			CommentPost commentPost = new CommentPost();
			commentPost.setAcc_ID(RandomUtils.getRandomString(15) + "Acc");
			commentPost.setComm_desc(RandomUtils.getRandomString(23));
			commentPost.setPost_ID(Post_ID);
			commentPost.setRep_ID(RandomUtils.getRandomString(15) + "Acc");
			String Comm_ID = this.commentPostLogic.saveCommentPost(commentPost);
			Assert.assertNotSame("", Comm_ID);
		}
	}

	@Test
	public void testDeleteCommentPost() {
		Posts po = this.postsDao.queryById(Post_ID);
		System.out.println(po.getPost_comm());
		for (String id : Comm_IDList) {
			boolean flag = this.commentPostLogic.deleteCommentPost(id);
			Assert.assertEquals(true, flag);
		}
		Posts poNew = this.postsDao.queryById(Post_ID);
		System.out.println(poNew.getPost_comm());
	}

	@Test
	public void testGetCommentInfo() {
		for (String id : Comm_IDList) {
			CommentPost comm = this.commentPostLogic.getCommentInfo(id);
			Assert.assertNotSame("", comm.getComm_ID());
			System.out.println(comm.toJSONString());
		}
	}
}
