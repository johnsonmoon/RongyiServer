package com.xuyihao.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.dao.CommentPostDao;
import com.xuyihao.dao.PostsDao;
import com.xuyihao.entity.CommentPost;
import com.xuyihao.entity.Posts;
import com.xuyihao.logic.CommentPostLogic;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

/**
 * Created by Xuyh at 2016/7/21 20:26.
 */
public class CommentPostLogicImpl implements CommentPostLogic {
	@Autowired
	private CommentPostDao commentPostDao;

	@Autowired
	private PostsDao postsDao;

	public void setCommentPostDao(CommentPostDao commentPostDao) {
		this.commentPostDao = commentPostDao;
	}

	public void setPostsDao(PostsDao postsDao) {
		this.postsDao = postsDao;
	}

	@Override
	public String saveCommentPost(CommentPost commentPost) {
		boolean flag = true;
		String Comm_ID = RandomUtils.getRandomString(15) + "Comm";
		String Add_time = DateUtils.currentDateTime();
		commentPost.setComm_ID(Comm_ID);
		commentPost.setComm_addTime(Add_time);
		flag = flag && this.commentPostDao.saveCommentPost(commentPost);
		String Post_ID = commentPost.getPost_ID();
		Posts post = this.postsDao.queryById(Post_ID);
		if ((post.getPost_ID() != null) && (!post.getPost_ID().equals(""))) {
			post.setPost_comm(post.getPost_comm() + 1);
			flag = flag && this.postsDao.updatePosts(post);
		}
		if (flag) {
			return Comm_ID;
		} else {
			return "";
		}
	}

	@Override
	public boolean deleteCommentPost(String Comm_ID) {
		boolean flag = true;
		Posts post = this.postsDao.queryById(this.commentPostDao.queryById(Comm_ID).getPost_ID());
		if ((post.getPost_ID() == null) || (post.getPost_ID().equals(""))) {
			flag = false;
		} else {
			flag = flag && this.commentPostDao.deleteCommentPost(Comm_ID);
			post.setPost_comm(post.getPost_comm() - 1);
			flag = flag && this.postsDao.updatePosts(post);
		}
		return flag;
	}

	@Override
	public CommentPost getCommentInfo(String Comm_ID) {
		CommentPost commentPost = this.commentPostDao.queryById(Comm_ID);
		if (commentPost == null) {
			return null;
		}
		return commentPost;
	}
}
