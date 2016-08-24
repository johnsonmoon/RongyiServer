package com.xuyihao.dao;

import com.xuyihao.entity.CommentCrs;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Xuyh on 2016/7/21.
 */
public interface CommentCrsDao {
	/**
	 * 写入数据库
	 *
	 * @param commentCrs 评论视频对象
	 * @return
	 */
	public boolean saveCommentCrs(CommentCrs commentCrs);

	/**
	 * 从数据库删除数据
	 *
	 * @param commentCrs 评论视频对象
	 * @return true成功， false失败
	 */
	public boolean deleteCommentCrs(String Comm_ID);

	/**
	 * 修改数据库中的数据
	 *
	 * @param commentCrs 评论视频对象
	 * @return true成功， false失败
	 */
	public boolean updateCommentCrs(CommentCrs commentCrs);

	/**
	 * 通过传入sql语句改数据
	 *
	 * @param update 更新的sql语句
	 * @return true成功， false失败
	 */
	public boolean updateCommentCrs(String update);

	/**
	 * 查询
	 *
	 * @param Comm_ID 评论ID
	 * @return 评论视频对象
	 */
	public CommentCrs queryById(String Comm_ID);

	/**
	 * 查询
	 *
	 * @param Acc_ID 评论者ID(查询评论者的评论集)
	 * @return 评论视频对象
	 */
	public List<CommentCrs> queryByAccountId(String Acc_ID);

	/**
	 * 查询
	 *
	 * @param Rep_ID 发布者账号ID(查询发布者收到的评论集)
	 * @return 评论视频对象
	 */
	public List<CommentCrs> queryByReporterId(String Rep_ID);

	/**
	 * 查询
	 *
	 * @param Crs_ID 视频ID(查询视频收到的评论集)
	 * @return 评论视频对象
	 */
	public List<CommentCrs> queryByCourseId(String Crs_ID);

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
