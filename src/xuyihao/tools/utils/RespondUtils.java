package xuyihao.tools.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

/**
 * 服务器返回消息工具
 * 
 * @Author Xuyh created at 2016年9月21日 上午11:18:12
 */
public class RespondUtils {
	/**
	 * 返回JSON格式字串
	 * 
	 * @param response
	 * @param jsonObject
	 * @throws IOException
	 */
	public static void PrintJSON(HttpServletResponse response, JSONObject jsonObject) {
		try {
			response.getWriter().println(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回文本消息(字串)
	 * 
	 * @param response
	 * @param msg
	 * @throws IOException
	 */
	public static void PrintString(HttpServletResponse response, String message) {
		try {
			response.getWriter().println(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 服务器返回文件
	 * 
	 * @param response
	 * @param filePathName
	 * @return
	 */
	public static boolean printFile(HttpServletResponse response, String filePathName) {
		boolean flag = true;
		String fileType = FileUtils.getFileMIMEType(filePathName).getValue();
		File file = new File(filePathName);
		String name = file.getName();
		// 设置响应头
		response.addHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
		long fileLength = file.length();
		response.addHeader("Content-Length", String.valueOf(fileLength));
		response.setContentType(fileType);
		response.setCharacterEncoding("UTF-8");
		FileInputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(file);
			out = response.getOutputStream();
			int length = 0;
			byte[] b = new byte[1024];
			while ((length = in.read(b)) != -1) {
				out.write(b, 0, length);
			}
		} catch (IOException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			try {
				in.close();
				out.flush();
				out.close();
				flag = true;
			} catch (IOException e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
}