package xuyihao.tools.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 视频操作类(使用ffmpeg工具)
 *
 * Created by Xuyh at 2016/09/20 上午 10:30.
 */
public class VedioUtils {
	/**
	 * 转换视频，通过目标视频后缀名更换视频格式
	 *
	 * @param ffmpegCommandPathName ffmpeg可执行文件的路径名(全)
	 * @param vedioPathName 源视频路径名(全)
	 * @param vedioDestinationPathName 生成目标视频路径名(全)
	 * @return true成功, false失败
	 */
	public static boolean convertVedio(String ffmpegCommandPathName, String vedioPathName,
			String vedioDestinationPathName) {
		File file = new File(vedioPathName);
		if (!file.exists()) {
			return false;
		}
		List<String> commands = new ArrayList<String>();
		commands.add(ffmpegCommandPathName);
		commands.add("-i");
		commands.add(vedioPathName);
		commands.add("-ac");
		commands.add("2");
		commands.add("-r");
		commands.add("29.97");
		commands.add(vedioDestinationPathName);
		return executeCommand(commands);
	}

	/**
	 * 对视频截图
	 *
	 * @param ffmpegCommandPathName ffmpeg可执行文件的路径名(全)
	 * @param vedioPathName 源视频路径名(全)
	 * @param imageDestinationPathName 生成目标图片路径名(全)
	 * @param cutTime 截取视频的时间点
	 * @return true成功, false失败
	 */
	public static boolean cutoutVedio(String ffmpegCommandPathName, String vedioPathName, String imageDestinationPathName,
			float cutTime) {
		File file = new File(vedioPathName);
		if (!file.exists()) {
			return false;
		}
		List<String> commands = new ArrayList<String>();
		commands.add(ffmpegCommandPathName);
		commands.add("-i");
		commands.add(vedioPathName);
		commands.add("-y");
		commands.add("-f");
		commands.add("image2");
		commands.add("-ss");
		commands.add(String.valueOf(cutTime));
		commands.add(imageDestinationPathName);
		return executeCommand(commands);
	}

	private static boolean executeCommand(List<String> commands) {
		ProcessBuilder builder = new ProcessBuilder();
		builder.command(commands);
		try {
			builder.start();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
