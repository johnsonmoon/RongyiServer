package com.xuyihao.dao;

import java.util.List;

/**
 * Created by Xuyh at 16-8-10 下午11:31
 */
public interface WantDao {
	/**
	 *
	 * @param Acc_ID
	 * @param Prod_ID
	 * @return
	 */
	public boolean saveWant(String Acc_ID, String Prod_ID, String Want_addTime);

	/**
	 *
	 * @param Acc_Id
	 * @param Prod_ID
	 * @return
	 */
	public boolean deleteWant(String Acc_Id, String Prod_ID);

	/**
	 *
	 * @param Acc_Id
	 * @return
	 */
	public boolean deleteWantAll(String Acc_Id);

	/**
	 *
	 * @param sql
	 * @return
	 */
	public boolean deleteWantBySql(String sql);

	/**
	 *
	 * @param Acc_Id
	 * @return
	 */
	public List<String> queryByAccountId(String Acc_Id);

	/**
	 *
	 * @param Prod_Id
	 * @return
	 */
	public List<String> queryByProductId(String Prod_Id);
	
	/**
	 * 关闭数据库连接(对于返回ResultSet的查询，其余的不用)
	 * 
	 */
	public void closeDBConnection();
}
