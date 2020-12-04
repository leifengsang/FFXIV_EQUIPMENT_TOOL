package meta;

import model.Model;

public class Food {

	private String id;

	private int level;

	private String attrType1;

	private int value1;

	private int limit1;

	private String attrType2;

	private int value2;

	private int limit2;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getAttrType1() {
		return attrType1;
	}

	public void setAttrType1(String attrType1) {
		this.attrType1 = attrType1;
	}

	public int getLimit1() {
		return limit1;
	}

	public void setLimit1(int limit1) {
		this.limit1 = limit1;
	}

	public String getAttrType2() {
		return attrType2;
	}

	public void setAttrType2(String attrType2) {
		this.attrType2 = attrType2;
	}

	public int getLimit2() {
		return limit2;
	}

	public void setLimit2(int limit2) {
		this.limit2 = limit2;
	}

	public int getValue1() {
		return value1;
	}

	public void setValue1(int value1) {
		this.value1 = value1;
	}

	public int getValue2() {
		return value2;
	}

	public void setValue2(int value2) {
		this.value2 = value2;
	}

	public String getName() {
		return Model.getInstance().getNameById(id);
	}

}
