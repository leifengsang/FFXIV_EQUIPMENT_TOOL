package meta;

/**
 * 骑士
 * @author leifengsang
 */
public class PLD extends Job {

	@Override
	public boolean isEnableSecondary() {
		return true;
	}

	@Override
	public int getDamageType() {
		return Job.DAMAGE_TYPE_PHYSICAL;
	}

	@Override
	public int getExtraAttrType() {
		return Job.EXTRA_ATTR_TYPE_FORTITUDE;
	}
}
