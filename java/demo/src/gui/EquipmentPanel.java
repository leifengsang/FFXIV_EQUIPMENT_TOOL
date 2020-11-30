package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import meta.Equipment;
import meta.Job;

@SuppressWarnings("serial")
public class EquipmentPanel extends JPanel {

	private Equipment equipment;

	private int damageType;
	private int extraType;

	private JRadioButton nameBtn;
	private JButton materiaBtn;
	private JLabel criticalHitLabel;
	private JLabel directHitLabel;
	private JLabel determinationLabel;
	private JLabel speedLabel;
	private JLabel extraLabel;

	public Equipment getEquipment() {
		return equipment;
	}

	public JRadioButton getNameBtn() {
		return nameBtn;
	}

	public JButton getMateriaBtn() {
		return materiaBtn;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public void setName(String name) {
		nameBtn.setText(name);
	}

	public void setCriticalHit(int criticalHit) {
		criticalHitLabel.setText(String.valueOf(criticalHit));
	}

	public void setDirectHit(int directHit) {
		directHitLabel.setText(String.valueOf(directHit));
	}

	public void setDetermination(int determination) {
		determinationLabel.setText(String.valueOf(determination));
	}

	public void setSpeed(int speed) {
		speedLabel.setText(String.valueOf(speed));
	}

	public void setExtra(int extra) {
		if (extraLabel == null) {
			return;
		}
		extraLabel.setText(String.valueOf(extra));
	}

	public EquipmentPanel(Equipment equipment, int damageType, int extraType) {
		super();
		this.equipment = equipment;
		this.damageType = damageType;
		this.extraType = extraType;
		init();
	}

	private void init() {
		this.setSize(970, 30);
		this.setLayout(null);

		int offsetX = 0;
		nameBtn = new JRadioButton(String.format("[%s]", equipment.getLevel()) + equipment.getName());
		nameBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nameBtn.setBounds(offsetX, 0, MainWindow.HEAD_NAME_WIDTH, this.getHeight());
		this.add(nameBtn);
		offsetX += MainWindow.HEAD_NAME_WIDTH;

		materiaBtn = new JButton("点击查看");
		materiaBtn.setBounds(offsetX, 0, MainWindow.HEAD_MATERIA_WIDTH, this.getHeight());
		materiaBtn.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(materiaBtn);
		offsetX += MainWindow.HEAD_MATERIA_WIDTH;

		final int remainWidth = this.getWidth() - offsetX - 14;
		final int remainBlock = 5;
		final int singleWidth = remainWidth / remainBlock;
		criticalHitLabel = new BorderLabel();
		criticalHitLabel.setBounds(offsetX, 0, singleWidth, this.getHeight());
		criticalHitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(criticalHitLabel);
		offsetX += singleWidth;

		directHitLabel = new BorderLabel();
		directHitLabel.setBounds(offsetX, 0, singleWidth, this.getHeight());
		directHitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(directHitLabel);
		offsetX += singleWidth;

		determinationLabel = new BorderLabel();
		determinationLabel.setBounds(offsetX, 0, singleWidth, this.getHeight());
		determinationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(determinationLabel);
		offsetX += singleWidth;

		speedLabel = new BorderLabel();
		speedLabel.setBounds(offsetX, 0, singleWidth, this.getHeight());
		speedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(speedLabel);
		offsetX += singleWidth;

		if (extraType != Job.EXTRA_ATTR_TYPE_NULL) {
			extraLabel = new BorderLabel();
			extraLabel.setBounds(offsetX, 0, singleWidth, this.getHeight());
			extraLabel.setHorizontalAlignment(SwingConstants.CENTER);
			this.add(extraLabel);
			offsetX += singleWidth;
		}

		refreshAttr();
	}

	public void refreshAttr() {
		setCriticalHit(equipment.getAttr().getCriticalHit());
		setDirectHit(equipment.getAttr().getDirectHit());
		setDetermination(equipment.getAttr().getDetermination());
		if (damageType == Job.DAMAGE_TYPE_PHYSICAL) {
			setExtra(equipment.getAttr().getSkillSpeed());
		} else {
			setExtra(equipment.getAttr().getSpellSpeed());
		}
		if (extraType == Job.EXTRA_ATTR_TYPE_FAITH) {
			setExtra(equipment.getAttr().getFaith());
		} else if (extraType == Job.EXTRA_ATTR_TYPE_FORTITUDE) {
			setExtra(equipment.getAttr().getFortitude());
		}
	}
}
