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

	public static final int TYPE_PURPLE = 1; //紫

	public static final int TYPE_COMMON = 2; //蓝

	public static final int TYPE_HQ = 3; //绿

	public static final int TYPE_UN_COMMON = 4; //白

	public static final int TYPE_MAGIC = 5; //粉

	/**
	 * 装备ID
	 */
	private String id;

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
	 * 普通镶嵌孔
	 */
	private int normalSocket;

	/**
	 * 是否可以禁断
	 */
	private boolean moreSocket;

	/**
	 * 魔晶石
	 */
	private ArrayList<Materia<String, Integer>> materiaList = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public int getNormalSocket() {
		return normalSocket;
	}

	public void setNormalSocket(int normalSocket) {
		this.normalSocket = normalSocket;
	}

	public boolean isMoreSocket() {
		return moreSocket;
	}

	public void setMoreSocket(boolean moreSocket) {
		this.moreSocket = moreSocket;
	}

	/**
	 * 一键装备不可能同时拥有技速和唱速，所以直接取max
	 * @return
	 */
	public int getSpeed() {
		return Math.max(this.getAttr().getSkillSpeed(), this.getAttr().getSpellSpeed());
	}

	/**
	 * 一键装备不可能同时拥有信仰和坚韧，所以直接取max
	 * @return
	 */
	public int getExtra() {
		return Math.max(this.getAttr().getFaith(), this.getAttr().getFortitude());
	}

	/**
	 * 是一件战职装备
	 * @return
	 */
	public boolean isBattle() {
		return this.attr.getVIT() != 0;
	}
}