package xuyihao.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import xuyihao.common.AppPropertiesLoader;

import java.util.Properties;

/**
 * 重写HttpServletRequest的部分方法实现post，get请求中文乱码问题
 * 
 * @Author Xuyh created on 2016/4/3
 *
 */
public class MyCharacterEncodingRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;
	private static boolean changeOrNot = false;

	static {
		Properties properties = AppPropertiesLoader.getAppProperties();
		changeOrNot = Boolean.parseBoolean(properties.getProperty("ChangeOrNot"));
	}

	public MyCharacterEncodingRequest(HttpServletRequest req) {
		super(req);
		this.request = req;
	}

	/**
	 * 重写需要增强的getParameter方法
	 */
	@Override
	public String getParameter(String name) {
		try {
			String value = this.request.getParameter(name);
			if (value == null) {
				return null;
			}

			if (!this.request.getMethod().equalsIgnoreCase("get")) {// 不是get方法请求
				return value;
			} else {// 是get方法请求
				if (MyCharacterEncodingRequest.changeOrNot) {
					value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
				}
				return value;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
