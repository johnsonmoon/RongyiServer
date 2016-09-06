package test.xuyihao.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xuyihao.dao.AccountsDao;
import xuyihao.dao.PostsDao;
import xuyihao.entity.Accounts;
import xuyihao.entity.Posts;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;
import junit.framework.Assert;
import test.xuyihao.CommonTest;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:35:59 PM.
 *
 */
public class PostsDaoTest extends CommonTest {
	private static List<String> Post_IDList = new ArrayList<>();
	private static List<String> Post_nameList = new ArrayList<>();
	private static String Acc_ID = null;
	private static String Author_ID = null;

	@Autowired
	private AccountsDao accountsDao;

	@Autowired
	private PostsDao postsDao;

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setPostsDao(PostsDao postsDao) {
		this.postsDao = postsDao;
	}

	@Before
	public void setUp() {
		Acc_ID = RandomUtils.getRandomString(15) + "Acc";
		Author_ID = RandomUtils.getRandomString(15) + "Acc";
		Accounts accounts = new Accounts();
		accounts.setAcc_ID(Acc_ID);
		accounts.setAcc_name("Johnson" + RandomUtils.getRandomNumberString(4));
		accounts.setAcc_pwd("moon" + RandomUtils.getRandomNumberString(4));
		accounts.setAcc_sex("男");
		accounts.setAcc_loc("浙江工业大学屛峰校区");
		accounts.setAcc_addTime(DateUtils.currentDateTime());
		this.accountsDao.saveAccounts(accounts);
		Accounts accounts2 = new Accounts();
		accounts2.setAcc_ID(Author_ID);
		accounts2.setAcc_name("Johnson" + RandomUtils.getRandomNumberString(4));
		accounts2.setAcc_pwd("moon" + RandomUtils.getRandomNumberString(4));
		accounts2.setAcc_sex("男");
		accounts2.setAcc_loc("浙江工业大学屛峰校区");
		accounts2.setAcc_addTime(DateUtils.currentDateTime());
		this.accountsDao.saveAccounts(accounts2);
		for (int i = 0; i < 50; i++) {
			String Post_ID = RandomUtils.getRandomString(15) + "Post";
			String Post_name = RandomUtils.getRandomString(30);
			Post_IDList.add(Post_ID);
			Post_nameList.add(Post_name);
			Posts post = new Posts();
			post.setPost_ID(Post_ID);
			post.setAcc_ID(Acc_ID);
			post.setAuthor_ID(Author_ID);
			post.setPost_addTime(DateUtils.currentDateTime());
			post.setPost_name(Post_name);
			post.setPost_route(RandomUtils.getRandomString(66));
			this.postsDao.savePosts(post);
		}
	}

	@Test
	public void testSavePosts() {
		boolean flag = true;
		for (int i = 0; i < 50; i++) {
			Posts post = new Posts();
			post.setPost_ID(RandomUtils.getRandomString(15) + "Post");
			post.setAcc_ID(Acc_ID);
			post.setAuthor_ID(Author_ID);
			post.setPost_addTime(DateUtils.currentDateTime());
			post.setPost_name(RandomUtils.getRandomString(59));
			post.setPost_route(RandomUtils.getRandomString(66));
			flag = flag && this.postsDao.savePosts(post);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeletePosts() {
		boolean flag = true;
		for (int i = 45; i < Post_IDList.size(); i++) {
			flag = flag && this.postsDao.deletePosts(Post_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testUpdatePostsPosts() {
		boolean flag = true;
		for (int i = 0; i < 20; i++) {
			Posts post = this.postsDao.queryById(Post_IDList.get(i));
			post.setPost_route("jajajaja");
			flag = flag && this.postsDao.updatePosts(post);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryById() {
		for (int i = 0; i < 45; i++) {
			Posts post = this.postsDao.queryById(Post_IDList.get(i));
			System.out.println(post.toJSONString());
			Assert.assertNotNull(post);
		}
	}

	@Test
	public void testQueryByName() {
		for (int i = 0; i < 45; i++) {
			Posts post = this.postsDao.queryByName(Post_nameList.get(i));
			System.out.println(post.toJSONString());
			Assert.assertNotNull(post);
		}
	}

	@Test
	public void testQueryByAuthorId() {
		List<Posts> postList = this.postsDao.queryByAuthorId(Author_ID);
		Assert.assertNotNull(postList);
		for (Posts post : postList) {
			System.out.println(post.toJSONString());
		}
	}

	@Test
	public void testQueryByAccountId() {
		List<Posts> postList = this.postsDao.queryByAccountId(Acc_ID);
		Assert.assertNotNull(postList);
		for (Posts post : postList) {
			System.out.println(post.toJSONString());
		}
	}
}
