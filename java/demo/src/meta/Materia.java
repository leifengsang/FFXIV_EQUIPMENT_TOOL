package meta;

import java.util.Map.Entry;

/**
 * @author Cookies
 * @param <K> type of materia
 * @param <V> level of materia
 */
public class Materia<K, V> implements Entry<K, V> {
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
	public static final int ATTR_VALUE[] = { 0, 1, 3, 4, 6, 12, 60, 20, 80 };

	/**
	 * 镶嵌所需装等
	 */
	public static final int LEVEL_LIMIT[] = { 0, 15, 30, 45, 70, 120, 290, 420, 420 };

	private final K key;

	private V value;

	public Materia(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		V old = this.value;
		this.value = value;
		return old;
	}
}
