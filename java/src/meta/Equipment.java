package meta;

import java.util.ArrayList;
import java.util.List;

public class Equipment {
	public static final int POS_ARMS = 1; //����

	public static final int POS_SECONDARY = 2; //����

	public static final int POS_HEAD = 3; //ͷ

	public static final int POS_BODY = 4; //�·�

	public static final int POS_HANDS = 5; //��

	public static final int POS_WAIST = 6; //����

	public static final int POS_LEGS = 7; //����

	public static final int POS_FEET = 8; //Ь��

	public static final int POS_EARRINGS = 9; //����

	public static final int POS_NECKLACE = 10; //����

	public static final int POS_BRACELETS = 11; //����

	public static final int POS_RING1 = 12; //��ָ1

	public static final int POS_RING2 = 13; //��ָ2

	public static final int POS_LIMIT = 13; //��λ����

	public static final int[] MAIN_EQUIPMENT_LIST = { 1, 3, 4, 5, 7, 8 };

	public static final int[] SUB_EQUIPMENT_LIST = { 6, 9, 10, 11, 12, 13 };

	public static final int TYPE_HQ = 1; //��

	public static final int TYPE_UN_COMMON = 2; //��

	public static final int TYPE_COMMON = 3; //��

	public static final int TYPE_PURPLE = 4; //��

	/**
	 * װ��ID
	 */
	private int id;

	/**
	 * װ������
	 */
	private String name;

	/**
	 * װ������
	 */
	private Attr attr;

	/**
	 * װ��֧�ֵ���ְ
	 */
	private List<Class<? extends Job>> enableJobList;

	/**
	 * װ������
	 * 1.����HQ����׽��ϣ�
	 * 2.����װ�������ף�
	 * 3.���䣨��ײ����ϣ�
	 */
	private int type;

	/**
	 * װ���Ĳ�λ
	 */
	private int position;

	/**
	 * װ��
	 */
	private int level;

	/**
	 * ħ��ʯ
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
	 * TODO:�����ڱ�ĵط�дһ���ж������Ƿ�֧�ָ���
	 * Ŀǰ֧�ֵ��У����ַ��ȡ���ʿ��
	 * �ݲ����ǵ��ַ��ȡ�ֻ���ж�Job.EquipmentMap.get(Equipment.POS_ARMS).enableJobList.contains(PLD.class)
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
