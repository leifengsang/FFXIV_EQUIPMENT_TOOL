package meta;

/**
 * 学者
 * @author leifengsang
 */
public class SCH extends Job {

	@Override
	public int getDamageType() {
		return Job.DAMAGE_TYPE_MAGIC;
	}

	@Override
	public int getExtraAttrType() {
		return Job.EXTRA_ATTR_TYPE_FAITH;
	}
}
