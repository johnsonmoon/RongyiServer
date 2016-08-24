package test.com.xuyihao.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.com.xuyihao.CommonTest;

import com.xuyihao.dao.AccountsDao;
import com.xuyihao.dao.PostsDao;
import com.xuyihao.dao.RepostPostDao;
import com.xuyihao.entity.Accounts;
import com.xuyihao.entity.Posts;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

import junit.framework.Assert;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:39:26 PM.
 *
 */
public class RepostPostDaoTest extends CommonTest {
	private static String Acc_ID = null;
	private static String Rep_ID = null;
	private static List<String> Post_IDList = new ArrayList<>();

	@Autowired
	private AccountsDao accountsDao;

	@Autowired
	private PostsDao postsDao;

	@Autowired
	private RepostPostDao repostPostDao;

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setPostsDao(PostsDao postsDao) {
		this.postsDao = postsDao;
	}

	public void setRepostPostDao(RepostPostDao repostPostDao) {
		this.repostPostDao = repostPostDao;
	}

	@Before
	public void setUp() {
		Post_IDList.clear();
		Acc_ID = RandomUtils.getRandomString(15) + "Acc";
		Rep_ID = RandomUtils.getRandomString(15) + "Acc";
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
		for (int i = 0; i < 50; i++) {
			String Post_ID = RandomUtils.getRandomString(15) + "Post";
			String Post_name = RandomUtils.getRandomString(30);
			Post_IDList.add(Post_ID);
			Posts post = new Posts();
			post.setPost_ID(Post_ID);
			post.setAcc_ID(Acc_ID);
			post.setAuthor_ID(Rep_ID);
			post.setPost_addTime(DateUtils.currentDateTime());
			post.setPost_name(Post_name);
			post.setPost_route(RandomUtils.getRandomString(66));
			this.postsDao.savePosts(post);
			// --
			this.repostPostDao.saveRepostPost(Acc_ID, Rep_ID, Post_ID, DateUtils.currentDateTime());
		}
	}

	@Test
	public void testSaveRepostPost() {
		boolean flag = true;
		for (int i = 0; i < 10; i++) {
			String Post_ID = RandomUtils.getRandomString(15) + "Post";
			String Post_name = RandomUtils.getRandomString(30);
			Posts post = new Posts();
			post.setPost_ID(Post_ID);
			post.setAcc_ID(Acc_ID);
			post.setAuthor_ID(Rep_ID);
			post.setPost_addTime(DateUtils.currentDateTime());
			post.setPost_name(Post_name);
			post.setPost_route(RandomUtils.getRandomString(66));
			this.postsDao.savePosts(post);
			// --
			flag = flag && this.repostPostDao.saveRepostPost(Acc_ID, Rep_ID, Post_ID, DateUtils.currentDateTime());
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteRepostPostStringStringString() {
		boolean flag = true;
		for (int i = 0; i < 20; i++) {
			flag = flag && this.repostPostDao.deleteRepostPost(Acc_ID, Rep_ID, Post_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteRepostPostStringString() {
		boolean flag = this.repostPostDao.deleteRepostPost(Acc_ID, Rep_ID);
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteRepostPostAll() {
		boolean flag = this.repostPostDao.deleteRepostPostAll(Acc_ID);
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryByAccountId() {
		List<String> Post_IDList = this.repostPostDao.queryByAccountId(Acc_ID);
		Assert.assertNotNull(Post_IDList);
		for (String postId : Post_IDList) {
			System.out.println(postId);
		}
	}

	@Test
	public void testQuery() {
		List<String> Post_IDList = this.repostPostDao.query(Acc_ID, Rep_ID);
		Assert.assertNotNull(Post_IDList);
		for (String postId : Post_IDList) {
			System.out.println(postId);
		}
	}
}
