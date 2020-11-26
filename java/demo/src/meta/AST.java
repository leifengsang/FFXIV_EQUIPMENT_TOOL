package meta;

/**
 * 占星术士
 * @author Cookies
 */
public class AST extends Job {

	@Override
	public int getDamageType() {
		return Job.DAMAGE_TYPE_MAGIC;
	}

	@Override
	public int getExtraAttrType() {
		return Job.EXTRA_ATTR_TYPE_FAITH;
	}
}
