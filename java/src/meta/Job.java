package meta;

import java.util.HashMap;

/**
 * 特职
 * @author Cookies
 */
public class Job {
	protected Attr attr = new Attr();

	protected HashMap<Integer, Equipment> equipmentMap = new HashMap<>();

	public Job() {

	}

	public Attr getAttr() {
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
			Equipment arms = equipmentMap.get(Equipment.POS_ARMS);
			if (arms == null || !arms.isEnableSecondary()) {
				System.out.println("主武器未装备或不支持副手！");
				return;
			}
		}

		equipmentMap.put(equipment.getPosition(), equipment);
		calAttr();
	}
}
