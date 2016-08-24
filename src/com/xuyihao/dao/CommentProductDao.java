package com.xuyihao.dao;

import com.xuyihao.entity.CommentProduct;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Xuyh at 2016/7/21 19:28.
 */
public interface CommentProductDao {
	/**
	 * 写入数据库
	 *
	 * @param commentProduct 评论产品对象
	 * @return
	 */
	public boolean saveCommentProduct(CommentProduct commentProduct);

	/**
	 * 从数据库删除数据
	 *
	 * @param commentProduct 评论产品对象
	 * @return true成功， false失败
	 */
	public boolean deleteCommentProduct(String Comm_ID);

	/**
	 * 修改数据库中的数据
	 *
	 * @param commentProduct 评论产品对象
	 * @return true成功， false失败
	 */
	public boolean updateCommentProduct(CommentProduct commentProduct);

	/**
	 * 通过传入sql语句改数据
	 *
	 * @param update 更新的sql语句
	 * @return true成功， false失败
	 */
	public boolean updateCommentProduct(String update);

	/**
	 * 查询
	 *
	 * @param Comm_ID 评论ID
	 * @return
	 */
	public CommentProduct queryById(String Comm_ID);

	/**
	 * 查询
	 *
	 * @param Acc_ID 评论者ID(查询评论者的评论集)
	 * @return
	 */
	public List<CommentProduct> queryByAccountId(String Acc_ID);

	/**
	 * 查询
	 *
	 * @param Prod_ID 产品ID(查询视产品到的评论集)
	 * @return
	 */
	public List<CommentProduct> queryByProductId(String Prod_ID);

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
