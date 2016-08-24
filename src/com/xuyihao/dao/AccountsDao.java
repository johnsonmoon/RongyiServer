package com.xuyihao.dao;

import com.xuyihao.entity.Accounts;
import java.sql.ResultSet;

/**
 * Created by Xuyh on 2016/7/21.
 */
public interface AccountsDao {
	/**
	 * 写入数据库
	 *
	 * @param accounts 账户对象
	 * @return
	 */
	public boolean saveAccounts(Accounts accounts);

	/**
	 * 从数据库删除数据
	 *
	 * @param accounts 账户对象
	 * @return true成功， false失败
	 */
	public boolean deleteAccounts(String Acc_ID);

	/**
	 * 修改数据库中的数据
	 *
	 * @param accounts 账户对象
	 * @return true成功， false失败
	 */
	public boolean updateAccounts(Accounts accounts);

	/**
	 * 通过传入sql语句改数据
	 *
	 * @param update 更新的sql语句
	 * @return true成功， false失败
	 */
	public boolean updateAccounts(String update);

	/**
	 * 查询
	 *
	 * @param Acc_name 账号名
	 * @return Accounts 账号对象
	 */
	public Accounts queryByName(String Acc_name);

	/**
	 * 查询
	 *
	 * @param Acc_ID 账号ID
	 * @return Accounts 账号对象
	 */
	public Accounts queryById(String Acc_ID);

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
