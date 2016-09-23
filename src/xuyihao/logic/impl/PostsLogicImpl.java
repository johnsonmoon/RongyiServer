package xuyihao.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xuyihao.dao.AccountsDao;
import xuyihao.dao.PostsDao;
import xuyihao.dao.RepostPostDao;
import xuyihao.entity.Accounts;
import xuyihao.entity.Posts;
import xuyihao.logic.PostsLogic;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;

/**
 * Created by Xuyh at 2016/7/21 20:28.
 */
@Component("PostsLogic")
public class PostsLogicImpl implements PostsLogic {
	@Autowired
	private PostsDao postDao;

	@Autowired
	private RepostPostDao repostPostDao;

	@Autowired
	private AccountsDao accountsDao;

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setPostDao(PostsDao postDao) {
		this.postDao = postDao;
	}

	public void setRepostPostDao(RepostPostDao repostPostDao) {
		this.repostPostDao = repostPostDao;
	}

	public String savePost(Posts posts) {
		boolean flag = true;
		String Post_ID = RandomUtils.getRandomString(15) + "Post";
		String Add_time = DateUtils.currentDateTime();
		posts.setPost_ID(Post_ID);
		posts.setPost_addTime(Add_time);
		flag = flag && this.postDao.savePosts(posts);
		// 账户发表加一(删除不减)
		Accounts accounts = this.accountsDao.queryById(posts.getAcc_ID());
		accounts.setAcc_pub(accounts.getAcc_pub() + 1);
		flag = flag && this.accountsDao.updateAccounts(accounts);
		if (flag) {
			return Post_ID;
		} else {
			return "";
		}
	}

	public boolean deletePost(String Post_ID) {
		boolean flag = this.postDao.deletePosts(Post_ID);
		return flag;
	}

	public boolean changePostInfo(Posts post) {
		Posts DBpost = this.postDao.queryById(post.getPost_ID());
		if ((DBpost.getPost_ID() == null) || (DBpost.getPost_ID().equals(""))) {
			return false;
		}
		if ((post.getPost_name() == null) || (post.getPost_name().equals(""))) {
			post.setPost_name(DBpost.getPost_name());
		}
		if ((post.getPost_route() == null) || (post.getPost_route().equals(""))) {
			post.setPost_route(DBpost.getPost_route());
		}
		post.setAcc_ID(DBpost.getAcc_ID());
		post.setAuthor_ID(DBpost.getAuthor_ID());
		post.setPost_rep(DBpost.getPost_rep());
		post.setPost_comm(DBpost.getPost_comm());
		post.setPost_like(DBpost.getPost_like());
		post.setPost_addTime(DBpost.getPost_addTime());
		boolean flag = this.postDao.updatePosts(post);
		return flag;
	}

	public Posts getPostInfo(String Post_ID) {
		Posts post = this.postDao.queryById(Post_ID);
		return post;
	}

	public String sharePost(String Acc_ID, String Post_ID) {
		boolean flag = true;
		Posts postOld = this.postDao.queryById(Post_ID);
		postOld.setPost_rep(postOld.getPost_rep() + 1);
		flag = flag && this.postDao.updatePosts(postOld);
		Posts postNew = new Posts(postOld);
		String Post_IDNew = RandomUtils.getRandomString(15) + "Posts";
		String Add_timeNew = DateUtils.currentDateTime();
		postNew.setPost_ID(Post_IDNew);
		postNew.setPost_addTime(Add_timeNew);
		postNew.setAcc_ID(Acc_ID);
		flag = flag && this.postDao.savePosts(postNew);
		flag = flag && this.repostPostDao.saveRepostPost(Acc_ID, postOld.getAuthor_ID(), Post_IDNew, Add_timeNew);
		// 账户发表加一(删除不减)
		Accounts accounts = this.accountsDao.queryById(Acc_ID);
		accounts.setAcc_pub(accounts.getAcc_pub() + 1);
		flag = flag && this.accountsDao.updateAccounts(accounts);
		if (flag) {
			return Post_IDNew;
		} else {
			return "";
		}
	}

	public List<Posts> getLatestPosts(int page, int size) {
		List<Posts> postsList = this.postDao.queryByLimitOrdered(Posts.BASE_POSTS_PHYSICAL_ID, -1, page, size);
		return postsList;
	}
}