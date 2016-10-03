package xuyihao.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;
import xuyihao.common.AppPropertiesLoader;
import xuyihao.entity.Accounts;
import xuyihao.entity.Address;
import xuyihao.entity.Cart;
import xuyihao.entity.CommentProduct;
import xuyihao.entity.Orders;
import xuyihao.filerelate.entity.AccountsPhotos;
import xuyihao.filerelate.logic.AccountsPhotosLogic;
import xuyihao.filerelate.logic.PhotoPathLogic;
import xuyihao.logic.AccountsLogic;
import xuyihao.logic.AddressLogic;
import xuyihao.logic.CartLogic;
import xuyihao.logic.CommentProductLogic;
import xuyihao.logic.OrdersLogic;
import xuyihao.service.AccountsService;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.FileUtils;
import xuyihao.tools.utils.ImageUtils;

/**
 * 
 * @Author Xuyh created at 2016年8月26日 下午1:18:39
 */
@MultipartConfig
@Component("AccountsService")
public class AccountsServiceImpl implements AccountsService {
	private String BASE_FILE_PATH = AppPropertiesLoader.getAppProperties().getProperty("BaseFilePath");
	private String RELATIVE_PATH = File.separator + "photos" + File.separator + "accounts" + File.separator;
	private String ABSOLUTE_PATH = BASE_FILE_PATH + RELATIVE_PATH;

	@Autowired
	private AccountsLogic accountsLogic;

	@Autowired
	private AddressLogic addressLogic;

	@Autowired
	private CartLogic cartLogic;

	@Autowired
	private CommentProductLogic commentProductLogic;

	@Autowired
	private OrdersLogic ordersLogic;

	@Autowired
	private AccountsPhotosLogic accountsPhotosLogic;

	@Autowired
	private PhotoPathLogic photoPathLogic;

	/**
	 * 用来保存会话信息
	 */
	private HttpSession session = null;

	public void setAccountsLogic(AccountsLogic accountsLogic) {
		this.accountsLogic = accountsLogic;
	}

	public void setAddressLogic(AddressLogic addressLogic) {
		this.addressLogic = addressLogic;
	}

	public void setCartLogic(CartLogic cartLogic) {
		this.cartLogic = cartLogic;
	}

	public void setCommentProductLogic(CommentProductLogic commentProductLogic) {
		this.commentProductLogic = commentProductLogic;
	}

	public void setOrdersLogic(OrdersLogic ordersLogic) {
		this.ordersLogic = ordersLogic;
	}

	public void setSessionInfo(HttpSession session) {
		this.session = session;
	}

	public void setAccountsPhotosLogic(AccountsPhotosLogic accountsPhotosLogic) {
		this.accountsPhotosLogic = accountsPhotosLogic;
	}

	public void setPhotoPathLogic(PhotoPathLogic photoPathLogic) {
		this.photoPathLogic = photoPathLogic;
	}

	public String isAccountNameExists(String name) {
		JSONObject json = new JSONObject();
		boolean exists = this.accountsLogic.accountNameExist(name);
		if (exists) {
			json.put("exists", true);
		} else {
			json.put("exists", false);
		}
		return json.toString();
	}

	public String register(Accounts account) {
		// XXX 检查是否已经有账号登录
		Object a = this.session.getAttribute("Acc_ID");
		if (a != null) {
			this.session.removeAttribute("Acc_ID");
			this.session.removeAttribute("Acc_name");
		}
		JSONObject json = new JSONObject();
		String Acc_ID = this.accountsLogic.saveAccounts(account);
		if (Acc_ID.equals("")) {
			json.put("result", false);
			json.put("Acc_ID", "");
		} else {
			// XXX 将登录结果存入session中
			this.session.setAttribute("Acc_name", account.getAcc_name());
			this.session.setAttribute("Acc_ID", Acc_ID);
			json.put("result", true);
			json.put("Acc_ID", Acc_ID);
		}
		return json.toString();
	}

	public String login(String name, String password) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.accountsLogic.login(name, password);
		if (Acc_ID.equals("")) {
			json.put("result", false);
			json.put("Acc_ID", "");
		} else {
			// XXX 将登录结果存入session中
			this.session.setAttribute("Acc_name", name);
			this.session.setAttribute("Acc_ID", Acc_ID);
			json.put("result", true);
			json.put("Acc_ID", Acc_ID);
		}
		return json.toString();
	}

	public String logout(String accountId) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		if (Acc_ID.equals(accountId)) {
			// this.session.invalidate();
			// XXX 这里需要思考一下是remove掉attr好还是直接invalidate
			this.session.removeAttribute("Acc_ID");
			this.session.removeAttribute("Acc_name");
			json.put("result", true);
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String changeAccountInformation(Accounts account) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		if (Acc_ID.equals(account.getAcc_ID())) {
			boolean flag = this.accountsLogic.changeAccountInfo(account);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String getAccountInformationByName(String name) {
		JSONObject json = new JSONObject();
		String queryAcc_ID = this.session.getAttribute("Acc_ID").toString();
		Accounts account = this.accountsLogic.getAccountsInformationByName(name);
		if (account.getAcc_ID().equals(queryAcc_ID)) {// 查询自己
			return account.toJSONString();
		} else {
			json.put("Acc_ID", account.getAcc_ID());
			json.put("Acc_name", account.getAcc_name());
			json.put("Acc_sex", account.getAcc_sex());
			json.put("Acc_loc", account.getAcc_loc());
			json.put("Acc_lvl", account.getAcc_lvl());
			json.put("Acc_shop", account.isAcc_shop());
			json.put("Acc_atn", account.getAcc_atn());
			json.put("Acc_atnd", account.getAcc_atnd());
			json.put("Acc_pub", account.getAcc_pub());
			json.put("Acc_addTime", account.getAcc_addTime());
			return json.toString();
		}
	}

	public String getAccountInformationById(String id) {
		JSONObject json = new JSONObject();
		String queryAcc_ID = this.session.getAttribute("Acc_ID").toString();
		Accounts account = this.accountsLogic.getAccountsInformationById(id);
		if (account.getAcc_ID().equals(queryAcc_ID)) {// 查询自己
			return account.toJSONString();
		} else {
			json.put("Acc_ID", account.getAcc_ID());
			json.put("Acc_name", account.getAcc_name());
			json.put("Acc_sex", account.getAcc_sex());
			json.put("Acc_loc", account.getAcc_loc());
			json.put("Acc_lvl", account.getAcc_lvl());
			json.put("Acc_shop", account.isAcc_shop());
			json.put("Acc_atn", account.getAcc_atn());
			json.put("Acc_atnd", account.getAcc_atnd());
			json.put("Acc_pub", account.getAcc_pub());
			json.put("Acc_addTime", account.getAcc_addTime());
			return json.toString();
		}
	}

	public String attention(String atnId, String atndId) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		if (atnId.equals(Acc_ID)) {
			boolean flag = this.accountsLogic.attention(atnId, atndId);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String cancelAttention(String atnId, String atndId) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		if (atnId.equals(Acc_ID)) {
			boolean flag = this.accountsLogic.cancelAtention(atnId, atndId);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String favourite(String accountId, String shopId) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		if (accountId.equals(Acc_ID)) {
			boolean flag = this.accountsLogic.favourite(accountId, shopId);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String cancelFavourite(String accountId, String shopId) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		if (accountId.equals(Acc_ID)) {
			boolean flag = this.accountsLogic.cancelFavourite(accountId, shopId);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String want(String accountId, String productId) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		if (accountId.equals(Acc_ID)) {
			boolean flag = this.accountsLogic.want(accountId, productId);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String cancelWant(String accountId, String productId) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		if (accountId.equals(Acc_ID)) {
			boolean flag = this.accountsLogic.cancelWant(accountId, productId);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String addAddress(Address address) {
		JSONObject json = new JSONObject();
		String requestAccId = this.session.getAttribute("Acc_ID").toString();
		if (requestAccId.equals(address.getAcc_ID())) {// 是否为登录用户
			String Add_ID = this.addressLogic.saveAddress(address);
			if (Add_ID == null || Add_ID.equals("")) {
				json.put("result", false);
				json.put("Add_ID", "");
			} else {
				json.put("result", true);
				json.put("Add_ID", Add_ID);
			}
		} else {
			json.put("result", false);
			json.put("Add_ID", "");
		}
		return json.toString();
	}

	public String deleteAddress(String addressId) {
		JSONObject json = new JSONObject();
		String requestAccId = this.session.getAttribute("Acc_ID").toString();
		Address queryAddress = this.addressLogic.getAddressInfo(addressId);
		if (requestAccId.equals(queryAddress.getAcc_ID())) {// 请求删除自己的收货地址
			boolean flag = this.addressLogic.deleteAddress(addressId);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String changeAddressInformation(Address address) {
		JSONObject json = new JSONObject();
		String requestAccId = this.session.getAttribute("Acc_ID").toString();
		Address requestAddress = this.addressLogic.getAddressInfo(address.getAdd_ID());
		if (requestAccId.equals(requestAddress.getAcc_ID())) {// 请求修改自己的收货地址
			boolean flag = this.addressLogic.changeAddressInfo(address);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String getAddressInformation(String addressId) {
		JSONObject json = new JSONObject();
		String requestAccId = this.session.getAttribute("Acc_ID").toString();
		Address queryAddress = this.addressLogic.getAddressInfo(addressId);
		if (requestAccId.equals(queryAddress.getAcc_ID())) {// 请求获取自己的收货地址信息
			return queryAddress.toJSONString();
		} else {
			json.put("result", false);
			return json.toString();
		}
	}

	public String addCart(Cart cart) {
		JSONObject json = new JSONObject();
		String requestAccId = this.session.getAttribute("Acc_ID").toString();
		if (requestAccId.equals(cart.getAcc_ID())) {// 是否为登录用户
			String Cart_ID = this.cartLogic.saveCart(cart);
			if (Cart_ID == null || Cart_ID.equals("")) {
				json.put("result", false);
				json.put("Cart_ID", "");
			} else {
				json.put("result", true);
				json.put("Cart_ID", Cart_ID);
			}
		} else {
			json.put("result", false);
			json.put("Cart_ID", "");
		}
		return json.toString();
	}

	public String getCartInformation(String cartId) {
		JSONObject json = new JSONObject();
		String requestAccId = this.session.getAttribute("Acc_ID").toString();
		Cart queryCart = this.cartLogic.getCartInfo(cartId);
		if (requestAccId.equals(queryCart.getAcc_ID())) {// 请求获取自己的购物车信息
			return queryCart.toJSONString();
		} else {
			json.put("result", false);
			return json.toString();
		}
	}

	public String changeProductCount(String cartId, int cartCount) {
		JSONObject json = new JSONObject();
		String requestAccId = this.session.getAttribute("Acc_ID").toString();
		Cart queryCart = this.cartLogic.getCartInfo(cartId);
		if (requestAccId.equals(queryCart.getAcc_ID())) {// 请求自己的购物车
			boolean flag = this.cartLogic.changeProductNumber(cartId, cartCount);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String deleteCart(String cartId) {
		JSONObject json = new JSONObject();
		String requestAccId = this.session.getAttribute("Acc_ID").toString();
		Cart queryCart = this.cartLogic.getCartInfo(cartId);
		if (requestAccId.equals(queryCart.getAcc_ID())) {// 请求自己的购物车
			boolean flag = this.cartLogic.deleteCart(cartId);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String addOrder(Orders order) {
		JSONObject json = new JSONObject();
		String requestAccId = this.session.getAttribute("Acc_ID").toString();
		if (requestAccId.equals(order.getAcc_ID())) {// 是否为登录用户
			String Ord_ID = this.ordersLogic.saveOrder(order);
			if (Ord_ID == null || Ord_ID.equals("")) {
				json.put("result", false);
				json.put("Ord_ID", "");
			} else {
				json.put("result", true);
				json.put("Ord_ID", Ord_ID);
			}
		} else {
			json.put("result", false);
			json.put("Ord_ID", "");
		}
		return json.toString();
	}

	public String getOrderInformation(String OrderId) {
		JSONObject json = new JSONObject();
		String requestAccId = this.session.getAttribute("Acc_ID").toString();
		Orders queryOrder = this.ordersLogic.getOrderInfo(OrderId);
		if (requestAccId.equals(queryOrder.getAcc_ID())) {// 请求获取自己的订单信息
			return queryOrder.toJSONString();
		} else {
			json.put("result", false);
			return json.toString();
		}
	}

	public String deleteOrder(String orderId) {
		JSONObject json = new JSONObject();
		String requestAccId = this.session.getAttribute("Acc_ID").toString();
		Orders queryOrder = this.ordersLogic.getOrderInfo(orderId);
		if (requestAccId.equals(queryOrder.getAcc_ID())) {// 请求自己的订单
			boolean flag = this.ordersLogic.deleteOrder(orderId);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String changeOrderInformation(Orders order) {
		JSONObject json = new JSONObject();
		String requestAccId = this.session.getAttribute("Acc_ID").toString();
		Orders queryOrder = this.ordersLogic.getOrderInfo(order.getOrd_ID());
		if (requestAccId.equals(queryOrder.getAcc_ID())) {// 请求自己的订单
			boolean flag = this.ordersLogic.changeOrderInfo(order);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String addCommentProduct(CommentProduct commentProduct) {
		JSONObject json = new JSONObject();
		String requestAccId = this.session.getAttribute("Acc_ID").toString();
		if (requestAccId.equals(commentProduct.getAcc_ID())) {// 是否为登录用户
			String Comm_ID = this.commentProductLogic.saveCommentProduct(commentProduct);
			if (Comm_ID == null || Comm_ID.equals("")) {
				json.put("result", false);
				json.put("Comm_ID", "");
			} else {
				json.put("result", true);
				json.put("Comm_ID", Comm_ID);
			}
		} else {
			json.put("result", false);
			json.put("Comm_ID", "");
		}
		return json.toString();
	}

	public String changeCommentProduct(String commentId, String commentDescription) {
		JSONObject json = new JSONObject();
		String requestAccId = this.session.getAttribute("Acc_ID").toString();
		CommentProduct queryCommentProduct = this.commentProductLogic.getCommentProductInfo(commentId);
		if (requestAccId.equals(queryCommentProduct.getAcc_ID())) {// 请求自己的产品评论
			boolean flag = this.commentProductLogic.changeCommentDescription(commentId, commentDescription);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String getCommentProductInformation(String commentId) {
		JSONObject json = new JSONObject();
		String requestAccId = this.session.getAttribute("Acc_ID").toString();
		CommentProduct queryCommentProduct = this.commentProductLogic.getCommentProductInfo(commentId);
		if (requestAccId.equals(queryCommentProduct.getAcc_ID())) {// 请求获取自己的评论信息
			return queryCommentProduct.toJSONString();
		} else {
			json.put("result", false);
			return json.toString();
		}
	}

	public String saveAccountHeadPhoto(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String absolutePath = ABSOLUTE_PATH;
		FileUtils.checkAndCreateFilePath(absolutePath);
		String requestAccId = this.session.getAttribute("Acc_ID").toString().trim();
		if (request.getParameter("Acc_ID").equals(requestAccId)) {
			try {
				// 检查数据库图片数据是否已经存在
				boolean exists = false;
				AccountsPhotos accountsPhoto = this.accountsPhotosLogic.getAccountsPhotosInfo(requestAccId);
				if (accountsPhoto.get_id() != 0) {
					exists = true;
					this.photoPathLogic.deletePhotoPath(accountsPhoto.getHeadPhoto_ID());
				}
				Part part = request.getPart("file");
				if (part == null) {
					json.put("result", false);
				} else {
					String fileTypeName = "." + FileUtils.getFileType(part);
					String newFileName = "headPhoto" + requestAccId + DateUtils.currentDate() + fileTypeName;
					String newFileThumbnailName = "headThumbnailPhoto" + requestAccId + DateUtils.currentDate() + fileTypeName;
					// 保存原始图片
					FileUtils.writePartToDisk(part, absolutePath + newFileName);
					// 生成并保存缩略图
					ImageUtils.zoomImageScale(absolutePath + newFileName, absolutePath + newFileThumbnailName, 448);
					// 数据库保存图片数据
					String photoId = this.photoPathLogic.savePhotoPath(newFileName, newFileThumbnailName);
					boolean result;
					if (!exists) {
						result = this.accountsPhotosLogic.saveAccountsPhotos(requestAccId, photoId, null);
					} else {
						result = this.accountsPhotosLogic.changeAccountsPhotosInfo(requestAccId, photoId, null);
					}
					if (result) {
						json.put("result", true);
					} else {
						json.put("result", false);
					}
				}
			} catch (ServletException e1) {
				e1.printStackTrace();
				json.put("result", false);
			} catch (IOException e) {
				e.printStackTrace();
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String saveAccountPhotos(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String absolutePath = ABSOLUTE_PATH;
		FileUtils.checkAndCreateFilePath(absolutePath);
		String requestAccId = this.session.getAttribute("Acc_ID").toString().trim();
		if (request.getParameter("Acc_ID").equals(requestAccId)) {
			try {
				// 检查数据库图片数据是否已经存在
				boolean exists = false;
				AccountsPhotos accountsPhoto = this.accountsPhotosLogic.getAccountsPhotosInfo(requestAccId);
				if (accountsPhoto.get_id() != 0) {
					exists = true;
					for (String id : this.accountsPhotosLogic.getAccountsOtherPhotos(requestAccId)) {
						this.photoPathLogic.deletePhotoPath(id);
					}
				}
				int counter = 0;
				List<String> combine = new ArrayList<String>();
				while (true) {
					Part part = request.getPart("file" + counter);
					counter++;
					if (part == null) {
						break;
					} else {
						String fileTypeName = "." + FileUtils.getFileType(part);
						String newFileName = "combinePhoto" + (counter - 1) + requestAccId + DateUtils.currentDate() + fileTypeName;
						String newFileThumbnailName = "combineThumbnailPhoto" + (counter - 1) + requestAccId
								+ DateUtils.currentDate() + fileTypeName;
						// 保存原始图片
						FileUtils.writePartToDisk(part, absolutePath + newFileName);
						// 生成并保存缩略图
						ImageUtils.zoomImageScale(absolutePath + newFileName, absolutePath + newFileThumbnailName, 448);
						// 数据库保存图片数据
						String photoId = this.photoPathLogic.savePhotoPath(newFileName, newFileThumbnailName);
						combine.add(photoId);
					}
				}
				boolean result;
				if (!exists) {
					result = this.accountsPhotosLogic.saveAccountsPhotos(requestAccId, null, combine);
				} else {
					result = this.accountsPhotosLogic.changeAccountsPhotosInfo(requestAccId, null, combine);
				}
				if (result) {
					json.put("result", true);
				} else {
					json.put("result", false);
				}
			} catch (ServletException e1) {
				e1.printStackTrace();
				json.put("result", false);
			} catch (IOException e) {
				e.printStackTrace();
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String changeAccountHeadPhoto(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String absolutePath = ABSOLUTE_PATH;
		FileUtils.checkAndCreateFilePath(absolutePath);
		String requestAccId = this.session.getAttribute("Acc_ID").toString().trim();
		if (request.getParameter("Acc_ID").equals(requestAccId)) {
			try {
				// 检查数据库图片数据是否已经存在
				AccountsPhotos accountsPhoto = this.accountsPhotosLogic.getAccountsPhotosInfo(requestAccId);
				if (accountsPhoto.get_id() == 0) {
					json.put("result", false);
				} else {
					Part part = request.getPart("file");
					if (part == null) {
						json.put("result", false);
					} else {
						String fileTypeName = "." + FileUtils.getFileType(part);
						String newFileName = "headPhoto" + requestAccId + DateUtils.currentDate() + fileTypeName;
						String newFileThumbnailName = "headThumbnailPhoto" + requestAccId + DateUtils.currentDate() + fileTypeName;
						// 保存原始图片
						FileUtils.writePartToDisk(part, absolutePath + newFileName);
						// 生成并保存缩略图
						ImageUtils.zoomImageScale(absolutePath + newFileName, absolutePath + newFileThumbnailName, 448);
						// 数据库保存图片数据
						this.photoPathLogic.deletePhotoPath(accountsPhoto.getHeadPhoto_ID());
						String photoId = this.photoPathLogic.savePhotoPath(newFileName, newFileThumbnailName);
						boolean result = this.accountsPhotosLogic.changeAccountsPhotosInfo(requestAccId, photoId, null);
						if (result) {
							json.put("result", true);
						} else {
							json.put("result", false);
						}
					}
				}
			} catch (ServletException e1) {
				e1.printStackTrace();
				json.put("result", false);
			} catch (IOException e) {
				e.printStackTrace();
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String changeAccountPhotos(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String absolutePath = ABSOLUTE_PATH;
		FileUtils.checkAndCreateFilePath(absolutePath);
		String requestAccId = this.session.getAttribute("Acc_ID").toString().trim();
		if (request.getParameter("Acc_ID").equals(requestAccId)) {
			try {
				// 检查数据库图片数据是否已经存在
				AccountsPhotos accountsPhoto = this.accountsPhotosLogic.getAccountsPhotosInfo(requestAccId);
				if (accountsPhoto.get_id() == 0) {
					json.put("result", false);
				} else {
					for (String id : this.accountsPhotosLogic.getAccountsOtherPhotos(requestAccId)) {
						this.photoPathLogic.deletePhotoPath(id);
					}
					int counter = 0;
					List<String> combine = new ArrayList<String>();
					while (true) {
						Part part = request.getPart("file" + counter);
						counter++;
						if (part == null) {
							break;
						} else {
							String fileTypeName = "." + FileUtils.getFileType(part);
							String newFileName = "combinePhoto" + (counter - 1) + requestAccId + DateUtils.currentDate()
									+ fileTypeName;
							String newFileThumbnailName = "combineThumbnailPhoto" + (counter - 1) + requestAccId
									+ DateUtils.currentDate() + fileTypeName;
							// 保存原始图片
							FileUtils.writePartToDisk(part, absolutePath + newFileName);
							// 生成并保存缩略图
							ImageUtils.zoomImageScale(absolutePath + newFileName, absolutePath + newFileThumbnailName, 448);
							// 数据库保存图片数据
							String photoId = this.photoPathLogic.savePhotoPath(newFileName, newFileThumbnailName);
							combine.add(photoId);
						}
					}
					boolean result;
					result = this.accountsPhotosLogic.changeAccountsPhotosInfo(requestAccId, null, combine);
					if (result) {
						json.put("result", true);
					} else {
						json.put("result", false);
					}
				}
			} catch (ServletException e1) {
				e1.printStackTrace();
				json.put("result", false);
			} catch (IOException e) {
				e.printStackTrace();
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String getAccountHeadPhotoId(String Acc_ID) {
		JSONObject json = new JSONObject();
		String Photo_ID = this.accountsPhotosLogic.getAccountsHeadPhoto(Acc_ID);
		json.put("headPhotoId", Photo_ID);
		return json.toString();
	}

	public String getAccountPhotosId(String Acc_ID) {
		JSONObject json = new JSONObject();
		List<String> Photo_IDList = this.accountsPhotosLogic.getAccountsOtherPhotos(Acc_ID);
		json.put("combinePhotoId", Photo_IDList);
		return json.toString();
	}

	public String getPhotoById(String Photo_ID) {
		String pathName = this.photoPathLogic.getPhotoPathInfo(Photo_ID).getPhoto_pathName();
		String realPath = ABSOLUTE_PATH + pathName;
		return realPath;
	}

	public String getThumbnailPhotoById(String Photo_ID) {
		String pathName = this.photoPathLogic.getPhotoPathInfo(Photo_ID).getThumbnail_pathName();
		String realPath = ABSOLUTE_PATH + pathName;
		return realPath;
	}
}