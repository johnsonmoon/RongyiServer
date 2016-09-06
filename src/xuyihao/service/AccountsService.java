package xuyihao.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xuyihao.entity.Accounts;
import xuyihao.entity.Address;
import xuyihao.entity.Cart;
import xuyihao.entity.CommentProduct;
import xuyihao.entity.Orders;

/**
 * 账户有关的服务接口
 * 
 * @Author Xuyh created at Aug 22, 2016 11:24:54 PM.
 *
 */
public interface AccountsService {
	/**
	 * XXX AccountsLogic
	 */
	/**
	 * 设置会话信息(根据不同的MVC框架进行变化)
	 * 
	 * @param session
	 */
	public void setSessionInfo(HttpSession session);

	/**
	 * 账户名是否存在
	 * 
	 * @param name
	 * @return
	 */
	public String isAccountNameExists(String name);

	/**
	 * 注册账户
	 * 
	 * @param account
	 * @return
	 */
	public String register(Accounts account);

	/**
	 * 登录
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public String login(String name, String password);

	/**
	 * 登出
	 * 
	 * @param accountId
	 * @return
	 */
	public String logout(String accountId);

	/**
	 * 修改账户信息
	 * 
	 * @param account
	 * @return
	 */
	public String changeAccountInformation(Accounts account);

	/**
	 * 通过账户名查询账户信息
	 * 
	 * @param name
	 * @return
	 */
	public String getAccountInformationByName(String name);

	/**
	 * 通过账户ID查询账户信息
	 * 
	 * @param id
	 * @return
	 */
	public String getAccountInformationById(String id);

	/**
	 * 关注账户
	 * 
	 * @param atnId
	 * @param atndId
	 * @return
	 */
	public String attention(String atnId, String atndId);

	/**
	 * 取消关注
	 * 
	 * @param atnId
	 * @param atndId
	 * @return
	 */
	public String cancelAttention(String atnId, String atndId);

	/**
	 * 收藏店铺
	 * 
	 * @param accountId
	 * @param shopId
	 * @return
	 */
	public String favourite(String accountId, String shopId);

	/**
	 * 取消收藏店铺
	 * 
	 * @param accountId
	 * @param shopId
	 * @return
	 */
	public String cancelFavourite(String accountId, String shopId);

	/**
	 * 收藏产品
	 * 
	 * @param accountId
	 * @param productId
	 * @return
	 */
	public String want(String accountId, String productId);

	/**
	 * 取消收藏产品
	 * 
	 * @param accountId
	 * @param productId
	 * @return
	 */
	public String cancelWant(String accountId, String productId);

	/**
	 * XXX AddressLogic
	 */
	/**
	 * 添加收获地址
	 * 
	 * @param address
	 * @return
	 */
	public String addAddress(Address address);

	/**
	 * 删除收获地址
	 * 
	 * @param addressId
	 * @return
	 */
	public String deleteAddress(String addressId);

	/**
	 * 修改收获地址信息
	 * 
	 * @param address
	 * @return
	 */
	public String changeAddressInformation(Address address);

	/**
	 * 获取收获地址信息
	 * 
	 * @param addressId
	 * @return
	 */
	public String getAddressInformation(String addressId);

	/**
	 * XXX CartLogic
	 */
	/**
	 * 添加购物车
	 * 
	 * @param cart
	 * @return
	 */
	public String addCart(Cart cart);

	/**
	 * 获取购物车信息
	 * 
	 * @param cartId
	 * @return
	 */
	public String getCartInformation(String cartId);

	/**
	 * 修改购物车产品数量
	 * 
	 * @param cartId
	 * @param cartCount
	 * @return
	 */
	public String changeProductCount(String cartId, int cartCount);

	/**
	 * 删除购物车
	 * 
	 * @param cartId
	 * @return
	 */
	public String deleteCart(String cartId);

	/**
	 * XXX OrdersLogic
	 */
	/**
	 * 新建订单
	 * 
	 * @param order
	 * @return
	 */
	public String addOrder(Orders order);

	/**
	 * 获取订单信息
	 * 
	 * @param OrderId
	 * @return
	 */
	public String getOrderInformation(String OrderId);

	/**
	 * 删除订单
	 * 
	 * @param orderId
	 * @return
	 */
	public String deleteOrder(String orderId);

	/**
	 * 修改订单信息
	 * 
	 * @param order
	 * @return
	 */
	public String changeOrderInformation(Orders order);

	/**
	 * XXX CommentProductLogic
	 */
	/**
	 * 用户评论产品
	 * 
	 * @param commentProduct
	 * @return
	 */
	public String addCommentProduct(CommentProduct commentProduct);

	/**
	 * 用户修改评论产品
	 * 
	 * @param commentId
	 * @param commentDescription
	 * @return
	 */
	public String changeCommentProduct(String commentId, String commentDescription);

	/**
	 * 获取用户评论产品信息
	 * 
	 * @param commentId
	 * @return
	 */
	public String getCommentProductInformation(String commentId);

	//TODO ---------------------- 照片模块 ----------------------------------------------

	/**
	 * 保存账号头像
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String saveAccountHeadPhoto(HttpServletRequest request);

	/**
	 * 保存账号照片集
	 * 
	 * @param request
	 * @return
	 */
	public String saveAccountPhotos(HttpServletRequest request);

	/**
	 * 修改账号头像
	 * 
	 * @param request
	 * @return
	 */
	public String changeAccountHeadPhoto(HttpServletRequest request);

	/**
	 * 修改账号图片集
	 * 
	 * @param request
	 * @return
	 */
	public String changeAccountPhotos(HttpServletRequest request);

	/**
	 * 获取账户照片id
	 * 
	 * @param Acc_ID
	 * @param response
	 * @return
	 */
	public String getAccountHeadPhotoId(String Acc_ID);

	/**
	 * 获取账户照片集id
	 * 
	 * @param Acc_ID
	 * @param response
	 * @return
	 */
	public String getAccountPhotosId(String Acc_ID);

	/**
	 * 获取图片
	 * 
	 * @param Photo_ID
	 * @return
	 */
	public String getPhotoById(String Photo_ID, HttpServletResponse response);

	/**
	 * 获取缩略图
	 * 
	 * @param Photo_ID
	 * @return
	 */
	public String getThumbnailPhotoById(String Photo_ID, HttpServletResponse response);
}