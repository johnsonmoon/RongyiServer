package com.xuyihao.logic;

import com.xuyihao.entity.LikePost;

/**
 * Created by Administrator on 2016/7/20.
 */
public interface LikePostLogic {
	/**
	 * insert information into database tables
	 *
	 * @param likePost instance of LikePost
	 * @return
	 */
	public String saveLikePost(LikePost likePost);

	/**
	 * 
	 * @param Like_ID
	 * @return
	 */
	public LikePost getLikePostInfo(String Like_ID);
}