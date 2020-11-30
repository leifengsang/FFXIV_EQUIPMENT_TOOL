package meta;

/**
 * 枪刃战士
 * @author leifengsang
 */
public class GNB extends Job {

	@Override
	public int getDamageType() {
		return Job.DAMAGE_TYPE_PHYSICAL;
	}

	@Override
	public int getExtraAttrType() {
		return Job.EXTRA_ATTR_TYPE_FORTITUDE;
	}
}
