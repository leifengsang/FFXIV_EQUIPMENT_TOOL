package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import meta.Attr;
import meta.Equipment;
import meta.Job;
import sql.SQLHelper;

public class EquipmentModel {

	public static final String[] Job_LIST = { "PLD", "WAR", "DRK", "GNB", "WHM", "SCH", "AST", "MNK", "DRG", "NIN",
			"SAM", "BRD", "MCH", "DNC", "BLM", "SMN", "RDM" };

	public static final int LANG_JP = 1;

	public static final int LANG_EN = 2;

	private final SQLHelper sqlHelper;

	private List<Equipment> equipmentList = new ArrayList<>();

	private Map<String, Equipment> equipmentMap = new HashMap<>();//Map<name,equipment>

	private Map<String, Map<Integer, String>> nameMap = new HashMap<>();//Map<id,Map<language,name>>

	private Map<String, Equipment> idMap = new HashMap<>();

	private int language = LANG_JP;

	private static EquipmentModel instance;

	public static EquipmentModel getInstance() {
		if (instance == null) {
			instance = new EquipmentModel();
		}
		return instance;
	}

	private EquipmentModel() {
		sqlHelper = SQLHelper.getInstance();
		load();
	}

	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}

	public int getLanguage() {
		return language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	private void load() {
		loadEquipment();
		loadTranslator();
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
					for (String job : Job_LIST) {
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
				idMap.put(equipment.getId(), equipment);
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
		}
		nameMap.put(id, translatorMap);
		translatorMap.put(lang, name);
		equipmentMap.put(name, idMap.get(id));
	}

	public Equipment getEquipmentByName(String name) {
		return equipmentMap.get(name);
	}

	public String getEquipmentNameById(String id) {
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
			equipment.getMateriaList().clear();
			list.add(equipment);
		}
		return list;
	}

}
