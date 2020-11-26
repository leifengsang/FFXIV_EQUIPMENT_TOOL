package gui;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import meta.Equipment;
import meta.Job;

public class EquipmentPanel extends JPanel {

	private Job job;
	private Equipment equipment;

	private JRadioButton nameBtn;
	private JLabel materiaLabel;
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

	public EquipmentPanel(Job job, Equipment equipment, boolean hasExtra) {
		super();
		this.job = job;
		this.equipment = equipment;
		init(hasExtra);
	}

	private void init(boolean hasExtra) {
		this.setSize(970, 30);
		this.setLayout(null);

		int offsetX = 0;
		nameBtn = new JRadioButton(String.format("[%d]", equipment.getLevel()) + equipment.getName());
		nameBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nameBtn.setBounds(offsetX, 0, MainWindow.HEAD_NAME_WIDTH, this.getHeight());
		nameBtn.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getSource() == nameBtn && nameBtn.isSelected()) {
					job.equip(equipment);
				}
			}
		});
		;
		this.add(nameBtn);
		offsetX += MainWindow.HEAD_NAME_WIDTH;

		final int remainWidth = this.getWidth() - offsetX - 14;
		final int remainBlock = 6;
		final int singleWidth = remainWidth / remainBlock;

		materiaLabel = new BorderLabel("点击查看");
		materiaLabel.setBounds(offsetX, 0, singleWidth, this.getHeight());
		materiaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(materiaLabel);
		offsetX += singleWidth;

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

		if (hasExtra) {
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
		setSpeed(equipment.getSpeed());
		setExtra(equipment.getExtra());
	}
}
