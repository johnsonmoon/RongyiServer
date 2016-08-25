package com.xuyihao.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Xuyh at 16-8-7 下午9:10
 */
public class AppPropertiesLoader {
	public static Properties getAppProperties() {
		try {
			Properties properties = new Properties();
			InputStream in = null;
			try {
				in = new FileInputStream(System.getProperty("user.dir") + "/AppConfig.properties");
			} catch (Exception e) {
				try {
					File file = new File(System.getProperty("user.dir"));
					file = file.getParentFile();
					in = new FileInputStream(file.getAbsolutePath() + "/webapps/rongyi/WEB-INF/config/AppConfig.properties");
				} catch (Exception e4) {
					try {
						in = AppPropertiesLoader.class.getResourceAsStream("/AppConfig.properties");
					} catch (Exception e2) {
					}
				}
			}
			properties.load(in);
			return properties;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
