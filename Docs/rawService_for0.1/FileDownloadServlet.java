package com.xuyihao.service;

import java.io.*;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuyihao.tools.AppPropertiesLoader;
import com.xuyihao.tools.MIME_FileType;

public class FileDownloadServlet extends HttpServlet {

	/**
	 * fields
	 */
	private static final long serialVersionUID = 1L;

	private static String baseFileSavePath = null;

	static {
		Properties prop = AppPropertiesLoader.getAppProperties();
		baseFileSavePath = prop.getProperty("BaseFilePath");
	}

	/**
	 * @author johnson
	 * @method init
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * @author johnson
	 * @method doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession();
		String fileExactName = request.getParameter("FilePathName");
		String fileType = request.getContentType();
		if (fileType == null) {
			fileType = this.getFileType(fileExactName).getValue();
		}
		// 获取文件
		File file = new File(baseFileSavePath + fileExactName);
		String name = file.getName();
		// 设置请求头
		response.addHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
		long l = file.length();
		response.addHeader("Content-Length", String.valueOf(l));
		response.setContentType(fileType);
		response.setCharacterEncoding("UTF-8");
		FileInputStream in = new FileInputStream(file);
		// 获取输出流
		OutputStream out = response.getOutputStream();
		// 读取文件流
		int length = 0;
		byte[] b = new byte[1024];
		while ((length = in.read(b)) != -1) {
			out.write(b, 0, length);
		}
		in.close();
		out.flush();
		out.close();
	}

	/**
	 * @author johnson
	 * @method doGet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @author johnson
	 * @method getFileType
	 */
	private MIME_FileType getFileType(String fileName) {
		int begin = fileName.lastIndexOf(".") + 1;
		String name = fileName.substring(begin);
		if (name.equals("txt")) {
			return MIME_FileType.Text_txt;
		} else if (name.equals("mp3")) {
			return MIME_FileType.Audio_mp3;
		} else if (name.equals("jpg")) {
			return MIME_FileType.Image_jpg;
		} else if (name.equals("png")) {
			return MIME_FileType.Image_png;
		} else if (name.equals("jpeg")) {
			return MIME_FileType.Image_jpeg;
		} else if (name.equals("gif")) {
			return MIME_FileType.Image_gif;
		} else if (name.equals("mp4")) {
			return MIME_FileType.Video_mp4;
		} else if (name.equals("rmvb")) {
			return MIME_FileType.Audio_rmvb;
		} else if (name.equals("avi")) {
			return MIME_FileType.Video_avi;
		} else {
			return MIME_FileType.Application_bin;
		}
	}
}
