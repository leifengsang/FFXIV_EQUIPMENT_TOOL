package meta;

import java.util.ArrayList;
import java.util.List;

public class Equipment {
	public static final int POS_ARMS = 1; //武器

	public static final int POS_SECONDARY = 2; //副手

	public static final int POS_HEAD = 3; //头

	public static final int POS_BODY = 4; //衣服

	public static final int POS_HANDS = 5; //手

	public static final int POS_WAIST = 6; //腰带

	public static final int POS_LEGS = 7; //裤子

	public static final int POS_FEET = 8; //鞋子

	public static final int POS_EARRINGS = 9; //耳环

	public static final int POS_NECKLACE = 10; //项链

	public static final int POS_BRACELETS = 11; //手链

	public static final int POS_RING1 = 12; //戒指1

	public static final int POS_RING2 = 13; //戒指2

	public static final int POS_LIMIT = 13; //部位上限

	public static final int[] MAIN_EQUIPMENT_LIST = { 1, 3, 4, 5, 7, 8 };

	public static final int[] SUB_EQUIPMENT_LIST = { 6, 9, 10, 11, 12, 13 };

	public static final int TYPE_HQ = 1; //绿

	public static final int TYPE_UN_COMMON = 2; //白

	public static final int TYPE_COMMON = 3; //蓝

	public static final int TYPE_PURPLE = 4; //紫

	/**
	 * 装备ID
	 */
	private int id;

	/**
	 * 装备名称
	 */
	private String name;

	/**
	 * 装备属性
	 */
	private Attr attr;

	/**
	 * 装备支持的特职
	 */
	private List<Class<? extends Job>> enableJobList;

	/**
	 * 装备类型
	 * 1.制作HQ（五孔禁断）
	 * 2.基础装备（二孔）
	 * 3.紫武（五孔不禁断）
	 */
	private int type;

	/**
	 * 装备的部位
	 */
	private int position;

	/**
	 * 装等
	 */
	private int level;

	/**
	 * 魔晶石
	 */
	private ArrayList<Materia<String, Integer>> materiaList = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Attr getAttr() {
		try {
			Attr attr = (Attr) this.attr.clone();
			int limit = this.attr.getMateriaValueLimit();
			for (Materia<String, Integer> materia : materiaList) {
				attr.add(new Attr(materia));
			}
			attr.checkLimit(limit);
			return attr;
		} catch (Exception e) {
			return null;
		}
	}

	public void setAttr(Attr attr) {
		this.attr = attr;
	}

	public List<Class<? extends Job>> getEnableJobList() {
		return enableJobList;
	}

	public void setEnableJobList(List<Class<? extends Job>> enableJobList) {
		this.enableJobList = enableJobList;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * TODO:后续在别的地方写一个判断主手是否支持副手
	 * 目前支持的有：单手法杖、骑士剑
	 * 暂不考虑单手法杖、只需判断Job.EquipmentMap.get(Equipment.POS_ARMS).enableJobList.contains(PLD.class)
	 * @return
	 */
	public boolean isEnableSecondary() {
		return false;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Equipment() {

	}

	public ArrayList<Materia<String, Integer>> getMateriaList() {
		return materiaList;
	}

	public void setMateriaList(ArrayList<Materia<String, Integer>> materiaList) {
		this.materiaList = materiaList;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}