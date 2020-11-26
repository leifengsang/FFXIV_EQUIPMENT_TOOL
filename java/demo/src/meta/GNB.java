package meta;

/**
 * 枪刃战士
 * @author Cookies
 */
public class GNB extends Job {

	@Override
	public int getDamageType() {
		return Job.DAMAGE_TYPE_PHYCICAL;
	}

	@Override
	public int getExtraAttrType() {
		return Job.EXTRA_ATTR_TYPE_FORTITUDE;
	}
}
