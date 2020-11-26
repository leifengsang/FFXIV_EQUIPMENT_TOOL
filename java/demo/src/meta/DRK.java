package meta;

/**
 * 暗黑骑士
 * @author Cookies
 */
public class DRK extends Job {

	@Override
	public int getDamageType() {
		return Job.DAMAGE_TYPE_PHYCICAL;
	}

	@Override
	public int getExtraAttrType() {
		return Job.EXTRA_ATTR_TYPE_FORTITUDE;
	}
}
