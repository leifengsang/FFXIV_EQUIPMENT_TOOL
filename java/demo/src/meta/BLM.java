package meta;

/**
 * 黑魔法师
 * @author Cookies
 */
public class BLM extends Job {

	@Override
	public int getDamageType() {
		return Job.DAMAGE_TYPE_MAGIC;
	}

	@Override
	public int getExtraAttrType() {
		return Job.EXTRA_ATTR_TYPE_NULL;
	}
}
