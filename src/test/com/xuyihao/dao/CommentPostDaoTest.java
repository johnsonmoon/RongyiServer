package test.com.xuyihao.dao;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.com.xuyihao.CommonTest;

import com.xuyihao.dao.AccountsDao;
import com.xuyihao.dao.CommentPostDao;
import com.xuyihao.dao.PostsDao;
import com.xuyihao.entity.Accounts;
import com.xuyihao.entity.CommentPost;
import com.xuyihao.entity.Posts;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:22:44 PM.
 *
 */
public class CommentPostDaoTest extends CommonTest {
	private static List<String> Comm_IDList = new ArrayList<>();
	private static String Acc_ID = null;
	private static String Rep_ID = null;
	private static String Post_ID = null;

	@Autowired
	private AccountsDao accountsDao;

	@Autowired
	private CommentPostDao commentPostDao;

	@Autowired
	private PostsDao postsDao;

	public void setPostsDao(PostsDao postsDao) {
		this.postsDao = postsDao;
	}

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setCommentCrsDao(CommentPostDao commentPostDao) {
		this.commentPostDao = commentPostDao;
	}

	@Before
	public void setUp() {
		Comm_IDList.clear();
		Acc_ID = RandomUtils.getRandomString(15) + "Acc";
		Rep_ID = RandomUtils.getRandomString(15) + "Acc";
		Post_ID = RandomUtils.getRandomString(15) + "Post";
		Accounts accounts = new Accounts();
		accounts.setAcc_ID(Acc_ID);
		accounts.setAcc_name("Johnson" + RandomUtils.getRandomString(5));
		accounts.setAcc_pwd("moon" + RandomUtils.getRandomString(5));
		accounts.setAcc_sex("男");
		accounts.setAcc_loc("浙江工业大学屛峰校区");
		accounts.setAcc_addTime(DateUtils.currentDateTime());
		this.accountsDao.saveAccounts(accounts);
		Accounts accounts2 = new Accounts();
		accounts2.setAcc_ID(Rep_ID);
		accounts2.setAcc_name("Johnson" + RandomUtils.getRandomString(5));
		accounts2.setAcc_pwd("moon" + RandomUtils.getRandomString(5));
		accounts2.setAcc_sex("男");
		accounts2.setAcc_loc("浙江工业大学屛峰校区");
		accounts2.setAcc_addTime(DateUtils.currentDateTime());
		this.accountsDao.saveAccounts(accounts2);
		Posts post = new Posts();
		post.setPost_ID(Post_ID);
		post.setAcc_ID(Acc_ID);
		post.setAuthor_ID(Rep_ID);
		post.setPost_addTime(DateUtils.currentDateTime());
		post.setPost_name(RandomUtils.getRandomString(30));
		post.setPost_route(RandomUtils.getRandomString(40));
		this.postsDao.savePosts(post);
		for (int i = 0; i < 50; i++) {
			String Comm_ID = RandomUtils.getRandomString(15) + "Comm";
			Comm_IDList.add(Comm_ID);
			CommentPost commentPost = new CommentPost();
			commentPost.setAcc_ID(Acc_ID);
			commentPost.setRep_ID(Rep_ID);
			commentPost.setComm_addTime(DateUtils.currentDateTime());
			commentPost.setComm_desc("lalalalala");
			commentPost.setComm_ID(Comm_ID);
			commentPost.setPost_ID(Post_ID);
			this.commentPostDao.saveCommentPost(commentPost);
		}
	}

	@Test
	public void testSaveCommentPost() {
		boolean flag = true;
		for (int i = 0; i < 10; i++) {
			CommentPost commentPost = new CommentPost();
			commentPost.setAcc_ID(Acc_ID);
			commentPost.setRep_ID(Rep_ID);
			commentPost.setComm_addTime(DateUtils.currentDateTime());
			commentPost.setComm_desc("lalalalala");
			commentPost.setComm_ID(RandomUtils.getRandomString(15) + "Comm");
			commentPost.setPost_ID(Post_ID);
			flag = flag && this.commentPostDao.saveCommentPost(commentPost);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteCommentPost() {
		boolean flag = true;
		for (int i = 45; i < Comm_IDList.size(); i++) {
			flag = flag && this.commentPostDao.deleteCommentPost(Comm_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testUpdateCommentPostCommentPost() {
		boolean flag = true;
		for (int i = 0; i < 20; i++) {
			CommentPost commentPost = this.commentPostDao.queryById(Comm_IDList.get(i));
			commentPost.setComm_desc("yeah!");
			flag = flag && this.commentPostDao.updateCommentPost(commentPost);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryById() {
		for (String Comm_ID : Comm_IDList) {
			CommentPost commentPost = this.commentPostDao.queryById(Comm_ID);
			Assert.assertNotNull(commentPost);
			System.out.println(commentPost.toJSONString());
		}
	}

	@Test
	public void testQueryByAccountId() {
		List<CommentPost> commentPostsList = this.commentPostDao.queryByAccountId(Acc_ID);
		Assert.assertNotNull(commentPostsList);
		for (CommentPost commentPost : commentPostsList) {
			System.out.println(commentPost.toJSONString());
		}
	}

	@Test
	public void testQueryByReporterId() {
		List<CommentPost> commentPostsList = this.commentPostDao.queryByReporterId(Rep_ID);
		Assert.assertNotNull(commentPostsList);
		for (CommentPost commentPost : commentPostsList) {
			System.out.println(commentPost.toJSONString());
		}
	}

	@Test
	public void testQueryByPostId() {
		List<CommentPost> commentPostsList = this.commentPostDao.queryByPostId(Post_ID);
		Assert.assertNotNull(commentPostsList);
		for (CommentPost commentPost : commentPostsList) {
			System.out.println(commentPost.toJSONString());
		}
	}
}
