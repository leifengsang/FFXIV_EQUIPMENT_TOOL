package gui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JDialog;

import meta.Equipment;

@SuppressWarnings("serial")
public class MateriaView extends JDialog {

	private static final int WIDTH = 500;
	private static final int HEIGHT = 300;

	private Equipment equipment;

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
		this.setVisible(true);
	}
}
