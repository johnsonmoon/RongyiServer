package com.xuyihao.dao;

import java.util.List;

/**
 *
 * Created by Xuyh at 16-8-10 下午11:26
 */
public interface AttentionDao {
	/**
	 *
	 * @param Acc_Id
	 * @param Att_Id
	 * @return
	 */
	public boolean saveAttention(String Acc_Id, String Att_Id, String Att_addTime);

	/**
	 *
	 * @param Acc_Id
	 * @param Att_Id
	 * @return
	 */
	public boolean deleteAttention(String Acc_Id, String Att_Id);

	/**
	 *
	 * @param Acc_Id
	 * @return
	 */
	public boolean deleteAttentionAll(String Acc_Id);

	/**
	 *
	 * @param sql
	 * @return
	 */
	public boolean deleteAttentionBySql(String sql);

	/**
	 *
	 * @param Acc_Id
	 * @return
	 */
	public List<String> queryByAtn(String Acc_Id);

	/**
	 *
	 * @param Att_Id
	 * @return
	 */
	public List<String> queryByAtned(String Att_Id);
	
	/**
	 * 关闭数据库连接(对于返回ResultSet的查询，其余的不用)
	 * 
	 */
	public void closeDBConnection();
}
