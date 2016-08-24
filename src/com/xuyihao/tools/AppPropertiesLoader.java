package com.xuyihao.tools;

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
			//InputStream in = new FileInputStream(System.getProperty("user.dir") + "/WebContent/WEB-INF/config/AppConfig.properties");
			InputStream in = new FileInputStream(System.getProperty("user.dir") + File.separator +  "AppConfig.properties");
			//InputStream in = AppPropertiesLoader.class.getResourceAsStream("/AppConfig.properties");
			properties.load(in);
			return properties;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
