package com.xuyihao.service;

import com.xuyihao.tools.AppPropertiesLoader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by xuyihao on 2016/5/13
 * 
 * @author johnson
 * @description 此工具类主要用来测试服务器收到的IP数据报文格式等
 * @attention 输出方式为网页输出加上文本文件输出文本格式（字符流）
 * @attention 添加@MultipartConfig注解之后,multipart/form-data类型的表单也可以通过request的getparameter方法获取数据
 */
@MultipartConfig
public class IPContentTestServlet extends HttpServlet {

	/**
	 * fields
	 */
	private static final long serialVersionUID = 1L;

	public static String baseFileSavePath = null;

	static {
		Properties prop = AppPropertiesLoader.getAppProperties();
		baseFileSavePath = prop.getProperty("BaseFilePath");
	}

	/**
	 * @throws ServletException
	 * @method init
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		baseFileSavePath = this.getServletContext().getInitParameter("BaseFilePath");
	}

	/**
	 * @method doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession();
		try {
			InputStream in = request.getInputStream();
			byte[] b = new byte[1024];
			String filePath = baseFileSavePath + "temp.txt";
			FileOutputStream fos = new FileOutputStream(filePath);
			int c;
			while ((c = in.read(b)) > 0) {
				fos.write(b, 0, c);
			}
			fos.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		response.getWriter().write("OK!");
	}

	/**
	 * @method doGet
	 */
	protected void doGet(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		this.doPost(request, response);
	}

}
