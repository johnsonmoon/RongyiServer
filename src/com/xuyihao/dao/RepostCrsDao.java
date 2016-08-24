package com.xuyihao.dao;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Xuyh at 16-8-10 下午11:27
 */
public interface RepostCrsDao {
	/**
	 *
	 * @param Acc_Id
	 * @param Rep_Id
	 * @param Crs_Id
	 * @return
	 */
	public boolean saveRepostCrs(String Acc_Id, String Rep_Id, String Crs_Id, String Rep_addTime);

	/**
	 *
	 * @param Acc_Id
	 * @return
	 */
	public boolean deleteRepostCrs(String Acc_Id, String Rep_Id, String Crs_Id);

	/**
	 *
	 * @param Acc_Id
	 * @param Rep_Id
	 * @return
	 */
	public boolean deleteRepostCrs(String Acc_Id, String Rep_Id);

	/**
	 *
	 * @param Acc_Id
	 * @return
	 */
	public boolean deleteRepostCrsAll(String Acc_Id);

	/**
	 *
	 * @param sql
	 * @return
	 */
	public boolean deleteBySql(String sql);

	/**
	 *
	 * @param Acc_Id
	 * @return 返回账户的所有分享视频教程编号
	 */
	public List<String> queryByAccountId(String Acc_Id);

	/**
	 *
	 * @param Acc_Id
	 * @param Rep_Id
	 * @return 返回账户的所有分享视频教程编号
	 */
	public List<String> query(String Acc_Id, String Rep_Id);

	/**
	 *
	 * @param sql
	 * @return
	 */
	public ResultSet queryBySql(String sql);
	
	/**
	 * 关闭数据库连接(对于返回ResultSet的查询，其余的不用)
	 * 
	 */
	public void closeDBConnection();
}
