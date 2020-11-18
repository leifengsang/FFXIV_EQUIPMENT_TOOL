import java.util.List;

import meta.Equipment;

public class Main {
	public static void main(String[] args) {
		List<Equipment> equipmentList = EquipmemtModel.getInstance().getEquipmentList();
		for(Equipment equipment : equipmentList){
			System.out.println(equipment.getName());
		}
	}
}
