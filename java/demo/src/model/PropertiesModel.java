package model;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesModel {

	private String dbPath;

	private String resourcePath;

	private String exportPath;

	private String levelFloor;

	private String levelCeil;

	private String width;

	private String height;

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

	public String getLevelFloor() {
		if (levelFloor == null) {
			return "500";
		}
		return levelFloor;
	}

	public String getLevelCeil() {
		if (levelCeil == null) {
			return "505";
		}
		return levelCeil;
	}

	public int getWidth() {
		try {
			return Integer.parseInt(width);
		} catch (Exception e) {
			return 1280;
		}
	}

	public int getHeight() {
		try {
			return Integer.parseInt(height);
		} catch (Exception e) {
			return 960;
		}
	}

	public PropertiesModel() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("config.properties"));
			this.dbPath = properties.getProperty("dbPath");
			this.resourcePath = properties.getProperty("resourcePath");
			this.exportPath = properties.getProperty("exportPath");
			this.levelFloor = properties.getProperty("levelFloor");
			this.levelCeil = properties.getProperty("levelCeil");
			this.width = properties.getProperty("width");
			this.height = properties.getProperty("height");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
