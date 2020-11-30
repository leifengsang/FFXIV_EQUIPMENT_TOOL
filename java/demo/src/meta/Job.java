package meta;

import java.util.HashMap;

/**
 * 特职
 * @author leifengsang
 */
public abstract class Job {

	public static final int DAMAGE_TYPE_PHYSICAL = 1; //物理
	public static final int DAMAGE_TYPE_MAGIC = 2; //魔法

	public static final int EXTRA_ATTR_TYPE_NULL = 10; //无
	public static final int EXTRA_ATTR_TYPE_FAITH = 11; // 信仰
	public static final int EXTRA_ATTR_TYPE_FORTITUDE = 12; //坚韧

	protected Attr attr = new Attr();

	protected HashMap<Integer, Equipment> equipmentMap = new HashMap<>();

	public Job() {

	}

	public Attr getAttr() {
		calAttr();
		return attr;
	}

	/**
	 * 计算自身属性
	 */
	private void calAttr() {
		attr.init();
		for (Equipment equipment : equipmentMap.values()) {
			attr.add(equipment.getAttr());
		}
	}

	public boolean isEnableSecondary() {
		return false;
	}

	public abstract int getDamageType();

	public abstract int getExtraAttrType();

	/**
	 * 根据职业获得技速/唱速
	 * @return
	 */
	public int getSpeed() {
		switch (this.getDamageType()) {
		case DAMAGE_TYPE_PHYSICAL:
			return this.attr.getSkillSpeed();
		case DAMAGE_TYPE_MAGIC:
			return this.attr.getSkillSpeed();
		default:
			return -1;
		}
	}

	/**
	 * 根据职业获得信仰/坚韧
	 * @return
	 */
	public int getExtraAttr() {
		switch (this.getExtraAttrType()) {
		case EXTRA_ATTR_TYPE_FAITH:
			return this.attr.getFaith();
		case EXTRA_ATTR_TYPE_FORTITUDE:
			return this.attr.getFortitude();
		default:
			return -1;
		}
	}

	/**
	 * 装备一件装备
	 * @param equipment
	 */
	public void equip(Equipment equipment) {
		if (equipment.getPosition() > Equipment.POS_LIMIT) {
			System.out.println("非法部位！");
			return;
		}

		if (equipment.getPosition() == Equipment.POS_SECONDARY) {
			if (!isEnableSecondary()) {
				System.out.println("主武器未装备或不支持副手！");
				return;
			}
		}

		equipmentMap.put(equipment.getPosition(), equipment);
	}
}