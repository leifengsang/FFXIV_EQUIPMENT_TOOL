package meta;

/**
 * 舞者
 * @author leifengsang
 */
public class DNC extends Job {

	@Override
	public int getDamageType() {
		return Job.DAMAGE_TYPE_PHYSICAL;
	}

	@Override
	public int getExtraAttrType() {
		return Job.EXTRA_ATTR_TYPE_NULL;
	}
}
