package gui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import meta.Equipment;
import meta.Materia;

@SuppressWarnings("serial")
public class MateriaPanel extends JPanel {

	private JComboBox<String> materiaBox;

	private JLabel label;

	private int index;

	private Equipment equipment;

	private boolean isMoreSocket() {
		return index > equipment.getNormalSocket();
	}

	public MateriaPanel(int index, Equipment equipment) {
		super();
		this.index = index;
		this.equipment = equipment;
		init();
	}

	private void init() {
		this.setSize(360, 30);
		this.setLayout(null);

		materiaBox = new JComboBox<String>();
		materiaBox.addItem("无");
		int levels[];
		if (!isMoreSocket()) {
			levels = Materia.NORMAL_SOCKET_LEVEL;
		} else {
			levels = Materia.MORE_SOCKET_LEVEL;
		}
		for (String name : Materia.NAMES) {
			for (int level : levels) {
				String text = String.format(Materia.MATERIA_FORMATTER, name, level, Materia.ATTR_VALUE[level]);
				if (equipment.getLevel() >= Materia.LEVEL_LIMIT[level]) {
					materiaBox.addItem(text);
				}
			}
		}
		this.add(materiaBox);
		int offsetX = 0;
		materiaBox.setBounds(offsetX, 0, 300, 30);
		Materia<String, Integer> materia = equipment.getMateriaMap().get(index);
		if (materia != null) {
			materiaBox.setSelectedItem(materia.getName());
		}
		offsetX += materiaBox.getWidth();

		label = new JLabel();
		label.setBounds(offsetX, 0, 50, 30);
		//绿色:0x0ab20a
		//红色:0xdd2d12
		this.add(label);
		setLabel();
	}

	private void setLabel() {
		
	}
}
