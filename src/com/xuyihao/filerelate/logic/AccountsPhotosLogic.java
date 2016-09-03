package com.xuyihao.filerelate.logic;

import java.util.List;

import com.xuyihao.filerelate.entity.AccountsPhotos;

/**
 * 
 * @author Xuyh at 2016年9月3日 下午12:02:51.
 *
 */
public interface AccountsPhotosLogic {
	/**
	 * 存储账户照片
	 * 
	 * @param Acc_ID
	 * @param HeadPhoto_ID
	 * @param photoIdList
	 * @return
	 */
	public boolean saveAccountsPhotos(String Acc_ID, String HeadPhoto_ID, List<String> photoIdList);

	/**
	 * 删除所有账户照片
	 * 
	 * @param Acc_ID
	 * @return
	 */
	public boolean deleteAccountsPhotos(String Acc_ID);

	/**
	 * 修改账户照片信息
	 * 
	 * @param Acc_ID
	 * @param HeadPhoto_ID
	 * @param photoIdList
	 * @return
	 */
	public boolean changeAccountsPhotosInfo(String Acc_ID, String HeadPhoto_ID, List<String> photoIdList);

	/**
	 * 获取账户照片信息
	 * 
	 * @param Acc_ID
	 * @return
	 */
	public AccountsPhotos getAccountsPhotosInfo(String Acc_ID);
	
	/**
	 * 获取账户头像图片
	 * 
	 * @param Acc_ID
	 * @return
	 */
	public String getAccountsHeadPhoto(String Acc_ID);
	
	/**
	 * 获取账户其他图片
	 * 
	 * @param Acc_ID
	 * @return
	 */
	public List<String> getAccountsOtherPhotos(String Acc_ID);
}