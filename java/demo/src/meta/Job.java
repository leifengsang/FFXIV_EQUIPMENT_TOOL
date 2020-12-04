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

	private Attr attr = new Attr();

	private HashMap<Integer, Equipment> equipmentMap = new HashMap<>();

	private Food food;

	public Job() {

	}

	public Attr getAttr() {
		calAttr();
		return attr;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	/**
	 * 计算自身属性
	 */
	private void calAttr() {
		attr.init();
		for (Equipment equipment : equipmentMap.values()) {
			if (equipment == null) {
				continue;
			}
			attr.add(equipment.getAttr());
		}
		attr.add(food);
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
			return this.getAttr().getSkillSpeed();
		case DAMAGE_TYPE_MAGIC:
			return this.getAttr().getSpellSpeed();
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
			return this.getAttr().getFaith();
		case EXTRA_ATTR_TYPE_FORTITUDE:
			return this.getAttr().getFortitude();
		default:
			return -1;
		}
	}

	/**
	 * 装备一件装备
	 * @param equipment
	 */
	public String equip(Equipment equipment) {
		if (equipment.getPosition() > Equipment.POS_LIMIT) {
			return "非法部位！";
		}

		if (equipment.getPosition() == Equipment.POS_SECONDARY) {
			if (!isEnableSecondary()) {
				return "该职业不支持副手！";
			}
		}

		if (equipment.getPosition() == Equipment.POS_RING1) {
			Equipment ep = equipmentMap.get(Equipment.POS_RING2);
			if (ep != null && equipment.getName().equals(ep.getName())) {
				if (equipment.getType() == Equipment.TYPE_RARE) {
					return "蓝色戒指只能同时装备一枚！";
				} else if (equipment.getType() == Equipment.TYPE_UN_COMMON) {
					return "副本绿色戒指只能同时装备一枚！";
				}
			}
		} else if (equipment.getPosition() == Equipment.POS_RING2) {
			Equipment ep = equipmentMap.get(Equipment.POS_RING1);
			if (ep != null && equipment.getName().equals(ep.getName())) {
				if (equipment.getType() == Equipment.TYPE_RARE) {
					return "蓝色戒指只能同时装备一枚！";
				} else if (equipment.getType() == Equipment.TYPE_UN_COMMON) {
					return "副本绿色戒指只能同时装备一枚！";
				}
			}
		}

		equipmentMap.put(equipment.getPosition(), equipment);
		return null;
	}

	/**
	 * 移除指定部位的装备
	 * @param position
	 */
	public void removeEquipmentByPosition(int position) {
		equipmentMap.put(position, null);
	}

}