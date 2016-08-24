package test.com.xuyihao.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.com.xuyihao.CommonTest;

import com.xuyihao.dao.AccountsDao;
import com.xuyihao.dao.LikePostDao;
import com.xuyihao.dao.PostsDao;
import com.xuyihao.entity.Accounts;
import com.xuyihao.entity.LikePost;
import com.xuyihao.entity.Posts;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

import junit.framework.Assert;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:34:13 PM.
 *
 */
public class LikePostDaoTest extends CommonTest {
	private static List<String> Like_IDList = new ArrayList<>();
	private static List<String> Post_IDList = new ArrayList<>();
	private static String Acc_ID = null;
	private static String Rep_ID = null;

	@Autowired
	private AccountsDao accountsDao;

	@Autowired
	private PostsDao postsDao;

	@Autowired
	private LikePostDao likePostDao;

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setPostsDao(PostsDao postsDao) {
		this.postsDao = postsDao;
	}

	public void setLikePostDao(LikePostDao likePostDao) {
		this.likePostDao = likePostDao;
	}

	@Before
	public void setUp() {
		Like_IDList.clear();
		Post_IDList.clear();
		Acc_ID = RandomUtils.getRandomString(15) + "Acc";
		Rep_ID = RandomUtils.getRandomString(15) + "Acc";
		Accounts accounts = new Accounts();
		accounts.setAcc_ID(Acc_ID);
		accounts.setAcc_name("Johnson" + RandomUtils.getRandomNumberString(5));
		accounts.setAcc_pwd("moon" + RandomUtils.getRandomNumberString(5));
		accounts.setAcc_sex("男");
		accounts.setAcc_loc("浙江工业大学屛峰校区");
		accounts.setAcc_addTime(DateUtils.currentDateTime());
		this.accountsDao.saveAccounts(accounts);
		Accounts accounts2 = new Accounts();
		accounts2.setAcc_ID(Acc_ID);
		accounts2.setAcc_name("Johnson" + RandomUtils.getRandomNumberString(5));
		accounts2.setAcc_pwd("moon" + RandomUtils.getRandomNumberString(5));
		accounts2.setAcc_sex("男");
		accounts2.setAcc_loc("浙江工业大学屛峰校区");
		accounts2.setAcc_addTime(DateUtils.currentDateTime());
		this.accountsDao.saveAccounts(accounts2);
		for (int i = 0; i < 50; i++) {
			String Like_ID = RandomUtils.getRandomString(15) + "Like";
			String Post_ID = RandomUtils.getRandomString(15) + "Post";
			Like_IDList.add(Like_ID);
			Post_IDList.add(Post_ID);
			Posts post = new Posts();
			post.setPost_ID(Post_ID);
			post.setAcc_ID(Acc_ID);
			post.setAuthor_ID(Rep_ID);
			post.setPost_addTime(DateUtils.currentDateTime());
			post.setPost_name(RandomUtils.getRandomString(12));
			post.setPost_route(RandomUtils.getRandomString(66));
			this.postsDao.savePosts(post);
			// --
			LikePost likePost = new LikePost();
			likePost.setAcc_ID(Acc_ID);
			likePost.setLike_addTime(DateUtils.currentDateTime());
			likePost.setLike_ID(Like_ID);
			likePost.setLike_ryb(5);
			likePost.setPost_ID(Post_ID);
			likePost.setRep_ID(Rep_ID);
			this.likePostDao.saveLikePost(likePost);
		}
	}

	@Test
	public void testSaveLikePost() {
		boolean flag = true;
		for (int i = 0; i < 50; i++) {
			String Like_ID = RandomUtils.getRandomString(15) + "Like";
			String Post_ID = RandomUtils.getRandomString(15) + "Post";
			Posts post = new Posts();
			post.setPost_ID(Post_ID);
			post.setAcc_ID(Acc_ID);
			post.setAuthor_ID(Rep_ID);
			post.setPost_addTime(DateUtils.currentDateTime());
			post.setPost_name(RandomUtils.getRandomString(12));
			post.setPost_route(RandomUtils.getRandomString(66));
			this.postsDao.savePosts(post);
			// --
			LikePost likePost = new LikePost();
			likePost.setAcc_ID(Acc_ID);
			likePost.setLike_addTime(DateUtils.currentDateTime());
			likePost.setLike_ID(Like_ID);
			likePost.setLike_ryb(5);
			likePost.setPost_ID(Post_ID);
			likePost.setRep_ID(Rep_ID);
			flag = flag && this.likePostDao.saveLikePost(likePost);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteLikePost() {
		boolean flag = true;
		for (int i = 45; i < Like_IDList.size(); i++) {
			flag = flag && this.likePostDao.deleteLikePost(Like_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testUpdateLikePostLikePost() {
		boolean flag = true;
		for (int i = 0; i < 20; i++) {
			LikePost likePost = this.likePostDao.queryById(Like_IDList.get(i));
			likePost.setLike_ryb(30);
			flag = flag && this.likePostDao.updateLikePost(likePost);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryById() {
		for (String Like_ID : Like_IDList) {
			LikePost likePost = this.likePostDao.queryById(Like_ID);
			Assert.assertNotNull(likePost);
			System.out.println(likePost.toJSONString());
		}
	}

	@Test
	public void testQueryByAccountId() {
		List<LikePost> likePostList = this.likePostDao.queryByAccountId(Acc_ID);
		Assert.assertNotNull(likePostList);
		for (LikePost likePost : likePostList) {
			System.out.println(likePost.toJSONString());
		}
	}

	@Test
	public void testQueryByReporterId() {
		List<LikePost> likePostList = this.likePostDao.queryByReporterId(Rep_ID);
		Assert.assertNotNull(likePostList);
		for (LikePost likePost : likePostList) {
			System.out.println(likePost.toJSONString());
		}
	}

	@Test
	public void testQueryByPostId() {
		for (String Post_ID : Post_IDList) {
			List<LikePost> likePostList = this.likePostDao.queryByPostId(Post_ID);
			Assert.assertNotNull(likePostList);
			for (LikePost likePost : likePostList) {
				System.out.println(likePost.toJSONString());
			}
		}
	}
}
