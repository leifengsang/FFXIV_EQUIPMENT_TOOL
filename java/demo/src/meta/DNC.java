package meta;

/**
 * 舞者
 * @author Cookies
 */
public class DNC extends Job {

	@Override
	public int getDamageType() {
		return Job.DAMAGE_TYPE_PHYCICAL;
	}

	@Override
	public int getExtraAttrType() {
		return Job.EXTRA_ATTR_TYPE_NULL;
	}
}
