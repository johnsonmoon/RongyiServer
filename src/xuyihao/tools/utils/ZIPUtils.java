package xuyihao.tools.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Xuyh at 2016/08/03 上午 11:24.
 */
public class ZIPUtils {
	/**
	 * 通过传入的文件名、文件路径名生成相应的zip压缩包
	 *
	 * @param zipFilePathName zip压缩包的文件路径名
	 * @param containedFilePathName 包含文件的文件路径名
	 * @param containedFileName 包含文件的文件名
	 * @return true if succeeded, false if failed
	 */
	public static boolean generateZipPackage(String zipFilePathName, List<String> containedFilePathName,
			List<String> containedFileName) {
		boolean flag = false;
		try {
			byte[] bytes = new byte[1024];
			ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFilePathName));
			for (int i = 0; i < containedFilePathName.size(); i++) {
				FileInputStream fileInputStream = new FileInputStream(containedFilePathName.get(i));
				zipOutputStream.putNextEntry(new ZipEntry(containedFileName.get(i)));
				;
				int length = 0;
				while ((length = fileInputStream.read(bytes)) != -1) {
					zipOutputStream.write(bytes, 0, length);
				}
				zipOutputStream.closeEntry();
				fileInputStream.close();
			}
			zipOutputStream.close();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 将目录下的所有文件压缩到zip文件下
	 *
	 * @param filePath 需要压缩的目录
	 * @param zipFilePathName 压缩文件路径名
	 * @return
	 */
	public static boolean generateDirectoryFilesToZipPackage(String filePath, String zipFilePathName) {
		boolean flag = false;
		File sourceFile = new File(filePath);
		if (!sourceFile.exists()) {
			System.out.println("\"" + filePath + "\"" + "Does not exists!");
			flag = false;
		} else {
			try {
				File zipFile = new File(zipFilePathName);
				if (zipFile.exists()) {
					System.out.println(zipFilePathName + " already exists!");
				} else {
					File[] containedFileArray = sourceFile.listFiles();
					if (containedFileArray == null || containedFileArray.length < 1) {
						System.out.println("No files exist in directory " + filePath);
					} else {
						byte[] bytes = new byte[1024];
						ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFilePathName));
						for (int i = 0; i < containedFileArray.length; i++) {
							FileInputStream fileInputStream = new FileInputStream(containedFileArray[i]);
							zipOutputStream.putNextEntry(new ZipEntry(containedFileArray[i].getName()));
							int length = 0;
							while ((length = fileInputStream.read(bytes)) != -1) {
								zipOutputStream.write(bytes, 0, length);
							}
							zipOutputStream.closeEntry();
							fileInputStream.close();
						}
						zipOutputStream.close();
						flag = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
}
