package com.xuyihao.service;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.xuyihao.tools.AppPropertiesLoader;
import com.xuyihao.tools.UploadUtil;

/**
 * created by xuyihao on 2016/5/11
 * 
 * @author xuyihao
 * @version 1.0
 * @description 尝试文件上传功能的servlet,需要通过jsp/html或者移动设备的表单格式发送post请求
 * @attention 通过getpart获取文件的方法需要servlet3.0以上支持
 * @attention 添加@MultipartConfig注解之后,multipart/form-data类型的表单也可以通过request的getparameter方法获取数据
 */

@MultipartConfig
public class FileUploadTestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String baseFileSavePath = null;

	static {
		Properties prop = AppPropertiesLoader.getAppProperties();
		baseFileSavePath = prop.getProperty("BaseFilePath");
	}

	/**
	 * method init
	 * 
	 * @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/*
	 * @method doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession();
		try {
			Part part0 = request.getPart("file0");
			part0.write(baseFileSavePath + UploadUtil.getFileName(part0));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Part part1 = request.getPart("file1");
			part1.write(baseFileSavePath + UploadUtil.getFileName(part1));
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.getWriter().write("OK!");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
