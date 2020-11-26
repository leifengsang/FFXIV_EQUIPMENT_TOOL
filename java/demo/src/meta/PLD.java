package meta;

/**
 * 骑士
 * @author Cookies
 */
public class PLD extends Job {

	@Override
	public boolean isEnableSecondary() {
		return true;
	}

	@Override
	public int getDamageType() {
		return Job.DAMAGE_TYPE_PHYCICAL;
	}

	@Override
	public int getExtraAttrType() {
		return Job.EXTRA_ATTR_TYPE_FORTITUDE;
	}
}
