package com.xuyihao.logic;

import com.xuyihao.entity.CommentPost;

/**
 * 评论帖子逻辑
 *
 * Created by Administrator on 2016/7/20.
 */
public interface CommentPostLogic {
	/**
	 * insert information into database table
	 *
	 * @param commentPost instance of CommentPost
	 * @return
	 */
	public String saveCommentPost(CommentPost commentPost);

	/**
	 * delete information from database table
	 *
	 * @param Comm_ID
	 * @return
	 */
	public boolean deleteCommentPost(String Comm_ID);

	/**
	 * get information of the comment by Comm_ID
	 * 
	 * @param Comm_ID
	 * @return
	 */
	public CommentPost getCommentInfo(String Comm_ID);
}