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
	 * 暴击
	 */
	private int criticalHit;

	/**
	 * 直击
	 */
	private int directHit;

	/**
	 * 信念
	 */
	private int determination;

	/**
	 * 信仰
	 */
	private int faith;

	/**
	 * 技能速度
	 */
	private int skillSpeed;

	/**
	 * 咏唱速度
	 */
	private int spellSpeed;

	/**
	 * 坚韧
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
	 * 魔晶石用构造方法
	 * @param materia
	 * @throws Error
	 */
	public Attr(Materia<String, Integer> materia) throws Error {
		switch (materia.getKey()) {
		case Materia.CRITICAL_HIT:
			this.criticalHit = Materia.ATTR_VALUE[materia.getValue()];
			break;
		case Materia.DIRECT_HIT:
			this.directHit = Materia.ATTR_VALUE[materia.getValue()];
			break;
		case Materia.DETERMINATION:
			this.determination = Materia.ATTR_VALUE[materia.getValue()];
			break;
		case Materia.FAITH:
			this.faith = Materia.ATTR_VALUE[materia.getValue()];
			break;
		case Materia.SKILL_SPEED:
			this.skillSpeed = Materia.ATTR_VALUE[materia.getValue()];
			break;
		case Materia.SPELL_SPEED:
			this.criticalHit = Materia.ATTR_VALUE[materia.getValue()];
			break;
		case Materia.FORTITUDE:
			this.fortitude = Materia.ATTR_VALUE[materia.getValue()];
			break;
		default:
			throw new Error("属性错误！");
		}
	}

	/**
	 * 重新计算属性时首先初始化成初始属性
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
	 * 增加一条属性（装备or魔晶石）
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
	 * 获得属性上限
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
	 * 检查并修正算上魔晶石加成后属性是否超过上限
	 * @param limit 属性上限
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

	@Override
	public String toString() {
		return "暴击:" + criticalHit
				+ ", 直击:" + directHit
				+ ", 信念:" + determination
				+ ", 技速:" + skillSpeed
				+ ", 唱速:"+ spellSpeed
				+ ", 信仰:" + faith
				+ ", 坚韧:" + fortitude;
	}

}