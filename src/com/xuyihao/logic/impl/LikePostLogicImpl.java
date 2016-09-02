package com.xuyihao.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuyihao.dao.LikePostDao;
import com.xuyihao.dao.PostsDao;
import com.xuyihao.entity.LikePost;
import com.xuyihao.entity.Posts;
import com.xuyihao.logic.LikePostLogic;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

/**
 * Created by Xuyh at 2016/7/21 20:27.
 */
@Component("LikePostLogic")
public class LikePostLogicImpl implements LikePostLogic {
	@Autowired
	private LikePostDao likePostDao;

	@Autowired
	private PostsDao postsDao;

	public void setLikePostDao(LikePostDao likePostDao) {
		this.likePostDao = likePostDao;
	}

	public void setPostsDao(PostsDao postsDao) {
		this.postsDao = postsDao;
	}

	public String saveLikePost(LikePost likePost) {
		boolean flag = true;
		Posts post = this.postsDao.queryById(likePost.getPost_ID());
		if ((post.getPost_ID() == null) || (post.getPost_ID().equals(""))) {
			return "";
		}
		post.setPost_like(post.getPost_like() + 1);
		flag = flag && this.postsDao.updatePosts(post);
		String Like_ID = RandomUtils.getRandomString(15) + "Like";
		String Add_time = DateUtils.currentDateTime();
		likePost.setLike_ID(Like_ID);
		likePost.setLike_addTime(Add_time);
		flag = flag && this.likePostDao.saveLikePost(likePost);
		if (flag) {
			return Like_ID;
		} else {
			return "";
		}
	}

	public LikePost getLikePostInfo(String Like_ID) {
		LikePost likePost = this.likePostDao.queryById(Like_ID);
		return likePost;
	}
}