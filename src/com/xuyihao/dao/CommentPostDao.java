package com.xuyihao.dao;

import com.xuyihao.entity.CommentPost;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Xuyh at 2016/7/21 19:27.
 */
public interface CommentPostDao {
	/**
	 * 写入数据库
	 *
	 * @param commentPost 评论帖子对象
	 * @return
	 */
	public boolean saveCommentPost(CommentPost commentPost);

	/**
	 * 从数据库删除数据
	 *
	 * @param commentPost 评论帖子对象
	 * @return true成功， false失败
	 */
	public boolean deleteCommentPost(String Comm_ID);

	/**
	 * 修改数据库中的数据
	 *
	 * @param commentPost 评论帖子对象
	 * @return true成功， false失败
	 */
	public boolean updateCommentPost(CommentPost commentPost);

	/**
	 * 通过传入sql语句改数据
	 *
	 * @param update 更新的sql语句
	 * @return true成功， false失败
	 */
	public boolean updateCommentPost(String update);

	/**
	 * 查询
	 *
	 * @param Comm_ID 评论ID
	 * @return
	 */
	public CommentPost queryById(String Comm_ID);

	/**
	 * 查询
	 *
	 * @param Acc_ID 评论者ID(查询评论者的评论集)
	 * @return
	 */
	public List<CommentPost> queryByAccountId(String Acc_ID);

	/**
	 * 查询
	 *
	 * @param Rep_ID 发布者账号ID(查询发布者收到的评论集)
	 * @return
	 */
	public List<CommentPost> queryByReporterId(String Rep_ID);

	/**
	 * 查询
	 *
	 * @param Post_ID 帖子ID(查询帖子收到的评论集)
	 * @return
	 */
	public List<CommentPost> queryByPostId(String Post_ID);

	/**
	 * 查询
	 *
	 * @param query 查询sql语句
	 * @return Resultset 查询结果游标
	 */
	public ResultSet queryBySql(String query);
	
	/**
	 * 关闭数据库连接(对于返回ResultSet的查询，其余的不用)
	 * 
	 */
	public void closeDBConnection();
}
