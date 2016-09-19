package xuyihao.tools.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.Part;

/**
 * 文件操作类
 * 
 * @author Xuyh at 2016年9月19日 下午10:22:55.
 *
 */
public class FileUtils {
	public static void writePartToDisk(Part part, String pathName) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = part.getInputStream();
			out = new FileOutputStream(pathName);
			byte[] b = new byte[1024];
			int length = 0;
			while ((length = in.read(b)) != -1) {
				out.write(b, 0, length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}