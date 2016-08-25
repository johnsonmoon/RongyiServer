package com.xuyihao.service.impl;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import com.xuyihao.common.ThreadLocalContext;
import com.xuyihao.entity.Accounts;
import com.xuyihao.entity.Address;
import com.xuyihao.entity.Cart;
import com.xuyihao.entity.CommentProduct;
import com.xuyihao.entity.Orders;
import com.xuyihao.logic.AccountsLogic;
import com.xuyihao.logic.AddressLogic;
import com.xuyihao.logic.CartLogic;
import com.xuyihao.logic.CommentProductLogic;
import com.xuyihao.logic.OrdersLogic;
import com.xuyihao.service.AccountsService;

import net.sf.json.JSONObject;

@MultipartConfig
public class AccountsServlet extends HttpServlet implements AccountsService {

	/**
	 * <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 9019941573067632809L;

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

	private HttpSession session = null;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置会话信息
		this.session = request.getSession();
		String action = request.getParameter("action").trim();
		if (action.equals("accountNameExists")) {
			String message = this.isAccountNameExists(request.getParameter("Acc_name"));
			response.getWriter().println(message);
		} else if (action.equals("register")) {
			Accounts account = new Accounts();
			account.setAcc_name(request.getParameter("Acc_name"));
			account.setAcc_pwd(request.getParameter("Acc_pwd"));
			account.setAcc_sex(request.getParameter("Acc_sex"));
			account.setAcc_loc(request.getParameter("Acc_loc"));
			account.setAcc_no(request.getParameter("Acc_no"));
			account.setAcc_name2(request.getParameter("Acc_name2"));
			account.setAcc_tel(request.getParameter("Acc_tel"));
			String message = this.register(account);
			response.getWriter().println(message);
		} else if (action.equals("login")) {
			String Acc_name = request.getParameter("Acc_name");
			String Acc_pwd = request.getParameter("Acc_pwd");
			String message = this.login(Acc_name, Acc_pwd);
			response.getWriter().println(message);
		} else if (action.equals("logout")) {
			String Acc_ID = session.getAttribute("Acc_ID").toString();
			String message = this.logout(Acc_ID);
			response.getWriter().println(message);
		} else if (action.equals("changeAccInfo")) {
			Accounts account = new Accounts();
			// XXX Acc_ID必须设置
			account.setAcc_ID(session.getAttribute("Acc_ID").toString());
			account.setAcc_name(request.getParameter("Acc_name"));
			account.setAcc_pwd(request.getParameter("Acc_pwd"));
			account.setAcc_sex(request.getParameter("Acc_sex"));
			account.setAcc_loc(request.getParameter("Acc_loc"));
			account.setAcc_shop(Boolean.parseBoolean(request.getParameter("Acc_shop")));
			account.setAcc_no(request.getParameter("Acc_no"));
			account.setAcc_name2(request.getParameter("Acc_name2"));
			account.setAcc_tel(request.getParameter("Acc_tel"));
			String message = this.changeAccountInformation(account);
			response.getWriter().println(message);
		} else if (action.equals("getAccInfoByName")) {
			String Acc_name = request.getParameter("Acc_name");
			String message = this.getAccountInformationByName(Acc_name);
			response.getWriter().println(message);
		} else if (action.equals("getAccInfoById")) {
			String Acc_ID = request.getParameter("Acc_ID");
			String message = this.getAccountInformationById(Acc_ID);
			response.getWriter().println(message);
		} else if (action.equals("att")) {
			String atnId = session.getAttribute("Acc_ID").toString();
			String atndId = request.getParameter("atndId");
			String message = this.attention(atnId, atndId);
			response.getWriter().println(message);
		} else if (action.equals("cancelAtt")) {
			String atnId = session.getAttribute("Acc_ID").toString();
			String atndId = request.getParameter("atndId");
			String message = this.cancelAttention(atnId, atndId);
			response.getWriter().println(message);
		} else if (action.equals("favo")) {
			String accountId = session.getAttribute("Acc_ID").toString();
			String shopId = request.getParameter("Shop_ID");
			String message = this.favourite(accountId, shopId);
			response.getWriter().println(message);
		} else if (action.equals("cancelFavo")) {
			String accountId = session.getAttribute("Acc_ID").toString();
			String shopId = request.getParameter("Shop_ID");
			String message = this.cancelFavourite(accountId, shopId);
			response.getWriter().println(message);
		} else if (action.equals("want")) {
			String accountId = session.getAttribute("Acc_ID").toString();
			String productId = request.getParameter("Prod_ID");
			String message = this.want(accountId, productId);
			response.getWriter().println(message);
		} else if (action.equals("cancelWant")) {
			String accountId = session.getAttribute("Acc_ID").toString();
			String productId = request.getParameter("Prod_ID");
			String message = this.cancelWant(accountId, productId);
			response.getWriter().println(message);
		} else if (action.equals("addAdd")) {
			Address address = new Address();
			address.setAdd_info(request.getParameter("Add_info"));
			// XXX 账户ID设置为当前会话的账户ID
			address.setAcc_ID(session.getAttribute("Acc_ID").toString());
			address.setConsign(request.getParameter("Consign"));
			address.setCon_tel(request.getParameter("Con_tel"));
			String message = this.addAddress(address);
			response.getWriter().println(message);
		} else if (action.equals("deleteAdd")) {
			String addressId = request.getParameter("Add_ID");
			String message = this.deleteAddress(addressId);
			response.getWriter().println(message);
		} else if (action.equals("changeAddInfo")) {
			Address address = new Address();
			// XXX Add_ID必须设置
			address.setAdd_ID(request.getParameter("Add_ID"));
			address.setConsign(request.getParameter("Consign"));
			address.setCon_tel(request.getParameter("Con_tel"));
			address.setAdd_info(request.getParameter("Add_info"));
			String message = this.changeAddressInformation(address);
			response.getWriter().println(message);
		} else if (action.equals("getAddInfo")) {
			String addressId = request.getParameter("Add_ID");
			String message = this.getAddressInformation(addressId);
			response.getWriter().println(message);
		} else if (action.equals("addCart")) {
			Cart cart = new Cart();
			cart.setAcc_ID(session.getAttribute("Acc_ID").toString());
			cart.setProd_ID(request.getParameter("Prod_ID"));
			cart.setPro_num(Integer.parseInt(request.getParameter("Pro_num")));
			cart.setProd_price(Float.parseFloat(request.getParameter("Prod_price")));
			String message = this.addCart(cart);
			response.getWriter().println(message);
		} else if (action.equals("getCartInfo")) {
			String cartId = request.getParameter("Cart_ID");
			String message = this.getCartInformation(cartId);
			response.getWriter().println(message);
		} else if (action.equals("changeProdCount")) {
			String cartId = request.getParameter("Cart_ID");
			int cartCount = Integer.parseInt(request.getParameter("Pro_num"));
			String message = this.changeProductCount(cartId, cartCount);
			response.getWriter().println(message);
		} else if (action.equals("deleteCart")) {
			String cartId = request.getParameter("Cart_ID");
			String message = this.deleteCart(cartId);
			response.getWriter().println(message);
		} else if (action.equals("addOrd")) {
			Orders order = new Orders();
			order.setAcc_ID(session.getAttribute("Acc_ID").toString());
			order.setProd_ID(request.getParameter("Prod_ID"));
			order.setProd_price(Float.parseFloat(request.getParameter("Prod_price")));
			order.setPro_num(Integer.parseInt(request.getParameter("Pro_num")));
			order.setAdd_ID(request.getParameter("Add_ID"));
			String message = this.addOrder(order);
			response.getWriter().println(message);
		} else if (action.equals("getOrdInfo")) {
			String orderId = request.getParameter("Ord_ID");
			String message = this.getOrderInformation(orderId);
			response.getWriter().println(message);
		} else if (action.equals("deleteOrd")) {
			String orderId = request.getParameter("Ord_ID");
			String message = this.deleteOrder(orderId);
			response.getWriter().println(message);
		} else if (action.equals("changeOrdInfo")) {
			Orders order = new Orders();
			// XXX Ord_ID 为必须量
			order.setOrd_ID(request.getParameter("Ord_ID"));
			order.setProd_ID(request.getParameter("Prod_ID"));
			order.setProd_price(Float.parseFloat(request.getParameter("Prod_price")));
			order.setPro_num(Integer.parseInt(request.getParameter("Pro_num")));
			order.setAdd_ID(request.getParameter("Add_ID"));
			String message = this.changeOrderInformation(order);
			response.getWriter().println(message);
		} else if (action.equals("commProd")) {
			CommentProduct commentProduct = new CommentProduct();
			commentProduct.setAcc_ID(session.getAttribute("Acc_ID").toString());
			commentProduct.setProd_ID(request.getParameter("Prod_ID"));
			commentProduct.setOrd_ID(request.getParameter("Ord_ID"));
			commentProduct.setComm_desc(request.getParameter("Comm_desc"));
			String message = this.addCommentProduct(commentProduct);
			response.getWriter().println(message);
		} else if (action.equals("changeCommProd")) {
			String commentId = request.getParameter("Comm_ID");
			String commentDescription = request.getParameter("Comm_desc");
			String message = this.changeCommentProduct(commentId, commentDescription);
			response.getWriter().println(message);
		} else if (action.equals("getCommProdInfo")) {
			String commentId = request.getParameter("Comm_ID");
			String message = this.getCommentProductInformation(commentId);
			response.getWriter().println(message);
		} else {
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// XXX servlet中没法通过Autowired注解从Spring容器中获取logic
		ServletContext ctx = this.getServletContext();
		WebApplicationContext context = ThreadLocalContext.setContextAndRet(ctx);
		this.accountsLogic = (AccountsLogic) context.getBean("AccountsLogic");
		this.addressLogic = (AddressLogic) context.getBean("AddressLogic");
		this.cartLogic = (CartLogic) context.getBean("CartLogic");
		this.commentProductLogic = (CommentProductLogic) context.getBean("CommentProductLogic");
		this.ordersLogic = (OrdersLogic) context.getBean("OrdersLogic");
	}

	@Override
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

	@Override
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
		} else {
			// XXX 将登录结果存入session中
			this.session.setAttribute("Acc_name", account.getAcc_name());
			this.session.setAttribute("Acc_ID", Acc_ID);
			json.put("result", true);
		}
		return json.toString();
	}

	@Override
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

	@Override
	public String login(String name, String password) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.accountsLogic.login(name, password);
		if (Acc_ID.equals("")) {
			json.put("result", false);
		} else {
			// XXX 将登录结果存入session中
			this.session.setAttribute("Acc_name", name);
			this.session.setAttribute("Acc_ID", Acc_ID);
			json.put("result", true);
		}
		return json.toString();
	}

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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
}