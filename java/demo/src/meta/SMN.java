package meta;

/**
 * 召唤师
 * @author Cookies
 */
public class SMN extends Job {

	@Override
	public int getDamageType() {
		return Job.DAMAGE_TYPE_MAGIC;
	}

	@Override
	public int getExtraAttrType() {
		return Job.EXTRA_ATTR_TYPE_NULL;
	}
}
