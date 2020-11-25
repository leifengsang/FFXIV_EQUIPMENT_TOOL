import java.util.List;

import meta.Equipment;
import model.EquipmemtModel;

public class Main {
	public static void main(String[] args) {
		String name = "";
		int length = 0;
		List<Equipment> list = EquipmemtModel.getInstance().getEquipmentList();
		for (Equipment equipment : list) {
			if (equipment.getName().length() > length) {
				length = equipment.getName().length();
				name = equipment.getName();
			}
		}
		System.out.println(name);
	}
}
