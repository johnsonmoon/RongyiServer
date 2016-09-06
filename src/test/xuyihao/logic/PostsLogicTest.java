package test.xuyihao.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.xuyihao.CommonTest;
import xuyihao.dao.AccountsDao;
import xuyihao.entity.Accounts;
import xuyihao.entity.Posts;
import xuyihao.logic.PostsLogic;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;

public class PostsLogicTest extends CommonTest {
	private static List<Posts> postList = new ArrayList<>();
	private static String Acc_ID1 = null;
	private static String Acc_ID2 = null;
	private static String Author_ID = null;

	@Autowired
	private PostsLogic postsLogic;

	@Autowired
	private AccountsDao accountsDao;

	public void setCourssesLogic(PostsLogic postsLogic) {
		this.postsLogic = postsLogic;
	}

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	@Before
	public void setUp() throws Exception {
		postList.clear();
		Acc_ID1 = RandomUtils.getRandomString(15) + "Acc";
		Acc_ID2 = RandomUtils.getRandomString(15) + "Acc";
		Author_ID = RandomUtils.getRandomString(15) + "Acc";
		Accounts account1 = new Accounts();
		Accounts account2 = new Accounts();
		Accounts author = new Accounts();
		account1.setAcc_ID(Acc_ID1);
		account2.setAcc_ID(Acc_ID2);
		author.setAcc_ID(Author_ID);
		account1.setAcc_addTime(DateUtils.currentDateTime());
		account2.setAcc_addTime(DateUtils.currentDateTime());
		author.setAcc_addTime(DateUtils.currentDateTime());
		account1.setAcc_name(RandomUtils.getRandomString(5));
		account2.setAcc_name(RandomUtils.getRandomString(5));
		author.setAcc_name(RandomUtils.getRandomString(5));
		this.accountsDao.saveAccounts(account1);
		this.accountsDao.saveAccounts(account2);
		this.accountsDao.saveAccounts(author);
		for (int i = 0; i < 10; i++) {
			Posts post = new Posts();
			post.setAuthor_ID(Author_ID);
			post.setPost_name(RandomUtils.getRandomString(13));
			post.setPost_route(RandomUtils.getRandomString(66));
			String Post_ID = this.postsLogic.savePost(post);
			postList.add(this.postsLogic.getPostInfo(Post_ID));
		}
	}

	@Test
	public void testSavePost() {
		for (int i = 0; i < 10; i++) {
			Posts post = new Posts();
			post.setAuthor_ID(Author_ID);
			post.setPost_name(RandomUtils.getRandomString(13));
			post.setPost_route(RandomUtils.getRandomString(66));
			String Post_ID = this.postsLogic.savePost(post);
			Assert.assertNotSame("", Post_ID);
		}
	}

	@Test
	public void testDeletePost() {
		boolean flag = true;
		for (Posts post : postList) {
			flag = flag && this.postsLogic.deletePost(post.getPost_ID());
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testChangePostInfo() {
		boolean flag = true;
		for (Posts post : postList) {
			post.setPost_route("啦啦啦");
			post.setPost_name("朴飘飘");
			flag = flag && this.postsLogic.changePostInfo(post);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testGetPostInfo() {
		for (Posts post : postList) {
			Posts po = this.postsLogic.getPostInfo(post.getPost_ID());
			Assert.assertNotSame("", po.getPost_ID());
			System.out.println(po.toJSONString());
		}
	}

	@Test
	public void testSharePost() {
		for (Posts post : postList) {
			String Post_ID1 = this.postsLogic.sharePost(Acc_ID1, post.getPost_ID());
			String Post_ID2 = this.postsLogic.sharePost(Acc_ID2, post.getPost_ID());
			Assert.assertNotSame("", Post_ID1);
			Assert.assertNotSame("", Post_ID2);
			System.out.println(Post_ID1);
			System.out.println(Post_ID2);
		}
	}
}
