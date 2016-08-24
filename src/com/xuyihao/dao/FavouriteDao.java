package com.xuyihao.dao;

import java.util.List;

/**
 * Created by Xuyh at 16-8-10 下午11:30
 */
public interface FavouriteDao {
	/**
	 *
	 * @param Acc_Id
	 * @param Shop_Id
	 * @return
	 */
	public boolean saveFavourite(String Acc_Id, String Shop_Id, String Favo_addTime);

	/**
	 *
	 * @param Acc_Id
	 * @param Shop_Id
	 * @return
	 */
	public boolean deleteFavourite(String Acc_Id, String Shop_Id);

	/**
	 *
	 * @param Acc_Id
	 * @return
	 */
	public boolean deleteFavouriteAll(String Acc_Id);

	/**
	 *
	 * @param sql
	 * @return
	 */
	public boolean deleteFavouriteBySql(String sql);

	/**
	 *
	 * @param Acc_Id
	 * @return
	 */
	public List<String> queryByAccountId(String Acc_Id);

	/**
	 *
	 * @param Shop_Id
	 * @return
	 */
	public List<String> queryByShopId(String Shop_Id);
	
	/**
	 * 关闭数据库连接(对于返回ResultSet的查询，其余的不用)
	 * 
	 */
	public void closeDBConnection();
}
