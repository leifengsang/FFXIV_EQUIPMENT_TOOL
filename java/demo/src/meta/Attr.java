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

	/**
	 * 耐力
	 */
	private int VIT;

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

	public int getVIT() {
		return VIT;
	}

	public void setVIT(int VIT) {
		this.VIT = VIT;
	}

	public Attr() {

	}

	/**
	 * 魔晶石用构造方法
	 * @param materia
	 * @throws Error
	 */
	public Attr(Materia materia) throws Error {
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
			this.spellSpeed = Materia.ATTR_VALUE[materia.getValue()];
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
		this.VIT += attr.VIT;
	}

	public void add(Food food) {
		if (food == null) {
			return;
		}
		addAttrByType(food.getAttrType1(), food.getValue1(), food.getLimit1());
		addAttrByType(food.getAttrType2(), food.getValue2(), food.getLimit2());
	}

	private void addAttrByType(String type, int value, int limit) {
		if (type.equals("")) {
			return;
		}
		switch (type) {
		case "criticalHit":
			if (limit == -1) {
				this.criticalHit += value;
			} else {
				this.criticalHit += Math.min(this.criticalHit * value / 100, limit);
			}
			break;
		case "directHit":
			if (limit == -1) {
				this.directHit += value;
			} else {
				this.directHit += Math.min(this.directHit * value / 100, limit);
			}
			break;
		case "determination":
			if (limit == -1) {
				this.determination += value;
			} else {
				this.determination += Math.min(this.determination * value / 100, limit);
			}
			break;
		case "faith":
			if (limit == -1) {
				this.faith += value;
			} else {
				this.faith += Math.min(this.faith * value / 100, limit);
			}
			break;
		case "fortitude":
			if (limit == -1) {
				this.fortitude += value;
			} else {
				this.fortitude += Math.min(this.fortitude * value / 100, limit);
			}
			break;
		case "skillSpeed":
			if (limit == -1) {
				this.skillSpeed += value;
			} else {
				this.skillSpeed += Math.min(this.skillSpeed * value / 100, limit);
			}
			break;
		case "spellSpeed":
			if (limit == -1) {
				this.spellSpeed += value;
			} else {
				this.spellSpeed += Math.min(this.spellSpeed * value / 100, limit);
			}
			break;
		default:
			throw new Error("attr type error!");
		}

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

	public int getEffectiveAttr(Materia materia, int limit) {
		int pre = -1;
		switch (materia.getKey()) {
		case Materia.CRITICAL_HIT:
			pre = this.criticalHit;
			break;
		case Materia.DIRECT_HIT:
			pre = this.directHit;
			break;
		case Materia.DETERMINATION:
			pre = this.determination;
			break;
		case Materia.FAITH:
			pre = this.faith;
			break;
		case Materia.SKILL_SPEED:
			pre = this.skillSpeed;
			break;
		case Materia.SPELL_SPEED:
			pre = this.spellSpeed;
			break;
		case Materia.FORTITUDE:
			pre = this.fortitude;
			break;
		default:
			return 0;
		}
		this.add(new Attr(materia));
		checkLimit(limit);
		switch (materia.getKey()) {
		case Materia.CRITICAL_HIT:
			return this.criticalHit - pre;
		case Materia.DIRECT_HIT:
			return this.directHit - pre;
		case Materia.DETERMINATION:
			return this.determination - pre;
		case Materia.FAITH:
			return this.faith - pre;
		case Materia.SKILL_SPEED:
			return this.skillSpeed - pre;
		case Materia.SPELL_SPEED:
			return this.spellSpeed - pre;
		case Materia.FORTITUDE:
			return this.fortitude - pre;
		default:
			return 0;
		}
	}

	/**
	 * 根据阈值计算暴击率
	 * @param threshold
	 * @return
	 */
	public static String getCriticalHitRateByThreshold(int threshold) {
		double rate = (threshold - 380) * 0.2 / 3300 + 0.05;
		return String.format("%.2f", rate * 100) + "%";

	}

	/**
	 * 根据阈值计算直击率
	 * @param threshold
	 * @return
	 */
	public static String getDirectHitRateByThreshold(int threshold) {
		double rate = (threshold - 380) * 0.55 / 3300;
		return String.format("%.2f", rate * 100) + "%";
	}

	/**
	 * 根据阈值计算增伤比
	 * @param threshold
	 * @return
	 */
	public static String getDeterminationRateByThreshold(int threshold) {
		double rate = (threshold - 340) * 0.13 / 3300;
		return String.format("%.2f", rate * 100) + "%";
	}

	/**
	 * 根据阈值计算增减伤比
	 * @param threshold
	 * @return
	 */
	public static String getFortitudeRateByThreshold(int threshold) {
		double rate = (threshold - 380) * 0.1 / 3300;
		return String.format("%.2f", rate * 100) + "%";
	}

	/**
	 * 根据阈值计算gcd
	 * @param threshold
	 * @return
	 */
	public static String getGcdByThreshold(int threshold) {
		//ROUNDDOWN(ROUNDDOWN(ROUNDDOWN((100*(1-0))*ROUNDDOWN((1000-ROUNDDOWN((130*(x-380)/3300),0))*2500/1000,0)/100,0)*100/1000,0)*100/100,0)/100
		double rate = ((int) (((1000 - ((int) (130 * (threshold - 380) * 1.0 / 3300))) * 2500 / 1000) * 0.1)) * 1.0
				/ 100;
		return String.format("%.2f", rate);
	}

	/**
	 * 根据阈值计算每三秒回蓝
	 * @param threshold
	 * @return
	 */
	public static String getRecoverByThreshold(int threshold) {
		int rate = (threshold - 340) * 150 / 3300 + 200;
		return String.valueOf(rate);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return "暴击:" + criticalHit + ", 直击:" + directHit + ", 信念:" + determination + ", 技速:" + skillSpeed + ", 唱速:"
				+ spellSpeed + ", 信仰:" + faith + ", 坚韧:" + fortitude;
	}

}