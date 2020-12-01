package meta;

import java.util.HashMap;
import java.util.Map;

/**
 * @author leifengsang
 */
public class Materia {
	public static final String CRITICAL_HIT = "criticalHit";

	public static final String DIRECT_HIT = "directHit";

	public static final String DETERMINATION = "determination";

	public static final String FAITH = "faith";

	public static final String SKILL_SPEED = "skillSpeed";

	public static final String SPELL_SPEED = "spellSpeed";

	public static final String FORTITUDE = "fortitude";

	/**
	 * 各等级魔晶石的属性
	 */
	public static final int ATTR_VALUE[] = { 0, 1, 3, 4, 6, 12, 40, 20, 60 };

	/**
	 * 镶嵌所需装等
	 */
	public static final int LEVEL_LIMIT[] = { 0, 15, 30, 45, 70, 120, 290, 420, 420 };

	public static final int NORMAL_SOCKET_LEVEL[] = { 1, 2, 3, 4, 5, 6, 7, 8 };

	public static final int MORE_SOCKET_LEVEL[] = { 1, 2, 3, 4, 5, 7 };

	/**
	 * 显示用
	 * example:暴击8[+60]
	 * String.format(MATERIA_FORMATTER,name,level,ATTR_VALUES[level])
	 */
	public static final String NAMES[] = { "暴击", "直击", "信念", "信仰", "坚韧", "技能速度", "咏唱速度" };
	public static final String MATERIA_FORMATTER = "%s%d[+%d]";

	public static final Map<String, String> NAME_MAP = new HashMap<>();
	static {
		NAME_MAP.put("criticalHit", "暴击");
		NAME_MAP.put("directHit", "直击");
		NAME_MAP.put("determination", "信念");
		NAME_MAP.put("faith", "信仰");
		NAME_MAP.put("fortitude", "坚韧");
		NAME_MAP.put("skillSpeed", "技能速度");
		NAME_MAP.put("spellSpeed", "咏唱速度");

		NAME_MAP.put("暴击", "criticalHit");
		NAME_MAP.put("直击", "directHit");
		NAME_MAP.put("信念", "determination");
		NAME_MAP.put("信仰", "faith");
		NAME_MAP.put("技能速度", "skillSpeed");
		NAME_MAP.put("咏唱速度", "spellSpeed");
	}

	private String key;

	private int value;

	public Materia(String key, int value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return String.format(MATERIA_FORMATTER, NAME_MAP.get((String) this.key), (int) value, ATTR_VALUE[(int) value]);
	}
}
