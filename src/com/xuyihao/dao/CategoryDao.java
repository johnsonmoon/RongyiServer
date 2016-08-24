package com.xuyihao.dao;

import com.xuyihao.entity.Category;

import java.sql.ResultSet;

/**
 * Created by Xuyh on 2016/7/21.
 */
public interface CategoryDao {
	/**
	 * 写入数据库
	 *
	 * @param category 商品分类对象
	 * @return
	 */
	public boolean saveCategory(Category category);

	/**
	 * 从数据库删除数据
	 *
	 * @param category 商品分类对象
	 * @return true成功， false失败
	 */
	public boolean deleteCategory(String Cat_ID);

	/**
	 * 修改数据库中的数据
	 *
	 * @param category 商品分类对象
	 * @return true成功， false失败
	 */
	public boolean updateCategory(Category category);

	/**
	 * 通过传入sql语句改数据
	 *
	 * @param update 更新的sql语句
	 * @return true成功， false失败
	 */
	public boolean updateCategory(String update);

	/**
	 * 查询
	 *
	 * @param Cat_ID 分类ID
	 * @return 商品分类对象
	 */
	public Category queryById(String Cat_ID);

	/**
	 * 查询
	 *
	 * @param Cat_name 商品分类名
	 * @return 商品分类对象
	 */
	public Category queryByName(String Cat_name);

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
