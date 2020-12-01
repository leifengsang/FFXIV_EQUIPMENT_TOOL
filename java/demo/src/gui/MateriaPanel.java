package gui;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import meta.Equipment;
import meta.Materia;

@SuppressWarnings("serial")
public class MateriaPanel extends JPanel {

	private static final Color COLOR_GREEN = new Color(0x0ab20a);
	private static final Color COLOR_RED = new Color(0xdd2d12);

	private JComboBox<String> materiaBox;

	private JLabel label;

	private int index;

	private Equipment equipment;

	private boolean isMoreSocket() {
		return index > equipment.getNormalSocket();
	}

	public JComboBox<String> getMateriaBox() {
		return materiaBox;
	}

	public void setMateriaBox(JComboBox<String> materiaBox) {
		this.materiaBox = materiaBox;
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
		Materia<String, Integer> materia = getMateria();
		if (materia != null) {
			materiaBox.setSelectedItem(materia.getName());
		}
		offsetX += materiaBox.getWidth() + 10;

		label = new JLabel();
		label.setBounds(offsetX, 0, 50, 30);
		this.add(label);
	}

	public Materia<String, Integer> getMateria() {
		return equipment.getMateriaMap().get(index);
	}

	public Materia<String, Integer> createMateria() {
		String name = (String) materiaBox.getSelectedItem();
		if (name.equals("无")) {
			return null;
		}
		String pre = name.split("\\[")[0]; //暴击8
		String key = Materia.NAME_MAP.get(pre.substring(0, pre.length() - 1));
		int value = Integer.parseInt((String) pre.subSequence(pre.length() - 1, pre.length()));
		return new Materia<String, Integer>(key, value);
	}

	public void setLabel(int value) {
		label.setText("+" + value);
		label.setForeground(Materia.ATTR_VALUE[getMateria().getValue()] == value ? COLOR_GREEN : COLOR_RED);
	}

	public void setLabel(String text) {
		label.setText(text);
	}
}
