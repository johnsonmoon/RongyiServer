package xuyihao.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * modified by xuyihao on 2016/4/3
 * 
 * @description: 用来解决post，get请求乱码问题
 */
/**
 * 用来解决post，get请求乱码问题
 * 
 * @Author Xuyh created on 2016/4/3
 *
 */
public class CharacterEncodingFilter implements Filter {
	// 定义编码格式变量
	protected String encoding = null;
	// 定义过滤器配置对象
	protected FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		// 初始化过滤器配置对象
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
	}

	/**
	 * 实现执行过滤方法
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (encoding != null) {
			// 设置请求的编码
			req.setCharacterEncoding(encoding);
			// 设置应答对象的内容类型（包括编码格式）
			res.setCharacterEncoding(encoding);
			res.setContentType("text/html; charset=" + encoding);
		}
		MyCharacterEncodingRequest requestWrapper = new MyCharacterEncodingRequest(req);
		// 传递给下一个过滤器
		chain.doFilter(requestWrapper, res);
	}

	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

}
