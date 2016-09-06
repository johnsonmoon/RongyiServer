package xuyihao.tools.utils;

import xuyihao.tools.MIME_FileType;

/**
 * 
 * @author Xuyh at 2016年9月4日 下午7:43:57.
 *
 */
public class FileTypeUtils {
	/**
	 * @author johnson
	 * @method getFileType
	 */
	public static MIME_FileType getFileType(String fileName) {
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
