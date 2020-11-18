package meta;

public class Attr implements Cloneable {
	private static final int BASE_CRITICAL_HIT = 380;

	private static final int BASE_DIRECT_HIT = 380;

	private static final int BASE_DETERMINATION = 340;

	private static final int BASE_FAITH = 340;

	private static final int BASE_SKILL_SPEED = 380;

	private static final int BASE_SPELL_SPEED = 380;

	private static final int BASE_FORTITUDE = 380;

	/**
	 * ����
	 */
	private int criticalHit;

	/**
	 * ֱ��
	 */
	private int directHit;

	/**
	 * ����
	 */
	private int determination;

	/**
	 * ����
	 */
	private int faith;

	/**
	 * �����ٶ�
	 */
	private int skillSpeed;

	/**
	 * ӽ���ٶ�
	 */
	private int spellSpeed;

	/**
	 * ����
	 */
	private int fortitude;

	public int getCriticalHit() {
		return criticalHit;
	}

	public void setCriticalHit(int criticalHit) {
		this.criticalHit = criticalHit;
	}

	public int getDirectHit() {
		return directHit;
	}

	public void setDirectHit(int directHit) {
		this.directHit = directHit;
	}

	public int getDetermination() {
		return determination;
	}

	public void setDetermination(int determination) {
		this.determination = determination;
	}

	public int getFaith() {
		return faith;
	}

	public void setFaith(int faith) {
		this.faith = faith;
	}

	public int getSkillSpeed() {
		return skillSpeed;
	}

	public void setSkillSpeed(int skillSpeed) {
		this.skillSpeed = skillSpeed;
	}

	public int getSpellSpeed() {
		return spellSpeed;
	}

	public void setSpellSpeed(int spellSpeed) {
		this.spellSpeed = spellSpeed;
	}

	public int getFortitude() {
		return fortitude;
	}

	public void setFortitude(int fortitude) {
		this.fortitude = fortitude;
	}

	public Attr() {

	}

	/**
	 * ħ��ʯ�ù��췽��
	 * @param materia
	 * @throws Error
	 */
	public Attr(Materia<String, Integer> materia) throws Error {
		switch (materia.getKey()) {
		case Materia.CRITICAL_HIT:
			this.criticalHit = materia.getValue();
			break;
		case Materia.DIRECT_HIT:
			this.directHit = materia.getValue();
			break;
		case Materia.DETERMINATION:
			this.determination = materia.getValue();
			break;
		case Materia.FAITH:
			this.faith = materia.getValue();
			break;
		case Materia.SKILL_SPEED:
			this.skillSpeed = materia.getValue();
			break;
		case Materia.SPELL_SPEED:
			this.criticalHit = materia.getValue();
			break;
		case Materia.FORTITUDE:
			this.fortitude = materia.getValue();
			break;
		default:
			throw new Error("���Դ���");
		}
	}

	/**
	 * ���¼�������ʱ���ȳ�ʼ���ɳ�ʼ����
	 */
	public void init() {
		this.criticalHit = BASE_CRITICAL_HIT;
		this.directHit = BASE_DIRECT_HIT;
		this.determination = BASE_DETERMINATION;
		this.faith = BASE_FAITH;
		this.skillSpeed = BASE_SKILL_SPEED;
		this.spellSpeed = BASE_SPELL_SPEED;
		this.fortitude = BASE_FORTITUDE;
	}

	/**
	 * ����һ�����ԣ�װ��orħ��ʯ��
	 * @param attr
	 */
	public void add(Attr attr) {
		this.criticalHit += attr.getCriticalHit();
		this.directHit += attr.getDirectHit();
		this.determination += attr.getDetermination();
		this.faith += attr.getFaith();
		this.skillSpeed += attr.getSkillSpeed();
		this.spellSpeed += attr.getSpellSpeed();
		this.fortitude += attr.getFortitude();
	}

	/**
	 * �����������
	 * @return
	 */
	public int getMateriaValueLimit() {
		int ret = 0;
		ret = Math.max(ret, this.criticalHit);
		ret = Math.max(ret, this.directHit);
		ret = Math.max(ret, this.determination);
		ret = Math.max(ret, this.faith);
		ret = Math.max(ret, this.skillSpeed);
		ret = Math.max(ret, this.spellSpeed);
		ret = Math.max(ret, this.fortitude);
		return ret;
	}

	/**
	 * ��鲢��������ħ��ʯ�ӳɺ������Ƿ񳬹�����
	 * @param limit ��������
	 */
	public void checkLimit(int limit) {
		this.criticalHit = Math.min(this.criticalHit, limit);
		this.directHit = Math.min(this.directHit, limit);
		this.determination = Math.min(this.determination, limit);
		this.faith = Math.min(this.faith, limit);
		this.skillSpeed = Math.min(this.skillSpeed, limit);
		this.spellSpeed = Math.min(this.spellSpeed, limit);
		this.fortitude = Math.min(this.fortitude, limit);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
