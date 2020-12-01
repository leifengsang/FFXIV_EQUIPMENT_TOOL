package gui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JDialog;

import meta.Attr;
import meta.Equipment;
import meta.Materia;

@SuppressWarnings("serial")
public class MateriaView extends JDialog {

	private static final int WIDTH = 500;
	private static final int HEIGHT = 300;
	private static final int SPACE_Y = 10;
	private static final int TOP = 20;
	private static final int LEFT = 70;

	private Equipment equipment;

	private MateriaPanel materiaPanels[] = new MateriaPanel[5];

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public MateriaView(Frame owner, Equipment equipment) {
		super(owner, true);
		this.equipment = equipment;
		init();
	}

	private void init() {
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screensize.getWidth();
		int screenHeight = (int) screensize.getHeight();
		this.setBounds((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2, WIDTH, HEIGHT);
		this.setTitle("魔晶石");
		this.setLayout(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		int offsetY = TOP;
		for (int i = 0; i < 5; i++) {
			//禁断孔
			if (i >= equipment.getNormalSocket() && !equipment.hasMoreSocket()) {
				break;
			}
			materiaPanels[i] = new MateriaPanel(i, equipment);
			materiaPanels[i].setLocation(LEFT, offsetY);
			materiaPanels[i].getMateriaBox().addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					calAttr();
				}
			});
			this.add(materiaPanels[i]);
			offsetY += materiaPanels[i].getHeight() + SPACE_Y;
		}
		calAttr();

		this.setVisible(true);
	}

	private void calAttr() {
		Attr attr = equipment.getAttrNoMateria();
		int limit = attr.getMateriaValueLimit();
		for (int i = 0; i < 5; i++) {
			//禁断孔
			if (i >= equipment.getNormalSocket() && !equipment.hasMoreSocket()) {
				return;
			}
			equipment.getMateriaMap().put(i, materiaPanels[i].createMateria());
			Materia<String, Integer> materia = materiaPanels[i].getMateria();
			if (materia == null) {
				materiaPanels[i].setLabel("");
				continue;
			}
			equipment.getMateriaMap().put(i, materia);
			materiaPanels[i].setLabel(attr.getEffectiveAttr(materia, limit));
		}
	}
}
