package com.xuyihao.logic;

import com.xuyihao.entity.CommentCrs;

/**
 * 评论教程逻辑
 *
 * Created by Administrator on 2016/7/20.
 */
public interface CommentCrsLogic {
	/**
	 * insert information insto database table
	 *
	 * @param commentCrs instance of CommentCrs
	 * @return
	 */
	public String saveCommentCrs(CommentCrs commentCrs);

	/**
	 * delete info from database table
	 *
	 * @param Comm_ID
	 * @return
	 */
	public boolean deleteCommentCrs(String Comm_ID);

	/**
	 * get comment info by Comm_ID
	 * 
	 * @param Comm_ID
	 * @return
	 */
	public CommentCrs getCommentCrsInfo(String Comm_ID);
}
