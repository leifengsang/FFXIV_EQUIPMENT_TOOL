package meta;

/**
 * 武僧
 * @author Cookies
 */
public class MNK extends Job {

	@Override
	public int getDamageType() {
		return Job.DAMAGE_TYPE_PHYCICAL;
	}

	@Override
	public int getExtraAttrType() {
		return Job.EXTRA_ATTR_TYPE_NULL;
	}
}
