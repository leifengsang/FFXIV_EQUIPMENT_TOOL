package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import meta.Attr;
import meta.Equipment;
import meta.Food;
import meta.Job;
import sql.SQLHelper;

public class Model {

	public static final String[] JOB_LIST = { "PLD", "WAR", "DRK", "GNB", "WHM", "SCH", "AST", "MNK", "DRG", "NIN",
			"SAM", "BRD", "MCH", "DNC", "BLM", "SMN", "RDM" };

	public static final int LANG_JP = 1;

	public static final int LANG_EN = 2;

	private final SQLHelper sqlHelper;

	private List<Equipment> equipmentList = new ArrayList<>();

	private Map<String, Equipment> equipmentMap = new HashMap<>();//Map<name,equipment>

	private List<Food> foodList = new ArrayList<>();

	private Map<String, Food> foodMap = new HashMap<>();

	private Map<String, Map<Integer, String>> nameMap = new HashMap<>();//Map<id,Map<language,name>>

	private Map<String, Equipment> equipmentIdMap = new HashMap<>();

	private Map<String, Food> foodIdMap = new HashMap<>();

	private int language = LANG_JP;

	private String resourcePath;

	private String exportPath = "../../export/";

	private List<Integer> criticalHitThreshold = new ArrayList<>();

	private List<Integer> directHitThreshold = new ArrayList<>();

	private List<Integer> determinationThreshold = new ArrayList<>();

	private List<Integer> fortitudeThreshold = new ArrayList<>();

	private List<Integer> speedThreshold = new ArrayList<>();

	private List<Integer> faithThreshold = new ArrayList<>();

	private static Model instance;

	public static Model getInstance() {
		if (instance == null) {
			instance = new Model();
		}
		return instance;
	}

	private Model() {
		loadProperties();
		sqlHelper = SQLHelper.getInstance();
		load();
	}

	private void loadProperties() {
		this.resourcePath = PropertiesModel.getInstance().getResourcePath();
		this.exportPath = PropertiesModel.getInstance().getExportPath();
	}

	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}

	public List<Food> getFoodList() {
		return foodList;
	}

	public int getLanguage() {
		return language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public String getExportPath() {
		return exportPath;
	}

	private void load() {
		loadEquipment();
		loadFood();
		loadTranslator();
		loadThreshold();
	}

	private void loadFood() {
		String sql = "select * from food";
		ResultSet ret = sqlHelper.execQuery(sql);
		try {
			while (ret.next()) {
				Food food = new Food();
				food.setId(SQLHelper.getStringValue(ret, "id"));
				food.setLevel(SQLHelper.getIntValue(ret, "level"));
				food.setAttrType1(SQLHelper.getStringValue(ret, "attrType1"));
				String attrStr = SQLHelper.getStringValue(ret, "attr1");
				if (!attrStr.equals("")) {
					String[] strs = attrStr.split(" ");
					if (strs.length == 1) {
						food.setValue1(Integer.parseInt(strs[0]));
						food.setLimit1(-1);
					} else if (strs.length == 2) {
						String rate = (String) strs[0].subSequence(0, strs[0].length() - 1);
						food.setValue1(Integer.parseInt(rate));
						food.setLimit1(Integer.parseInt(strs[1]));
					}
				}

				food.setAttrType2(SQLHelper.getStringValue(ret, "attrType2"));
				attrStr = SQLHelper.getStringValue(ret, "attr2");
				if (!attrStr.equals("")) {
					String[] strs = attrStr.split(" ");
					if (strs.length == 1) {
						food.setValue2(Integer.parseInt(strs[0]));
						food.setLimit2(-1);
					} else if (strs.length == 2) {
						String rate = (String) strs[0].subSequence(0, strs[0].length() - 1);
						food.setValue2(Integer.parseInt(rate));
						food.setLimit2(Integer.parseInt(strs[1]));
					}
				}

				foodList.add(food);
				foodIdMap.put(food.getId(), food);
			}
		} catch (Exception e) {
			System.out.println("从数据库加载食物数据失败！ type:" + e.getClass().getName() + ", error:" + e.getMessage());
		}

		foodList.sort(Comparator.comparing(Food::getLevel).reversed());
	}

	@SuppressWarnings("resource")
	private void loadThreshold() {
		try {
			InputStream is = new FileInputStream(new File(resourcePath + "threshold.xlsx"));
			Workbook excel = new XSSFWorkbook(is);
			for (int sheetNum = 0; sheetNum < excel.getNumberOfSheets(); sheetNum++) {
				Sheet sheet = excel.getSheetAt(sheetNum);
				List<Integer> list = getThresholdListByType(sheet.getSheetName());
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum);
					Cell cell = row.getCell(0);
					list.add((int) cell.getNumericCellValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void loadEquipment() {
		String sql = "select * from equipment";
		ResultSet ret = sqlHelper.execQuery(sql);
		try {
			while (ret.next()) {
				Equipment equipment = new Equipment();
				equipment.setId(SQLHelper.getStringValue(ret, "id"));
				equipment.setPosition(SQLHelper.getIntValue(ret, "position"));
				equipment.setType(SQLHelper.getIntValue(ret, "type"));
				equipment.setLevel(SQLHelper.getIntValue(ret, "level"));
				List<Class<? extends Job>> enableJobList = new ArrayList<>();
				String enableJobString = SQLHelper.getStringValue(ret, "enableJobList");
				if (enableJobString.equals("ALL")) {
					for (String job : JOB_LIST) {
						Class<?> clazz = Class.forName("meta." + job);
						enableJobList.add((Class<? extends Job>) clazz);
					}
				} else {
					String[] jobs = enableJobString.split("#");
					for (String job : jobs) {
						if (job.equals("")) {
							continue;
						}
						Class<?> clazz = Class.forName("meta." + job);
						enableJobList.add((Class<? extends Job>) clazz);
					}
				}
				equipment.setEnableJobList(enableJobList);
				Attr attr = new Attr();
				attr.setCriticalHit(SQLHelper.getIntValue(ret, "criticalHit"));
				attr.setDirectHit(SQLHelper.getIntValue(ret, "directHit"));
				attr.setDetermination(SQLHelper.getIntValue(ret, "determination"));
				attr.setFaith(SQLHelper.getIntValue(ret, "faith"));
				attr.setFortitude(SQLHelper.getIntValue(ret, "fortitude"));
				attr.setSkillSpeed(SQLHelper.getIntValue(ret, "skillSpeed"));
				attr.setSpellSpeed(SQLHelper.getIntValue(ret, "spellSpeed"));
				attr.setVIT(SQLHelper.getIntValue(ret, "VIT"));
				equipment.setAttr(attr);
				equipment.setNormalSocket(SQLHelper.getIntValue(ret, "normalSocket"));
				equipment.setMoreSocket(SQLHelper.getBooleanValue(ret, "moreSocket"));

				equipmentList.add(equipment);
				equipmentIdMap.put(equipment.getId(), equipment);
			}
		} catch (Exception e) {
			System.out.println("从数据库加载装备数据失败！ type:" + e.getClass().getName() + ", error:" + e.getMessage());
		}
	}

	private void loadTranslator() {
		String sql = "select * from translator";
		ResultSet ret = sqlHelper.execQuery(sql);
		try {
			while (ret.next()) {
				String id = SQLHelper.getStringValue(ret, "id");
				String name;
				name = SQLHelper.getStringValue(ret, "japanese");
				addTranslator(id, name, LANG_JP);
				name = SQLHelper.getStringValue(ret, "english");
				addTranslator(id, name, LANG_EN);
			}
		} catch (Exception e) {
			System.out.println("从数据库加载翻译失败！ type:" + e.getClass().getName() + ", error:" + e.getMessage());
		}
	}

	private void addTranslator(String id, String name, int lang) {
		Map<Integer, String> translatorMap = nameMap.get(id);
		if (translatorMap == null) {
			translatorMap = new HashMap<>();
			nameMap.put(id, translatorMap);
		}
		translatorMap.put(lang, name);

		Equipment equipment = equipmentIdMap.get(id);
		if (equipment != null) {
			String key = name + "#" + equipment.getPosition();
			equipmentMap.put(key, equipment);
			if (equipment.getPosition() == Equipment.POS_RING1) {
				equipment = equipmentIdMap.get(equipment.getId() + "#copy");
				key = name + "#" + equipment.getPosition();
				equipmentMap.put(key, equipment);
			}
			return;
		}

		Food food = foodIdMap.get(id);
		if (food != null) {
			foodMap.put(name, food);
			return;
		}
	}

	public Equipment getEquipmentByKey(String key) {
		return equipmentMap.get(key);
	}

	public Food getFoodByName(String name) {
		return foodMap.get(name);
	}

	public String getNameById(String id) {
		return nameMap.get(id).get(language);
	}

	public List<Equipment> getEquipmentByLevelAndJob(Class<? extends Job> clazz, int floor, int ceil) {
		List<Equipment> list = new ArrayList<>();
		for (Equipment equipment : equipmentList) {
			if (equipment.getPosition() == -1) {
				continue;
			}

			if (!equipment.isBattle()) {
				continue;
			}

			if (!equipment.getEnableJobList().contains(clazz)) {
				continue;
			}
			int level = equipment.getLevel();
			if (!(level >= floor && level <= ceil)) {
				continue;
			}
			equipment.getMateriaMap().clear();
			list.add(equipment);
		}
		return list;
	}

	public int getCurrentThreshold(int target, String type) {
		List<Integer> list = getThresholdListByType(type);
		int ret = -1;
		for (int value : list) {
			if (target >= value) {
				ret = value;
			} else {
				break;
			}
		}
		return ret;
	}

	public int getNextThreshold(int target, String type) {
		List<Integer> list = getThresholdListByType(type);
		for (int value : list) {
			if (target < value) {
				return value;
			}
		}
		return -1;
	}

	private List<Integer> getThresholdListByType(String type) {
		switch (type) {
		case "criticalHit":
			return criticalHitThreshold;
		case "directHit":
			return directHitThreshold;
		case "determination":
			return determinationThreshold;
		case "fortitude":
			return fortitudeThreshold;
		case "speed":
			return speedThreshold;
		case "faith":
			return faithThreshold;
		default:
			throw new Error("attr type error");
		}
	}

}
