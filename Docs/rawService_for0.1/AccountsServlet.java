package com.xuyihao.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xuyihao.dao.AccountsDao;
import com.xuyihao.tools.RespondTextTool;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * created by xuyihao 2016/4/2
 * */
@MultipartConfig
public class AccountsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * fields
	 */
	@Autowired
	private AccountsDao accountsDao;

	/**
	 * method init
	 * 
	 * @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/*
	 * method doGet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/*
	 * method doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession();
		String action = request.getParameter("action");// get the detailed request
																										// action
		if ("login".equals(action)) {// do login request
			this.login(request, response);
		} else if ("regist".equals(action)) {// do regist request
			this.regist(response, request);
		} else if ("ifNameExists".equals(action)) {
			this.ifNameExists(response, request);
		} else if ("checkD".equals(action)) {
			this.CheckDatabaseEncoding(response, request);
		} else if ("checkW".equals(action)) {
			this.CheckWebEncoding(request, response);
		} else if ("completeInfo".equals(action)) {
			this.completeInfo(request, response);
		} else if ("logout".equals(action)) {
			this.logout(response, request);
		} else if ("getInfoMyself".equals(action)) {
			this.getAccountInfoMyself(response, request);
		} else if ("getInfoByOthers".equals(action)) {
			this.getAccountInfoByOthers(request, response);
		} else if ("attend".equals(action)) {
			this.Attention(response, request);
		} else if ("cancelAttend".equals(action)) {
			this.CancelAttention(request, response);
		} else if ("favourite".equals(action)) {
			this.Favourite(response, request);
		} else if ("cancelFavourite".equals(action)) {
			this.CancelFavourite(response, request);
		} else if ("want".equals(action)) {
			this.want(response, request);
		} else if ("cancelWant".equals(action)) {
			this.cancelWant(response, request);
		} else {
		}
	}

	/*
	 * method to check whether the encoding for mysql database is correct or not
	 */
	private void CheckDatabaseEncoding(HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {
		// accountsDao.TestDatabaseEncoding();
	}

	/*
	 * method to check the web encoding of Chinese
	 */
	private void CheckWebEncoding(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><head>");
		out.println("<title>测试结果</title>");
		out.println("</head>");
		out.println("<body>");
		out.println(request.getParameter("A0"));
		out.println(request.getParameter("A1"));
		out.println(request.getParameter("A2"));
		out.println("</body></html>");
	}

	/*
	 * @method check whether the account name is already exists
	 * 
	 * @attention 用来查看用户名是否已经存在
	 */
	private void ifNameExists(HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {
		Accounts acc = new Accounts();
		acc.Set_Acc_name(request.getParameter("Acc_name")); 
		boolean exist = accountsDao.AccountNameExist(acc);
		
		JSONObject jsonObject = new JSONObject(); 
		if(exist){
			jsonObject.put("exist", "yes"); 
		}else{ 
			jsonObject.put("exist", "no"); 
		}
		RespondTextTool.PrintJSON(response, jsonObject);
	}

	/*
	 * @method login
	 * 
	 * @attention 用户登录
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Acc_name = request.getParameter("Acc_name"); 
		String Acc_pwd = request.getParameter("Acc_pwd"); 
		Accounts acc = new Accounts();
		acc.Set_Acc_name(Acc_name); 
		acc.Set_Acc_pwd(Acc_pwd);
		
		JSONObject jsonObject = new JSONObject();
		
		if(accountsDao.AccountNameExist(acc)){//if the account exists
			jsonObject.put("exist", "yes"); 
			long result = accountsDao.Login(acc);
			if(result == 0){//error come up, login failed
				jsonObject.put("loginResult", "no"); 
			}else{//login successfully
				jsonObject.put("loginResult", "yes");
		
				//login successfully and save the account session.
				request.getSession().setAttribute("Acc_ID", String.valueOf(result));
				request.getSession().setAttribute("Acc_name", Acc_name); 
			}
		}else{//account do not exist jsonObject.put("exist", "no");
			jsonObject.put("loginResult", "no"); 
		}
		RespondTextTool.PrintJSON(response, jsonObject);
	}

	/*
	 * @method logout
	 * 
	 * @attention 用户登出
	 */
	private void logout(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("logoutResult", "Yes");

		RespondTextTool.PrintJSON(response, jsonObject);
	}

	/*
	 * @method regist
	 * 
	 * @attention 用户注册
	 */
	private void regist(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		
		Accounts acc = new Accounts();
		acc.Set_Acc_name(request.getParameter("Acc_name"));
		acc.Set_Acc_pwd(request.getParameter("Acc_pwd"));
		acc.Set_Acc_sex(request.getParameter("Acc_sex"));
		acc.Set_Acc_loc(request.getParameter("Acc_loc"));
		
		long result = accountsDao.Regist(acc); 
		JSONObject jsonObject = new JSONObject(); 
		if(result == 0){// error come up while registing
			jsonObject.put("registResult", "no"); 
			jsonObject.put("loginResult", "no"); 
		}else{//regist successfully jsonObject.put("registResult", "yes");
			jsonObject.put("loginResult", "yes");
		
			//Done register means login successfully, so we save the session of the	account. 
			request.getSession().setAttribute("Acc_ID", String.valueOf(result)); 
			request.getSession().setAttribute("Acc_name", acc.Get_Acc_name());
		} 
		RespondTextTool.PrintJSON(response, jsonObject);
		
	}

	/*
	 * @Method that complete the identify of the account.
	 * 
	 * @attention 用户完善自己资料信息
	 */
	private void completeInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(); 
		Accounts acc = new Accounts();
		acc.Set_Acc_name(String.valueOf(session.getAttribute("Acc_name")));
		acc.Set_Acc_name2(request.getParameter("Acc_name2"));
		acc.Set_Acc_no(request.getParameter("Acc_no"));
		acc.Set_Acc_tel(request.getParameter("Acc_tel"));
		
		long result = accountsDao.completeInfo(acc); 
		JSONObject jsonObject = new JSONObject(); 
		if(result == Long.parseLong(String.valueOf(session.getAttribute("Acc_ID")))){
			jsonObject.put("complete", "yes"); }else{ jsonObject.put("complete", "no"); 
		}
		RespondTextTool.PrintJSON(response, jsonObject);
		
	}

	/*
	 * @method getting account information (by account itself)
	 * 
	 * @attention 用户获取自己的信息
	 */
	private void getAccountInfoMyself(HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {
		String name = request.getSession().getAttribute("Acc_name").toString();
		Accounts acc = accountsDao.getAccountsInformation(name); 
		JSONObject jsonObject = new JSONObject(); 
		jsonObject.put("Acc_ID", acc.Get_Acc_ID()); 
		jsonObject.put("Acc_name", acc.Get_Acc_name());
		jsonObject.put("Acc_sex", acc.Get_Acc_sex()); 
		jsonObject.put("Acc_loc", acc.Get_Acc_loc()); 
		jsonObject.put("Acc_lvl", acc.Get_Acc_lvl());
		jsonObject.put("Acc_ryb", acc.Get_Acc_ryb()); 
		jsonObject.put("Acc_shop", acc.Get_Acc_shop()); 
		jsonObject.put("Acc_atn", acc.Get_Acc_atn());
		jsonObject.put("Acc_atnd", acc.Get_Acc_atnd()); 
		jsonObject.put("Acc_pub", acc.Get_Acc_pub()); 
		jsonObject.put("Acc_no", acc.Get_Acc_no());
		jsonObject.put("Acc_name2", acc.Get_Acc_name2());
		jsonObject.put("Acc_tel", acc.Get_Acc_tel());
		RespondTextTool.PrintJSON(response, jsonObject);
	}

	/*
	 * @method getting account information (by other accounts)
	 * 
	 * @attention 其他用户获取某一用户信息
	 */
	private void getAccountInfoByOthers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("Acc_name"); 
		Accounts acc = accountsDao.getAccountsInformation(name); 
		JSONObject jsonObject = new JSONObject(); 
		jsonObject.put("Acc_name", acc.Get_Acc_name());
		jsonObject.put("Acc_sex", acc.Get_Acc_sex()); 
		jsonObject.put("Acc_loc", acc.Get_Acc_loc()); 
		jsonObject.put("Acc_lvl", acc.Get_Acc_lvl());
		jsonObject.put("Acc_atn", acc.Get_Acc_atn()); 
		jsonObject.put("Acc_atnd", acc.Get_Acc_atnd()); 
		jsonObject.put("Acc_pub", acc.Get_Acc_pub());
		RespondTextTool.PrintJSON(response, jsonObject);
	}

	/*
	 * @method attention (for one account attending another)
	 * 
	 * @attention 用户A关注用户B
	 */
	private void Attention(HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {
		
		long ID1 = Long.parseLong(request.getSession().getAttribute("Acc_ID").toString());
		long ID2 = accountsDao.getAccountID(request.getParameter("Acc_name"));
		JSONObject jsonObject = new JSONObject(); if(accountsDao.Attention(ID1, ID2)){//attend successfully jsonObject.put("attendResult", "yes"); }else{
		jsonObject.put("attendResult", "no"); }
		RespondTextTool.PrintJSON(response, jsonObject);
		
	}

	/*
	 * @method Cancel Attend (Cancel who attended before)
	 * 
	 * @attention 用户A取消关注用户B
	 */
	private void CancelAttention(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		long ID1 = Long.parseLong(request.getSession().getAttribute("Acc_ID").toString());
		long ID2 = accountsDao.getAccountID(request.getParameter("Acc_name"));
		JSONObject jsonObject = new JSONObject();
		if(accountsDao.CancelAtention(ID1, ID2)){//Cancel succesfully
			jsonObject.put("cancelAttendResult", "yes"); 
		}else{
			jsonObject.put("cancelAttendResult", "no"); 
		}
		RespondTextTool.PrintJSON(response, jsonObject);
		
	}

	/*
	 * @method account favourite shop
	 * 
	 * @attention 用户收藏店铺
	 */
	private void Favourite(HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {
	
		long Acc_ID = Long.parseLong(request.getSession().getAttribute("Acc_ID").toString());
		Shops shop = new Shops();
		shop.setShop_ID(Integer.parseInt(request.getParameter("Shop_ID")));
		JSONObject jsonObject = new JSONObject();
		if(accountsDao.favourite(Acc_ID, shop)){
			jsonObject.put("favouriteResult", "yes"); 
		}else{
			jsonObject.put("favouriteResult", "no"); 
		}
		RespondTextTool.PrintJSON(response, jsonObject);
		
	}

	/*
	 * @method account cancel favourite shop
	 * 
	 * @attention 用户取消收藏店铺
	 */
	public void CancelFavourite(HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {
		
		long Acc_ID = Long.parseLong(request.getSession().getAttribute("Acc_ID").toString());
		Shops shop = new Shops();
		shop.setShop_ID(Integer.parseInt(request.getParameter("Shop_ID")));
		JSONObject jsonObject = new JSONObject();
		if(accountsDao.cancelFavourite(Acc_ID, shop)){
			jsonObject.put("cancelFavouriteResult", "yes"); 
		}else{
			jsonObject.put("cancelFavouriteResult", "no"); 
		}
		RespondTextTool.PrintJSON(response, jsonObject);
		
	}

	/*
	 * @method account want product
	 * 
	 * @attention 用户收藏产品
	 */
	public void want(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		
		long Acc_ID = Long.parseLong(request.getSession().getAttribute("Acc_ID").toString());
		Products prod = new Products();
		prod.setProd_ID(Long.parseLong(request.getParameter("Prod_ID")));
		JSONObject jsonObject = new JSONObject(); 
		if(accountsDao.want(Acc_ID,	prod)){ 
			jsonObject.put("wantResult", "yes"); 
		}else{
			jsonObject.put("wantResult", "no"); 
		} 
		RespondTextTool.PrintJSON(response, jsonObject);
		
	}

	/*
	 * @method account cancel want product
	 * 
	 * @attention 用户取消收藏产品
	 */
	public void cancelWant(HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {
		
		long Acc_ID = Long.parseLong(request.getSession().getAttribute("Acc_ID").toString());
		Products prod = new Products();
		prod.setProd_ID(Long.parseLong(request.getParameter("Prod_ID")));
		JSONObject jsonObject = new JSONObject();
		if(accountsDao.cancelWant(Acc_ID, prod)){
			jsonObject.put("cancelWantResult", "yes"); 
		}else{
			jsonObject.put("cancelWantResult", "no"); 
		}
		RespondTextTool.PrintJSON(response, jsonObject);
		
	}

}
