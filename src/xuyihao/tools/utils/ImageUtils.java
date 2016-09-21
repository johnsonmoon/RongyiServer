package xuyihao.tools.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 图片工具
 *
 * Created by Xuyh at 2016/9/4 13:30.
 */
public class ImageUtils {
	/**
	 * 按指定高度 等比例缩放图片(生成缩略图)
	 *
	 * @param imageFile
	 * @param newPath
	 * @param newWidth
	 * @return
	 * @throws IOException
	 */
	public static boolean zoomImageScale(String imageFilePathName, String newPath, int newWidth) throws IOException {
		File imageFile = new File(imageFilePathName);
		if (!imageFile.canRead()) {
			return false;
		}
		BufferedImage bufferedImage = ImageIO.read(imageFile);
		if (bufferedImage == null) {
			return false;
		}
		int originalWidth = bufferedImage.getWidth();
		int originalHeight = bufferedImage.getHeight();
		double scale = (double) originalWidth / (double) newWidth;// 缩放的比例
		int newHeight = (int) (originalHeight / scale);
		zoomImage(imageFile, newPath, bufferedImage, newWidth, newHeight);
		return true;
	}

	/**
	 * 按尺寸缩放图片(生成缩略图)
	 *
	 * @param imageFile
	 * @param newPath
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException
	 */
	public static boolean zoomImage(String imageFilePathName, String newPath, int width, int height) throws IOException {
		File imageFile = new File(imageFilePathName);
		if (imageFile != null && !imageFile.canRead()) {
			return false;
		}
		BufferedImage bufferedImage = ImageIO.read(imageFile);
		if (null == bufferedImage) {
			return false;
		}
		zoomImage(imageFile, newPath, bufferedImage, width, height);
		return true;
	}

	private static void zoomImage(File imageFile, String newPath, BufferedImage bufferedImage, int width, int height)
			throws IOException {
		String fileName = imageFile.getName();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (suffix != null
				&& (suffix.trim().toLowerCase().endsWith("png") || suffix.trim().toLowerCase().endsWith("gif"))) {
			BufferedImage to = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = to.createGraphics();
			to = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
			g2d.dispose();
			g2d = to.createGraphics();
			Image from = bufferedImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
			g2d.drawImage(from, 0, 0, null);
			g2d.dispose();
			ImageIO.write(to, suffix, new File(newPath));
		} else {
			BufferedImage newImage = new BufferedImage(width, height, bufferedImage.getType());
			Graphics g = newImage.getGraphics();
			g.drawImage(bufferedImage, 0, 0, width, height, null);
			g.dispose();
			ImageIO.write(newImage, suffix, new File(newPath));
		}
	}
}