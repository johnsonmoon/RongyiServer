package xuyihao.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import xuyihao.common.ThreadLocalContext;
import xuyihao.entity.Category;
import xuyihao.entity.Products;
import xuyihao.entity.Shops;
import xuyihao.service.ShopsService;

/**
 * 
 * @Author Xuyh created at 2016年8月26日 下午1:17:35
 */
@MultipartConfig
public class ShopsServlet extends HttpServlet {

	/**
	 * <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 2129550310773048138L;

	@Autowired
	private ShopsService shopsService;

	private HttpSession session = null;

	public void setShopsService(ShopsService shopsService) {
		this.shopsService = shopsService;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext context = ThreadLocalContext.setContextAndRet(this.getServletContext());
		this.shopsService = (ShopsService) context.getBean("ShopsService");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置会话信息
		this.session = request.getSession();
		this.shopsService.setSessionInfo(session);
		String action = request.getParameter("action");
		if (action == null || action.equals("")) {
			return;
		}
		action = action.trim();
		switch (action) {
		case "addShop":
			this.addShop(request, response);
			break;
		case "shopNameExists":
			this.isShopNameExists(request, response);
			break;
		case "getShopIdByName":
			this.getShopIdByName(request, response);
			break;
		case "getShopInfo":
			this.getShopInformationById(request, response);
			break;
		case "changeShopInfo":
			this.changeShopInformation(request, response);
			break;
		case "deleteShop":
			this.deleteShop(request, response);
			break;
		case "addCat":
			this.addCategory(request, response);
			break;
		case "getCatInfo":
			this.getCategoryInformationById(request, response);
			break;
		case "addProd":
			this.addProduct(request, response);
			break;
		case "getProdInfo":
			this.getProductInformation(request, response);
			break;
		case "deleteProd":
			this.deleteProduct(request, response);
			break;
		}
	}

	public void addShop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		Shops shop = new Shops();
		shop.setAcc_ID(Acc_ID);
		shop.setShop_name(request.getParameter("Shop_name"));
		shop.setShop_info(request.getParameter("Shop_info"));
		// TODO 需要进一步设计，店铺运营执照信息是存图片还是文字，后期需要修正
		shop.setShop_licen(request.getParameter("Shop_licen"));
		String message = this.shopsService.addShop(shop);
		response.getWriter().println(message);
	}

	public void isShopNameExists(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String shopName = request.getParameter("Shop_name");
		String message = this.shopsService.isShopNameExists(shopName);
		response.getWriter().println(message);
	}

	public void getShopIdByName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String shopName = request.getParameter("Shop_name");
		String message = this.shopsService.getShopIdByName(shopName);
		response.getWriter().println(message);
	}

	public void getShopInformationById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String shopId = request.getParameter("Shop_ID");
		String message = this.shopsService.getShopInformationById(shopId);
		response.getWriter().println(message);
	}

	public void changeShopInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		Shops shop = new Shops();
		// XXX Shop_ID 为必需项
		shop.setShop_ID(request.getParameter("Shop_ID"));
		shop.setShop_info(request.getParameter("Shop_info"));
		// TODO 需要进一步设计，店铺运营执照信息是存图片还是文字，后期需要修正
		shop.setShop_licen(request.getParameter("Shop_licen"));
		String message = this.shopsService.changeShopInformation(shop);
		response.getWriter().println(message);
	}

	public void deleteShop(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String shopId = request.getParameter("Shop_ID");
		String message = this.shopsService.deleteShop(shopId);
		response.getWriter().println(message);
	}

	public void addCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		// TODO category 在设计上需要完善
		Category category = new Category();
		category.setCat_name(request.getParameter("Cat_name"));
		category.setCat_desc(request.getParameter("Cat_desc"));
		String message = this.shopsService.addCategory(category);
		response.getWriter().println(message);
	}

	public void getCategoryInformationById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryId = request.getParameter("Cat_ID");
		String message = this.shopsService.getCategoryInformationById(categoryId);
		response.getWriter().println(message);
	}

	public void addProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		Products product = new Products();
		product.setCat_ID(request.getParameter("Cat_ID"));
		product.setShop_ID(request.getParameter("Shop_ID"));
		product.setProd_name(request.getParameter("Prod_name"));
		product.setProd_desc(request.getParameter("Prod_desc"));
		// TODO 产品详情需要进一步详细设计
		product.setProd_info(request.getParameter("Prod_info"));
		float price = 0f;
		int num = 0;
		try {
			price = Float.parseFloat(request.getParameter("Prod_price"));
			num = Integer.parseInt(request.getParameter("Prod_num"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		product.setProd_price(price);
		product.setProd_num(num);
		String message = this.shopsService.addProduct(product);
		response.getWriter().println(message);
	}

	public void getProductInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("Prod_ID");
		String message = this.shopsService.getProductInformation(productId);
		response.getWriter().println(message);
	}

	public void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String productId = request.getParameter("Prod_ID");
		String message = this.shopsService.deleteProduct(productId);
		response.getWriter().println(message);
	}
}