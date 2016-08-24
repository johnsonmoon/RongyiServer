package com.xuyihao.logic;

import com.xuyihao.entity.Accounts;

/**
 * 账户逻辑
 *
 * Created by Administrator on 2016/7/20.
 */
public interface AccountsLogic {
	/**
	 * 检查帐号名是否存在
	 *
	 * @param Acc_name 帐号名
	 * @return
	 */
	public boolean accountNameExist(String Acc_name);

	/**
	 * 注册
	 *
	 * @param accounts 帐号对象
	 * @return Acc_ID if register successfully, also means login successfully
	 */
	public String saveAccounts(Accounts accounts);

	/**
	 * method for account user login, if failed, return ZERO
	 *
	 * @param accounts 帐号对象
	 * @return Acc_ID if login successfully
	 */
	public String login(String Acc_name, String Acc_pwd);

	/**
	 * Method for complete information of the accounts.
	 *
	 * @param accounts accounts 帐号对象
	 * @return Acc_ID if complete Account information successfully
	 */
	public boolean changeAccountInfo(Accounts accounts);

	/**
	 * method getting Account information
	 *
	 * @param Acc_name 帐号名
	 * @return Accounts instance
	 */
	public Accounts getAccountsInformationByName(String Acc_name);

	/**
	 * 
	 * @param Acc_ID
	 * @return
	 */
	public Accounts getAccountsInformationById(String Acc_ID);

	/**
	 * method attention(for account attend anther account)
	 *
	 * @param atn_Id 关注者Id
	 * @param atned_Id 被关注者Id
	 * @return true if successfully, 返回true如果关注成功
	 */
	public boolean attention(String atn_Id, String atned_Id);

	/**
	 * method cancel attention
	 *
	 * @param atn_Id 取消关注者Id
	 * @param atned_Id 被关注者Id
	 * @return true if cancel successfully, 返回true如果取消关注成功
	 */
	public boolean cancelAtention(String atn_Id, String atned_Id);

	/**
	 * method account favourite shop
	 *
	 * @param Acc_Id 用户ID
	 * @param Shop_ID 店铺ID
	 * @return true if successfully, 返回true如果用户收藏店铺成功
	 */
	public boolean favourite(String Acc_Id, String Shop_ID);

	/**
	 * method cancel account favourite shop
	 *
	 * @param Acc_ID 用户ID
	 * @param Shop_ID 店铺ID
	 * @return true if successfully, 返回true如果用户取消关注店铺成功
	 */
	public boolean cancelFavourite(String Acc_ID, String Shop_ID);

	/**
	 * method account want product 用户收藏产品
	 *
	 * @param Acc_ID 用户ID
	 * @param Prod_ID 产品ID
	 * @return true if successfully
	 */
	public boolean want(String Acc_ID, String Prod_ID);

	/**
	 * method account cancel want product 用户取消收藏商品
	 *
	 * @param Acc_ID 用户ID
	 * @param Prod_ID 产品ID
	 * @return true if successfully
	 */
	public boolean cancelWant(String Acc_ID, String Prod_ID);
}