package com.xuyihao.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.dao.PostsDao;
import com.xuyihao.dao.RepostPostDao;
import com.xuyihao.entity.Posts;
import com.xuyihao.logic.PostsLogic;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

/**
 * Created by Xuyh at 2016/7/21 20:28.
 */
public class PostsLogicImpl implements PostsLogic {
	@Autowired
	private PostsDao postDao;

	@Autowired
	private RepostPostDao repostPostDao;

	public void setPostDao(PostsDao postDao) {
		this.postDao = postDao;
	}

	public void setRepostPostDao(RepostPostDao repostPostDao) {
		this.repostPostDao = repostPostDao;
	}

	@Override
	public String savePost(Posts posts) {
		boolean flag = true;
		String Post_ID = RandomUtils.getRandomString(15) + "Post";
		String Add_time = DateUtils.currentDateTime();
		posts.setPost_ID(Post_ID);
		posts.setPost_addTime(Add_time);
		flag = flag && this.postDao.savePosts(posts);
		if (flag) {
			return Post_ID;
		} else {
			return "";
		}
	}

	@Override
	public boolean deletePost(String Post_ID) {
		boolean flag = this.postDao.deletePosts(Post_ID);
		return flag;
	}

	@Override
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

	@Override
	public Posts getPostInfo(String Post_ID) {
		Posts post = this.postDao.queryById(Post_ID);
		return post;
	}

	@Override
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
		if (flag) {
			return Post_IDNew;
		} else {
			return "";
		}
	}
}
