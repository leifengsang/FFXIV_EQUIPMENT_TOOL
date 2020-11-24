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

public class EquipmemtModel {

	public static final String[] Job_LIST = { "PLD", "WAR", "DRK", "GNB", "WHM", "SCH", "AST", "MNK", "DRG", "NIN",
			"SAM", "BRD", "MCH", "DNC", "BLM", "SMN", "RDM" };

	private final SQLHelper sqlHelper;

	private List<Equipment> equipmentList = new ArrayList<>();

	private Map<String, Equipment> equipmentMap = new HashMap<>();

	private static EquipmemtModel instance;

	public static EquipmemtModel getInstance() {
		if (instance == null) {
			instance = new EquipmemtModel();
		}
		return instance;
	}

	private EquipmemtModel() {
		sqlHelper = SQLHelper.getInstance();
		load();
	}

	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}

	@SuppressWarnings("unchecked")
	private void load() {
		String sql = "select * from equipment";
		ResultSet ret = sqlHelper.execQuery(sql);
		try {
			while (ret.next()) {
				Equipment equipment = new Equipment();
				equipment.setId(SQLHelper.getIntValue(ret, "id"));
				equipment.setName(SQLHelper.getStringValue(ret, "name"));
				equipment.setPosition(SQLHelper.getIntValue(ret, "position"));
				equipment.setType(SQLHelper.getIntValue(ret, "type"));
				List<Class<? extends Job>> enableJobList = new ArrayList<>();
				String enableJobString = SQLHelper.getStringValue(ret, "enableJobList");
				String[] jobs = enableJobString.split("#");
				for (String job : jobs) {
					Class<?> clazz = Class.forName("meta." + job);
					if (clazz.isAssignableFrom(Job.class)) {
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
				equipment.setAttr(attr);
				equipmentList.add(equipment);
				equipmentMap.put(equipment.getName(), equipment);

			}
		} catch (Exception e) {
			System.out.println("从数据库加载装备数据失败:" + e.getMessage());
		}
	}

	public Equipment getEquipmentByName(String name) {
		return equipmentMap.get(name);
	}

}
