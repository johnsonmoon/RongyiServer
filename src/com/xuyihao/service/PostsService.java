package com.xuyihao.service;

import com.xuyihao.entity.CommentPost;
import com.xuyihao.entity.LikePost;
import com.xuyihao.entity.Posts;

/**
 * 帖子相关服务接口
 * 
 * @Author Xuyh created at 2016年8月23日 上午10:48:35
 */
public interface PostsService {
	/**
	 * XXX PostsLogic
	 */
	/**
	 * 新建帖子
	 * 
	 * @param post
	 * @return
	 */
	public String addPost(Posts post);

	/**
	 * 删除帖子
	 * 
	 * @param postId
	 * @return
	 */
	public String deletePost(String postId);

	/**
	 * 修改帖子信息
	 * 
	 * @param post
	 * @return
	 */
	public String changePostInformation(Posts post);

	/**
	 * 获取帖子信息
	 * 
	 * @param postId
	 * @return
	 */
	public String getPostInformation(String postId);

	/**
	 * 分享帖子
	 * 
	 * @param accountId
	 * @param PostId
	 * @return
	 */
	public String sharePost(String accountId, String PostId);

	/**
	 * XXX CommentPostLogic
	 */
	/**
	 * 添加帖子评论
	 * 
	 * @param commentPost
	 * @return
	 */
	public String addCommentPost(CommentPost commentPost);

	/**
	 * 删除帖子评论
	 * 
	 * @param commentId
	 * @return
	 */
	public String deleteCommentPost(String commentId);

	/**
	 * 获取帖子信息
	 * 
	 * @param commentId
	 * @return
	 */
	public String getCommentPostInformation(String commentId);

	/**
	 * XXX LikePostLogic
	 */
	/**
	 * 添加帖子打赏
	 * 
	 * @param likePost
	 * @return
	 */
	public String addLikePost(LikePost likePost);

	/**
	 * 获取帖子打赏信息
	 * 
	 * @param likeId
	 * @return
	 */
	public String getLikePostInformation(String likeId);
}
