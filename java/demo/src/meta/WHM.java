package meta;

/**
 * 白魔法师
 * @author leifengsang
 */
public class WHM extends Job {

	@Override
	public int getDamageType() {
		return Job.DAMAGE_TYPE_MAGIC;
	}

	@Override
	public int getExtraAttrType() {
		return Job.EXTRA_ATTR_TYPE_FAITH;
	}
}
