package com.xuyihao.filerelate.dao;

import com.xuyihao.filerelate.entity.AccountsPhotos;

/**
 * 
 * @author Xuyh at 2016年8月30日 上午9:48:44.
 *
 */
public interface AccountsPhotosDao {
	/**
	 * 添加账号图片
	 * 
	 * @param accountsPhotos
	 * @return
	 */
	public boolean saveAccountsPhotos(AccountsPhotos accountsPhotos);

	/**
	 * 删除账号图片
	 * 
	 * @param Acc_ID
	 * @return
	 */
	public boolean deleteAccountsPhotos(String Acc_ID);

	/**
	 * 修改账号图片
	 * 
	 * @param accountsPhotos
	 * @return
	 */
	public boolean updateAccountsPhotos(AccountsPhotos accountsPhotos);

	/**
	 * 修改账号图片
	 * 
	 * @param update
	 * @return
	 */
	public boolean updateAccountsPhotosBySql(String update);

	/**
	 * 查询账号图片
	 * 
	 * @param Acc_ID
	 * @return
	 */
	public AccountsPhotos query(String Acc_ID);

	/**
	 * 查询账号图片
	 * 
	 * @param query
	 * @return
	 */
	public AccountsPhotos queryBySql(String query);

	/**
	 * 关闭数据库连接(对于返回ResultSet的查询，其余的不用)
	 * 
	 */
	public void closeDBConnection();
}