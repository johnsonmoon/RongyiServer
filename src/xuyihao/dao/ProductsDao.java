package xuyihao.dao;

import java.sql.ResultSet;
import java.util.List;

import xuyihao.entity.Products;

/**
 * Created by Xuyh at 2016/7/21 20:23.
 */
public interface ProductsDao {
	/**
	 * 写入数据库
	 *
	 * @param product 产品对象
	 * @return
	 */
	public boolean saveProducts(Products product);

	/**
	 * 从数据库删除数据
	 *
	 * @param product 产品对象
	 * @return true成功， false失败
	 */
	public boolean deleteProducts(String Prod_ID);

	/**
	 * 修改数据库中的内容
	 *
	 * @param product 产品对象
	 * @return true成功， false失败
	 */
	public boolean updateProducts(Products product);

	/**
	 * 修改数据库中的内容
	 *
	 * @param update 更新的sql语句
	 * @return true成功， false失败
	 */
	public boolean updateProducts(String update);

	/**
	 * 查询
	 *
	 * @param Prod_ID 产品ID
	 * @return
	 */
	public Products queryById(String Prod_ID);

	/**
	 * 查询
	 *
	 * @param Prod_name 产品名称
	 * @return
	 */
	public List<Products> queryByName(String Prod_name);

	/**
	 * 查询
	 *
	 * @param Cat_ID 产品分类ID
	 * @return
	 */
	public List<Products> queryByCategoryId(String Cat_ID);

	/**
	 * 查询
	 *
	 * @param Shop_ID 店铺ID
	 * @return
	 */
	public List<Products> queryByShopId(String Shop_ID);

	/**
	 * 查询
	 *
	 * @param sql 查询sql语句
	 * @return Resultset 查询结果游标
	 */
	public ResultSet queryBySql(String sql);
	
	/**
	 * 关闭数据库连接(对于返回ResultSet的查询，其余的不用)
	 * 
	 */
	public void closeDBConnection();
}
