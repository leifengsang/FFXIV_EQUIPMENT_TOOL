package meta;

import java.util.Map.Entry;

public class Materia<K, V> implements Entry<K, V> {
	public static final String CRITICAL_HIT = "criticalHit";

	public static final String DIRECT_HIT = "directHit";

	public static final String DETERMINATION = "determination";

	public static final String FAITH = "faith";

	public static final String SKILL_SPEED = "skillSpeed";

	public static final String SPELL_SPEED = "spellSpeed";
	
	public static final String FORTITUDE = "fortitude";
	
	public static final int VALUE_8 = 60;
	
	public static final int VALUE_7 = 20;

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
