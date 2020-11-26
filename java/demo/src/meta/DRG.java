package meta;

/**
 * 龙骑士
 * @author Cookies
 */
public class DRG extends Job {

	@Override
	public int getDamageType() {
		return Job.DAMAGE_TYPE_PHYCICAL;
	}

	@Override
	public int getExtraAttrType() {
		return Job.EXTRA_ATTR_TYPE_NULL;
	}
}
