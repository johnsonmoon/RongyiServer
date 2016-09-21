package xuyihao.servlet;

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

import xuyihao.common.ThreadLocalContext;
import xuyihao.entity.Accounts;
import xuyihao.entity.Address;
import xuyihao.entity.Cart;
import xuyihao.entity.CommentProduct;
import xuyihao.entity.Orders;
import xuyihao.service.AccountsService;
import xuyihao.tools.utils.RespondUtils;

/**
 * 
 * @Author Xuyh created at 2016年8月26日 下午1:17:07
 */
@MultipartConfig
public class AccountsServlet extends HttpServlet {

	/**
	 * <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 9019941573067632809L;

	@Autowired
	private AccountsService accountsService;

	private HttpSession session = null;

	public void setAccountsService(AccountsService accountsService) {
		this.accountsService = accountsService;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// XXX servlet中没法通过Autowired注解从Spring容器中获取logic
		ServletContext ctx = this.getServletContext();
		WebApplicationContext context = ThreadLocalContext.setContextAndRet(ctx);
		this.accountsService = (AccountsService) context.getBean("AccountsService");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置会话信息
		this.session = request.getSession();
		this.accountsService.setSessionInfo(session);
		String action = request.getParameter("action");
		if (action == null || action.equals("")) {
			return;
		}
		action = action.trim();
		switch (action) {
		case "accountNameExists":
			this.isAccountNameExists(request, response);
			break;
		case "register":
			this.register(request, response);
			break;
		case "login":
			this.login(request, response);
			break;
		case "logout":
			this.logout(request, response);
			break;
		case "changeAccInfo":
			this.changeAccountInformation(request, response);
			break;
		case "getAccInfoByName":
			this.getAccountInformationByName(request, response);
			break;
		case "getAccInfoById":
			this.getAccountInformationById(request, response);
			break;
		case "att":
			this.attention(request, response);
			break;
		case "cancelAtt":
			this.cancelAttention(request, response);
			break;
		case "favo":
			this.favourite(request, response);
			break;
		case "cancelFavo":
			this.cancelFavourite(request, response);
			break;
		case "want":
			this.want(request, response);
			break;
		case "cancelWant":
			this.cancelWant(request, response);
			break;
		case "addAdd":
			this.addAddress(request, response);
			break;
		case "deleteAdd":
			this.deleteAddress(request, response);
			break;
		case "changeAddInfo":
			this.changeAddressInformation(request, response);
			break;
		case "getAddInfo":
			this.getAddressInformation(request, response);
			break;
		case "addCart":
			this.addCart(request, response);
			break;
		case "getCartInfo":
			this.getCartInformation(request, response);
			break;
		case "changeProdCount":
			this.changeProductCount(request, response);
			break;
		case "deleteCart":
			this.deleteCart(request, response);
			break;
		case "addOrd":
			this.addOrder(request, response);
			break;
		case "getOrdInfo":
			this.getOrderInformation(request, response);
			break;
		case "deleteOrd":
			this.deleteOrder(request, response);
			break;
		case "changeOrdInfo":
			this.changeOrderInformation(request, response);
			break;
		case "commProd":
			this.addCommentProduct(request, response);
			break;
		case "changeCommProd":
			this.changeCommentProduct(request, response);
			break;
		case "getCommProdInfo":
			this.getCommentProductInformation(request, response);
			break;
		case "addHeadPhoto":
			this.saveAccHeadPhoto(request, response);
			break;
		case "addPhotos":
			this.saveAccPhotos(request, response);
			break;
		case "changeHeadPhoto":
			this.changeAccHeadPhoto(request, response);
			break;
		case "changePhotos":
			this.changeAccPhotos(request, response);
			break;
		case "getHeadPhotoId":
			this.getAccHeadPhotoId(request, response);
			break;
		case "getPhotosId":
			this.getAccPhotosId(request, response);
			break;
		case "getPhotoById":
			this.getPhotoById(request, response);
			break;
		case "getThumbnailPhotoById":
			this.getThumbnailPhotoById(request, response);
			break;
		}
	}

	public void isAccountNameExists(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = this.accountsService.isAccountNameExists(request.getParameter("Acc_name"));
		RespondUtils.PrintString(response, message);
	}

	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Accounts account = new Accounts();
		account.setAcc_name(request.getParameter("Acc_name"));
		account.setAcc_pwd(request.getParameter("Acc_pwd"));
		account.setAcc_sex(request.getParameter("Acc_sex"));
		account.setAcc_loc(request.getParameter("Acc_loc"));
		account.setAcc_no(request.getParameter("Acc_no"));
		account.setAcc_name2(request.getParameter("Acc_name2"));
		account.setAcc_tel(request.getParameter("Acc_tel"));
		String message = this.accountsService.register(account);
		RespondUtils.PrintString(response, message);
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String message = this.accountsService.logout(Acc_ID);
		RespondUtils.PrintString(response, message);
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Acc_name = request.getParameter("Acc_name");
		String Acc_pwd = request.getParameter("Acc_pwd");
		String message = this.accountsService.login(Acc_name, Acc_pwd);
		RespondUtils.PrintString(response, message);
	}

	public void changeAccountInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		Accounts account = new Accounts();
		// XXX Acc_ID必须设置
		account.setAcc_ID(Acc_ID);
		account.setAcc_name(request.getParameter("Acc_name"));
		account.setAcc_pwd(request.getParameter("Acc_pwd"));
		account.setAcc_sex(request.getParameter("Acc_sex"));
		account.setAcc_loc(request.getParameter("Acc_loc"));
		boolean ownShop = false;
		try {
			ownShop = Boolean.parseBoolean(request.getParameter("Acc_shop"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		account.setAcc_shop(ownShop);
		account.setAcc_no(request.getParameter("Acc_no"));
		account.setAcc_name2(request.getParameter("Acc_name2"));
		account.setAcc_tel(request.getParameter("Acc_tel"));
		String message = this.accountsService.changeAccountInformation(account);
		RespondUtils.PrintString(response, message);
	}

	public void getAccountInformationByName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_name = request.getParameter("Acc_name");
		String message = this.accountsService.getAccountInformationByName(Acc_name);
		RespondUtils.PrintString(response, message);
	}

	public void getAccountInformationById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = request.getParameter("Acc_ID");
		String message = this.accountsService.getAccountInformationById(Acc_ID);
		RespondUtils.PrintString(response, message);
	}

	public void attention(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String atnId = Acc_ID;
		String atndId = request.getParameter("atndId");
		String message = this.accountsService.attention(atnId, atndId);
		RespondUtils.PrintString(response, message);
	}

	public void cancelAttention(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String atnId = Acc_ID;
		String atndId = request.getParameter("atndId");
		String message = this.accountsService.cancelAttention(atnId, atndId);
		RespondUtils.PrintString(response, message);
	}

	public void favourite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String accountId = Acc_ID;
		String shopId = request.getParameter("Shop_ID");
		String message = this.accountsService.favourite(accountId, shopId);
		RespondUtils.PrintString(response, message);
	}

	public void cancelFavourite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String accountId = Acc_ID;
		String shopId = request.getParameter("Shop_ID");
		String message = this.accountsService.cancelFavourite(accountId, shopId);
		RespondUtils.PrintString(response, message);
	}

	public void want(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String accountId = Acc_ID;
		String productId = request.getParameter("Prod_ID");
		String message = this.accountsService.want(accountId, productId);
		RespondUtils.PrintString(response, message);
	}

	public void cancelWant(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String accountId = Acc_ID;
		String productId = request.getParameter("Prod_ID");
		String message = this.accountsService.cancelWant(accountId, productId);
		RespondUtils.PrintString(response, message);
	}

	public void addAddress(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		Address address = new Address();
		address.setAdd_info(request.getParameter("Add_info"));
		// XXX 账户ID设置为当前会话的账户ID
		address.setAcc_ID(Acc_ID);
		address.setConsign(request.getParameter("Consign"));
		address.setCon_tel(request.getParameter("Con_tel"));
		String message = this.accountsService.addAddress(address);
		RespondUtils.PrintString(response, message);
	}

	public void deleteAddress(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String addressId = request.getParameter("Add_ID");
		String message = this.accountsService.deleteAddress(addressId);
		RespondUtils.PrintString(response, message);
	}

	public void changeAddressInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		Address address = new Address();
		// XXX Add_ID必须设置
		address.setAdd_ID(request.getParameter("Add_ID"));
		address.setConsign(request.getParameter("Consign"));
		address.setCon_tel(request.getParameter("Con_tel"));
		address.setAdd_info(request.getParameter("Add_info"));
		String message = this.accountsService.changeAddressInformation(address);
		RespondUtils.PrintString(response, message);
	}

	public void getAddressInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String addressId = request.getParameter("Add_ID");
		String message = this.accountsService.getAddressInformation(addressId);
		RespondUtils.PrintString(response, message);
	}

	public void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		Cart cart = new Cart();
		cart.setAcc_ID(Acc_ID);
		cart.setProd_ID(request.getParameter("Prod_ID"));
		float price = 0f;
		int num = 0;
		try {
			price = Float.parseFloat(request.getParameter("Prod_price"));
			num = Integer.parseInt(request.getParameter("Pro_num"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		cart.setPro_num(num);
		cart.setProd_price(price);
		String message = this.accountsService.addCart(cart);
		RespondUtils.PrintString(response, message);
	}

	public void getCartInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String cartId = request.getParameter("Cart_ID");
		String message = this.accountsService.getCartInformation(cartId);
		RespondUtils.PrintString(response, message);
	}

	public void changeProductCount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String cartId = request.getParameter("Cart_ID");
		int num = 0;
		try {
			num = Integer.parseInt(request.getParameter("Pro_num"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String message = this.accountsService.changeProductCount(cartId, num);
		RespondUtils.PrintString(response, message);
	}

	public void deleteCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String cartId = request.getParameter("Cart_ID");
		String message = this.accountsService.deleteCart(cartId);
		RespondUtils.PrintString(response, message);
	}

	public void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		Orders order = new Orders();
		order.setAcc_ID(Acc_ID);
		order.setProd_ID(request.getParameter("Prod_ID"));
		float price = 0f;
		int num = 0;
		try {
			price = Float.parseFloat(request.getParameter("Prod_price"));
			num = Integer.parseInt(request.getParameter("Pro_num"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		order.setProd_price(price);
		order.setPro_num(num);
		order.setAdd_ID(request.getParameter("Add_ID"));
		String message = this.accountsService.addOrder(order);
		RespondUtils.PrintString(response, message);
	}

	public void getOrderInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String orderId = request.getParameter("Ord_ID");
		String message = this.accountsService.getOrderInformation(orderId);
		RespondUtils.PrintString(response, message);
	}

	public void deleteOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String orderId = request.getParameter("Ord_ID");
		String message = this.accountsService.deleteOrder(orderId);
		RespondUtils.PrintString(response, message);
	}

	public void changeOrderInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		Orders order = new Orders();
		// XXX Ord_ID 为必须量
		order.setOrd_ID(request.getParameter("Ord_ID"));
		order.setProd_ID(request.getParameter("Prod_ID"));
		float price = 0f;
		int num = 0;
		try {
			price = Float.parseFloat(request.getParameter("Prod_price"));
			num = Integer.parseInt(request.getParameter("Pro_num"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		order.setProd_price(price);
		order.setPro_num(num);
		order.setAdd_ID(request.getParameter("Add_ID"));
		String message = this.accountsService.changeOrderInformation(order);
		RespondUtils.PrintString(response, message);
	}

	public void addCommentProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		CommentProduct commentProduct = new CommentProduct();
		commentProduct.setAcc_ID(Acc_ID);
		commentProduct.setProd_ID(request.getParameter("Prod_ID"));
		commentProduct.setOrd_ID(request.getParameter("Ord_ID"));
		commentProduct.setComm_desc(request.getParameter("Comm_desc"));
		String message = this.accountsService.addCommentProduct(commentProduct);
		RespondUtils.PrintString(response, message);
	}

	public void changeCommentProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String commentId = request.getParameter("Comm_ID");
		String commentDescription = request.getParameter("Comm_desc");
		String message = this.accountsService.changeCommentProduct(commentId, commentDescription);
		RespondUtils.PrintString(response, message);
	}

	public void getCommentProductInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commentId = request.getParameter("Comm_ID");
		String message = this.accountsService.getCommentProductInformation(commentId);
		RespondUtils.PrintString(response, message);
	}

	public void saveAccHeadPhoto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = this.accountsService.saveAccountHeadPhoto(request);
		RespondUtils.PrintString(response, message);
	}

	public void saveAccPhotos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = this.accountsService.saveAccountPhotos(request);
		RespondUtils.PrintString(response, message);
	}

	public void changeAccHeadPhoto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = this.accountsService.changeAccountHeadPhoto(request);
		RespondUtils.PrintString(response, message);
	}

	public void changeAccPhotos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = this.accountsService.changeAccountPhotos(request);
		RespondUtils.PrintString(response, message);
	}

	public void getAccHeadPhotoId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = request.getParameter("Acc_ID");
		String message = this.accountsService.getAccountHeadPhotoId(Acc_ID);
		RespondUtils.PrintString(response, message);
	}

	public void getAccPhotosId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = request.getParameter("Acc_ID");
		String message = this.accountsService.getAccountPhotosId(Acc_ID);
		RespondUtils.PrintString(response, message);
	}

	public void getPhotoById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Photo_ID = request.getParameter("Photo_ID");
		String filePathName = this.accountsService.getPhotoById(Photo_ID);
		RespondUtils.printFile(response, filePathName);
	}

	public void getThumbnailPhotoById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Photo_ID = request.getParameter("Photo_ID");
		String filePathName = this.accountsService.getThumbnailPhotoById(Photo_ID);
		RespondUtils.printFile(response, filePathName);
	}
}