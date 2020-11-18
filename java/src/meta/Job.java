package meta;

import java.util.HashMap;

/**
 * ��ְ
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
	 * ������������
	 */
	private void calAttr() {
		attr.init();
		for (Equipment equipment : equipmentMap.values()) {
			attr.add(equipment.getAttr());
		}
	}

	/**
	 * װ��һ��װ��
	 * @param equipment
	 */
	public void equip(Equipment equipment) {
		if (equipment.getPosition() > Equipment.POS_LIMIT) {
			System.out.println("�Ƿ���λ��");
			return;
		}

		if (equipment.getPosition() == Equipment.POS_SECONDARY) {
			Equipment arms = equipmentMap.get(Equipment.POS_ARMS);
			if (arms == null || !arms.isEnableSecondary()) {
				System.out.println("������δװ����֧�ָ��֣�");
				return;
			}
		}

		equipmentMap.put(equipment.getPosition(), equipment);
		calAttr();
	}
}
