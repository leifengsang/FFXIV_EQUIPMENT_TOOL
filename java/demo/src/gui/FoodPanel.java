package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import meta.Food;
import meta.Job;

@SuppressWarnings("serial")
public class FoodPanel extends JPanel {
	private Food food;

	private int damageType;
	private int extraType;

	private JRadioButton nameBtn;
	private JLabel criticalHitLabel;
	private JLabel directHitLabel;
	private JLabel determinationLabel;
	private JLabel speedLabel;
	private JLabel extraLabel;

	public Food getFood() {
		return food;
	}

	public JRadioButton getNameBtn() {
		return nameBtn;
	}

	public FoodPanel(Food food, int damageType, int extraType) {
		super();
		this.food = food;
		this.damageType = damageType;
		this.extraType = extraType;
		init();
	}

	private void init() {
		this.setSize(970, 30);
		this.setLayout(null);

		int offsetX = 0;
		nameBtn = new JRadioButton(food.getName());
		nameBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nameBtn.setBounds(offsetX, 0, MainWindow.HEAD_NAME_WIDTH, this.getHeight());
		this.add(nameBtn);
		offsetX += MainWindow.HEAD_NAME_WIDTH;
		offsetX += MainWindow.HEAD_MATERIA_WIDTH;

		final int remainWidth = this.getWidth() - offsetX - 14;
		final int remainBlock = 5;
		final int singleWidth = remainWidth / remainBlock;
		criticalHitLabel = new BorderLabel();
		criticalHitLabel.setBounds(offsetX, 0, singleWidth, this.getHeight());
		criticalHitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		criticalHitLabel.setText("0");
		this.add(criticalHitLabel);
		offsetX += singleWidth;

		directHitLabel = new BorderLabel();
		directHitLabel.setBounds(offsetX, 0, singleWidth, this.getHeight());
		directHitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		directHitLabel.setText("0");
		this.add(directHitLabel);
		offsetX += singleWidth;

		determinationLabel = new BorderLabel();
		determinationLabel.setBounds(offsetX, 0, singleWidth, this.getHeight());
		determinationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		determinationLabel.setText("0");
		this.add(determinationLabel);
		offsetX += singleWidth;

		speedLabel = new BorderLabel();
		speedLabel.setBounds(offsetX, 0, singleWidth, this.getHeight());
		speedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		speedLabel.setText("0");
		this.add(speedLabel);
		offsetX += singleWidth;

		if (extraType != Job.EXTRA_ATTR_TYPE_NULL) {
			extraLabel = new BorderLabel();
			extraLabel.setBounds(offsetX, 0, singleWidth, this.getHeight());
			extraLabel.setHorizontalAlignment(SwingConstants.CENTER);
			extraLabel.setText("0");
			this.add(extraLabel);
			offsetX += singleWidth;
		}

		setFoodAttr();
	}

	private void setFoodAttr() {
		setAttr(food.getAttrType1(), food.getValue1(), food.getLimit1());
		setAttr(food.getAttrType2(), food.getValue2(), food.getLimit2());
	}

	private void setAttr(String type, int value, int limit) {
		if (type.equals("")) {
			return;
		}

		String text = "+" + String.valueOf(value);
		if (limit != -1) {
			text += "%" + String.format("[%d]", limit);
		}

		JLabel label = null;
		switch (type) {
		case "criticalHit":
			label = criticalHitLabel;
			break;
		case "directHit":
			label = directHitLabel;
			break;
		case "determination":
			label = determinationLabel;
			break;
		case "faith":
			if (extraType == Job.EXTRA_ATTR_TYPE_FAITH) {
				label = extraLabel;
			}
			break;
		case "fortitude":
			if (extraType == Job.EXTRA_ATTR_TYPE_FORTITUDE) {
				label = extraLabel;
			}
			break;
		case "skillSpeed":
			if (damageType == Job.DAMAGE_TYPE_PHYSICAL) {
				label = speedLabel;
			}
			break;
		case "spellSpeed":
			if (damageType == Job.DAMAGE_TYPE_MAGIC) {
				label = speedLabel;
			}
			break;
		default:
			throw new Error("attr type error!");
		}
		if (label != null) {
			label.setText(text);
		}
	}
}
