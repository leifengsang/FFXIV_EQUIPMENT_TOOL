package meta;

/**
 * 诗人
 * @author leifengsang
 */
public class BRD extends Job {

	@Override
	public int getDamageType() {
		return Job.DAMAGE_TYPE_PHYSICAL;
	}

	@Override
	public int getExtraAttrType() {
		return Job.EXTRA_ATTR_TYPE_NULL;
	}
}
