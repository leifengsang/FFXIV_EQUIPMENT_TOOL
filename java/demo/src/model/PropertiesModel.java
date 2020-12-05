package model;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesModel {

	private String dbPath;

	private String resourcePath;

	private String exportPath;

	private static PropertiesModel instance;

	public static PropertiesModel getInstance() {
		if (instance == null) {
			instance = new PropertiesModel();
		}
		return instance;
	}

	public String getDbPath() {
		return dbPath;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public String getExportPath() {
		return exportPath;
	}

	public PropertiesModel() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("config.properties"));
			this.dbPath = properties.getProperty("dbPath");
			this.resourcePath = properties.getProperty("resourcePath");
			this.exportPath = properties.getProperty("exportPath");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
