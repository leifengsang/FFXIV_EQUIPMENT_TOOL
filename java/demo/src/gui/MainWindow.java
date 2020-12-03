package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import meta.Attr;
import meta.Equipment;
import meta.Job;
import model.EquipmentModel;

public class MainWindow {

	private static final int TITLE_HEIGHT = 35;
	public static final int HEAD_NAME_WIDTH = 450;
	public static final int HEAD_MATERIA_WIDTH = 100;

	private Map<Integer, List<Equipment>> equipmentMap = new HashMap<Integer, List<Equipment>>();
	private Job currentJob;
	private int offsetY = 0;
	private static final int SINGLE_PART_HEIGHT = 20;

	private JFrame frame;
	private JTextField floorInput;
	private JTextField ceilInput;

	private JPanel equipmentPanel;

	private JLabel criticalHitNumLabel;
	private JLabel criticalHitThresholdNumLabel;
	private JLabel criticalHitRateNumLabel;
	private JLabel nextCriticalHitThresholdNumLabel;
	private JLabel nextCriticalHitRateNumLabel;

	private JLabel directHitNumLabel;
	private JLabel directHitThresholdNumLabel;
	private JLabel directHitRateNumLabel;
	private JLabel nextDirectHitThresholdNumLabel;
	private JLabel nextDirectHitRateNumLabel;

	private JLabel determinationNumLabel;
	private JLabel determinationThresholdNumLabel;
	private JLabel determinationRateNumLabel;
	private JLabel nextDeterminationThresholdNumLabel;
	private JLabel nextDeterminationRateNumLabel;

	private JLabel speedLabel;
	private JLabel speedNumLabel;
	private JLabel speedThresholdNumLabel;
	private JLabel gcdNumLabel;
	private JLabel nextSpeedThresholdNumLabel;
	private JLabel nextGcdNumLabel;

	private JPanel extraPanel;
	private JLabel extraLabel;
	private JLabel extraNumLabel;
	private JLabel extraThresholdNumLabel;
	private JLabel extraRateLabel;
	private JLabel extraRateNumLabel;
	private JLabel nextExtraThresholdNumLabel;
	private JLabel nextExtraRateLabel;
	private JLabel nextExtraRateNumLabel;

	private JLabel headSpeedLabel;
	private JLabel headExtraLabel;

	private int screenWidth;
	private int screenHeight;

	private JScrollPane mainPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		initGlobal(new Font("alias", Font.PLAIN, 14));

		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screensize.getWidth();
		screenHeight = (int) screensize.getHeight();
		frame = new JFrame();
		frame.setTitle("配装模拟器");
		frame.setBounds((screenWidth - 1280) / 2, (screenHeight - 960) / 2, 1280, 960);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		JPanel attrPanel = new JPanel();
		attrPanel.setBounds(frame.getWidth() - 300, 10, 300, frame.getHeight() - 10 - TITLE_HEIGHT);
		frame.getContentPane().add(attrPanel);
		attrPanel.setLayout(new GridLayout(5, 1, 0, 0));

		JPanel criticalHitPanel = new BorderPanel();
		attrPanel.add(criticalHitPanel);
		criticalHitPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel criticalHitLabel = new JLabel("暴击  ");
		criticalHitPanel.add(criticalHitLabel);

		criticalHitNumLabel = new JLabel("0");
		criticalHitPanel.add(criticalHitNumLabel);

		JLabel criticalHitThresholdLabel = new JLabel("当前阈值");
		criticalHitPanel.add(criticalHitThresholdLabel);

		criticalHitThresholdNumLabel = new JLabel("0");
		criticalHitPanel.add(criticalHitThresholdNumLabel);

		JLabel criticalHitRateLabel = new JLabel("暴击率");
		criticalHitPanel.add(criticalHitRateLabel);

		criticalHitRateNumLabel = new JLabel("0");
		criticalHitPanel.add(criticalHitRateNumLabel);

		JLabel nextCriticalHitThresholdLabel = new JLabel("下一档阈值");
		criticalHitPanel.add(nextCriticalHitThresholdLabel);

		nextCriticalHitThresholdNumLabel = new JLabel("0");
		criticalHitPanel.add(nextCriticalHitThresholdNumLabel);

		JLabel nextCriticalHitThresholdNumLabel = new JLabel("暴击率");
		criticalHitPanel.add(nextCriticalHitThresholdNumLabel);

		nextCriticalHitRateNumLabel = new JLabel("0");
		criticalHitPanel.add(nextCriticalHitRateNumLabel);

		JPanel directHitPanel = new BorderPanel();
		attrPanel.add(directHitPanel);
		directHitPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel directHitLabel = new JLabel("直击");
		directHitPanel.add(directHitLabel);

		directHitNumLabel = new JLabel("0");
		directHitPanel.add(directHitNumLabel);

		JLabel directHitThresholdLabel = new JLabel("当前阈值");
		directHitPanel.add(directHitThresholdLabel);

		directHitThresholdNumLabel = new JLabel("0");
		directHitPanel.add(directHitThresholdNumLabel);

		JLabel directHitRateLabel = new JLabel("直击率");
		directHitPanel.add(directHitRateLabel);

		directHitRateNumLabel = new JLabel("0");
		directHitPanel.add(directHitRateNumLabel);

		JLabel nextDirectHitThresholdLabel = new JLabel("下一档阈值");
		directHitPanel.add(nextDirectHitThresholdLabel);

		nextDirectHitThresholdNumLabel = new JLabel("0");
		directHitPanel.add(nextDirectHitThresholdNumLabel);

		JLabel nextDirectHitRateLabel = new JLabel("直击率");
		directHitPanel.add(nextDirectHitRateLabel);

		nextDirectHitRateNumLabel = new JLabel("0");
		directHitPanel.add(nextDirectHitRateNumLabel);

		JPanel determinationPanel = new BorderPanel();
		attrPanel.add(determinationPanel);
		determinationPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel determinationLabel = new JLabel("信念");
		determinationPanel.add(determinationLabel);

		determinationNumLabel = new JLabel("0");
		determinationPanel.add(determinationNumLabel);

		JLabel determinationThresholdLabel = new JLabel("当前阈值");
		determinationPanel.add(determinationThresholdLabel);

		determinationThresholdNumLabel = new JLabel("0");
		determinationPanel.add(determinationThresholdNumLabel);

		JLabel determinationRateLabel = new JLabel("增伤比");
		determinationPanel.add(determinationRateLabel);

		determinationRateNumLabel = new JLabel("0");
		determinationPanel.add(determinationRateNumLabel);

		JLabel nextDeterminationThresholdLabel = new JLabel("下一档阈值");
		determinationPanel.add(nextDeterminationThresholdLabel);

		nextDeterminationThresholdNumLabel = new JLabel("0");
		determinationPanel.add(nextDeterminationThresholdNumLabel);

		JLabel nextDeterminationHitRateLabel = new JLabel("增伤比");
		determinationPanel.add(nextDeterminationHitRateLabel);

		nextDeterminationRateNumLabel = new JLabel("0");
		determinationPanel.add(nextDeterminationRateNumLabel);

		JPanel speedPanel = new BorderPanel();
		attrPanel.add(speedPanel);
		speedPanel.setLayout(new GridLayout(0, 2, 0, 0));

		speedLabel = new JLabel("技能速度");
		speedPanel.add(speedLabel);

		speedNumLabel = new JLabel("0");
		speedPanel.add(speedNumLabel);

		JLabel speedThresholdLabel = new JLabel("当前阈值");
		speedPanel.add(speedThresholdLabel);

		speedThresholdNumLabel = new JLabel("0");
		speedPanel.add(speedThresholdNumLabel);

		JLabel gcdLabel = new JLabel("复唱时间");
		speedPanel.add(gcdLabel);

		gcdNumLabel = new JLabel("0");
		speedPanel.add(gcdNumLabel);

		JLabel nextSpeedThresholdLabel = new JLabel("下一档阈值");
		speedPanel.add(nextSpeedThresholdLabel);

		nextSpeedThresholdNumLabel = new JLabel("0");
		speedPanel.add(nextSpeedThresholdNumLabel);

		JLabel nextGcdLabel = new JLabel("复唱时间");
		speedPanel.add(nextGcdLabel);

		nextGcdNumLabel = new JLabel("0");
		speedPanel.add(nextGcdNumLabel);

		extraPanel = new BorderPanel();
		attrPanel.add(extraPanel);
		extraPanel.setLayout(new GridLayout(0, 2, 0, 0));

		extraLabel = new JLabel("信仰");
		extraPanel.add(extraLabel);

		extraNumLabel = new JLabel("0");
		extraPanel.add(extraNumLabel);

		JLabel extraThresholdLabel = new JLabel("当前阈值");
		extraPanel.add(extraThresholdLabel);

		extraThresholdNumLabel = new JLabel("0");
		extraPanel.add(extraThresholdNumLabel);

		extraRateLabel = new JLabel("每3秒回蓝");
		extraPanel.add(extraRateLabel);

		extraRateNumLabel = new JLabel("0");
		extraPanel.add(extraRateNumLabel);

		JLabel nextExtraThresholdLabel = new JLabel("下一档阈值");
		extraPanel.add(nextExtraThresholdLabel);

		nextExtraThresholdNumLabel = new JLabel("0");
		extraPanel.add(nextExtraThresholdNumLabel);

		nextExtraRateLabel = new JLabel("每3秒回蓝");
		extraPanel.add(nextExtraRateLabel);

		nextExtraRateNumLabel = new JLabel("0");
		extraPanel.add(nextExtraRateNumLabel);

		JPanel filterPanel = new JPanel();
		filterPanel.setBounds(10, 10, frame.getWidth() - 300 - 10, 50);
		frame.getContentPane().add(filterPanel);
		filterPanel.setLayout(null);

		JComboBox<String> jobComboBox = new JComboBox<>();
		for (String job : EquipmentModel.Job_LIST) {
			jobComboBox.addItem(job);
		}
		jobComboBox.setBounds(59, 13, 107, 24);
		filterPanel.add(jobComboBox);

		JLabel jobLabel = new JLabel("职业：");
		jobLabel.setBounds(14, 16, 72, 18);
		filterPanel.add(jobLabel);

		floorInput = new JTextField();
		floorInput.setText("500");
		floorInput.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (floorInput.getText().length() > 3) {
					e.consume();
				}
				char ch = e.getKeyChar();
				if (!(ch >= '0' && ch <= '9')) {
					e.consume();
				}
			}
		});

		JLabel itemLevelLabel = new JLabel("装等：");
		itemLevelLabel.setBounds(190, 16, 45, 18);
		filterPanel.add(itemLevelLabel);
		floorInput.setBounds(235, 13, 50, 24);
		filterPanel.add(floorInput);
		floorInput.setColumns(10);

		JLabel splitLabel = new JLabel("-");
		splitLabel.setBounds(289, 16, 9, 18);
		filterPanel.add(splitLabel);

		ceilInput = new JTextField();
		ceilInput.setText("505");
		ceilInput.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (ceilInput.getText().length() > 3) {
					e.consume();
				}
				char ch = e.getKeyChar();
				if (!(ch >= '0' && ch <= '9')) {
					e.consume();
				}
			}
		});
		ceilInput.setBounds(300, 13, 50, 24);
		filterPanel.add(ceilInput);
		ceilInput.setColumns(10);

		JButton filterBtn = new JButton("筛选");
		filterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int floor = Integer.valueOf(floorInput.getText());
					int ceil = Integer.valueOf(ceilInput.getText());
					if (floor > ceil) {
						JOptionPane.showMessageDialog(null, "输入错误", "输入错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "输入错误", "输入错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				doFilter((String) jobComboBox.getSelectedItem(), Integer.valueOf(floorInput.getText()),
						Integer.valueOf(ceilInput.getText()));
			}
		});
		filterBtn.setBounds(379, 12, 113, 27);
		filterPanel.add(filterBtn);

		JComboBox<String> languageComboBox = new JComboBox<>();
		languageComboBox.setBounds(filterPanel.getWidth() - 100 - 14, 13, 100, 24);
		filterPanel.add(languageComboBox);
		languageComboBox.addItem("日本語");
		languageComboBox.addItem("english");
		languageComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int language = 0;
				switch ((String) languageComboBox.getSelectedItem()) {
				case "日本語":
					language = EquipmentModel.LANG_JP;
					break;
				case "english":
					language = EquipmentModel.LANG_EN;
					break;
				default:
					return;
				}
				if (EquipmentModel.getInstance().getLanguage() != language) {
					EquipmentModel.getInstance().setLanguage(language);
					layoutEquipmentPanel();
				}
			}
		});

		JLabel languageLabel = new JLabel("语言：");
		languageLabel.setBounds(808, 16, 45, 18);
		filterPanel.add(languageLabel);

		JPanel simulatorPanel = new JPanel();
		simulatorPanel.setBounds(10, 60, frame.getWidth() - 300 - 10, frame.getHeight() - 60 - TITLE_HEIGHT);
		frame.getContentPane().add(simulatorPanel);
		simulatorPanel.setLayout(null);

		JPanel headPanel = new JPanel();
		headPanel.setBounds(0, 0, simulatorPanel.getWidth(), 30);
		simulatorPanel.add(headPanel);
		headPanel.setLayout(null);

		int offsetX = 0;
		/**
		 * 最长的名字
		 * レプリカ・スカイラット・ストライカーフィンガレスグローブ
		 * Augmented Neo-Ishgardian Leather Belt of Striking
		 */
		JLabel headNameLabel = new BorderLabel("名称");
		headNameLabel.setHorizontalAlignment(SwingConstants.CENTER);//具体的装备界面需要更改为left
		headNameLabel.setBounds(0, 0, HEAD_NAME_WIDTH, 30);
		headPanel.add(headNameLabel);
		offsetX += headNameLabel.getWidth();

		JLabel HeadMateriaLabel = new BorderLabel("魔晶石");
		HeadMateriaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HeadMateriaLabel.setBounds(offsetX, 0, HEAD_MATERIA_WIDTH, 30);
		headPanel.add(HeadMateriaLabel);
		offsetX += HEAD_MATERIA_WIDTH;

		final int remainWidth = headPanel.getWidth() - offsetX - 14;
		final int remainBlock = 5;
		final int singleWidth = remainWidth / remainBlock;
		JLabel HeadCriticalHitLabel = new BorderLabel("暴击");
		HeadCriticalHitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HeadCriticalHitLabel.setBounds(offsetX, 0, singleWidth, 30);
		headPanel.add(HeadCriticalHitLabel);
		offsetX += singleWidth;

		JLabel headDirectHitLabel = new BorderLabel("直击");
		headDirectHitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headDirectHitLabel.setBounds(offsetX, 0, singleWidth, 30);
		headPanel.add(headDirectHitLabel);
		offsetX += singleWidth;

		JLabel headDeterminationLabel = new BorderLabel("信念");
		headDeterminationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headDeterminationLabel.setBounds(offsetX, 0, singleWidth, 30);
		headPanel.add(headDeterminationLabel);
		offsetX += singleWidth;

		headSpeedLabel = new BorderLabel("咏唱速度");
		headSpeedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headSpeedLabel.setBounds(offsetX, 0, singleWidth, 30);
		headPanel.add(headSpeedLabel);
		offsetX += singleWidth;

		headExtraLabel = new BorderLabel("信仰");
		headExtraLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headExtraLabel.setBounds(offsetX, 0, singleWidth, 30);
		headPanel.add(headExtraLabel);
		offsetX += singleWidth;

		equipmentPanel = new JPanel();
		equipmentPanel.setBackground(new Color(204, 232, 207));
		equipmentPanel.setBounds(0, headPanel.getHeight(), simulatorPanel.getWidth(), 0);
		equipmentPanel.setLayout(null);

		doFilter((String) jobComboBox.getSelectedItem(), Integer.parseInt(floorInput.getText()),
				Integer.parseInt(ceilInput.getText()));

		mainPanel = new JScrollPane(equipmentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.setBounds(0, headPanel.getHeight(), simulatorPanel.getWidth(),
				simulatorPanel.getHeight() - headPanel.getHeight());
		mainPanel.getVerticalScrollBar().setUnitIncrement(10);
		simulatorPanel.add(mainPanel);
	}

	private void initGlobal(Font font) {
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		Object key = null;
		Object value = null;
		while (keys.hasMoreElements()) {
			key = keys.nextElement();
			value = UIManager.get(key);
			if (key instanceof String) {
				/** 设置全局的背景色 */
				if (((String) key).endsWith(".background")) {
					UIManager.put(key, new Color(204, 232, 207));
				}
			}

			/** 设置全局的字体 */
			if (value instanceof Font) {
				UIManager.put(key, font);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void doFilter(String job, int floor, int ceil) {
		initEquipment();
		Class<? extends Job> clazz = null;
		try {
			clazz = (Class<? extends Job>) Class.forName("meta." + job);
			currentJob = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Equipment> equipmentList = EquipmentModel.getInstance().getEquipmentByLevelAndJob(clazz, floor, ceil);
		for (Equipment equipment : equipmentList) {
			equipmentMap.get(equipment.getPosition()).add(equipment);
		}
		layoutAttrPanel();
		calAttr();
		layoutEquipmentPanel();
	}

	private void layoutAttrPanel() {
		//设置技速/唱速
		if (currentJob.getDamageType() == Job.DAMAGE_TYPE_PHYSICAL) {
			speedLabel.setText("技能速度");
			headSpeedLabel.setText("技能速度");
		} else {
			speedLabel.setText("咏唱速度");
			headSpeedLabel.setText("咏唱速度");
		}

		//设置额外属性
		switch (currentJob.getExtraAttrType()) {
		case Job.EXTRA_ATTR_TYPE_FAITH:
			extraPanel.setVisible(true);
			headExtraLabel.setVisible(true);
			extraLabel.setText("信仰");
			extraRateLabel.setText("每三秒回蓝");
			nextExtraRateLabel.setText("每三秒回蓝");
			headExtraLabel.setText("信仰");
			break;
		case Job.EXTRA_ATTR_TYPE_FORTITUDE:
			extraPanel.setVisible(true);
			headExtraLabel.setVisible(true);
			extraLabel.setText("坚韧");
			extraRateLabel.setText("增减伤比");
			nextExtraRateLabel.setText("增减伤比");
			headExtraLabel.setText("坚韧");
			break;
		default:
			extraPanel.setVisible(false);
			headExtraLabel.setVisible(false);
		}
	}

	private void calAttr() {
		Attr attr = currentJob.getAttr();
		criticalHitNumLabel.setText(String.valueOf(attr.getCriticalHit()));
		int criticalHitThreshold = EquipmentModel.getInstance().getCurrentThreshold(attr.getCriticalHit(),
				"criticalHit");
		criticalHitThresholdNumLabel.setText(String.valueOf(criticalHitThreshold));
		criticalHitRateNumLabel.setText(Attr.getCriticalHitRateByThreshold(criticalHitThreshold));
		int nextCriticalHitThreshold = EquipmentModel.getInstance().getNextThreshold(attr.getCriticalHit(),
				"criticalHit");
		if (nextCriticalHitThreshold == -1) {
			nextCriticalHitThresholdNumLabel.setText("无下一档数据");
			nextCriticalHitRateNumLabel.setText("无下一档数据");
		} else {
			nextCriticalHitThresholdNumLabel.setText(String.valueOf(nextCriticalHitThreshold));
			nextCriticalHitRateNumLabel.setText(Attr.getCriticalHitRateByThreshold(nextCriticalHitThreshold));
		}

		directHitNumLabel.setText(String.valueOf(attr.getDirectHit()));
		int directHitThreshold = EquipmentModel.getInstance().getCurrentThreshold(attr.getDirectHit(), "directHit");
		directHitThresholdNumLabel.setText(String.valueOf(directHitThreshold));
		directHitRateNumLabel.setText(Attr.getDirectHitRateByThreshold(directHitThreshold));
		int nextDirectHitThreshold = EquipmentModel.getInstance().getNextThreshold(attr.getDirectHit(), "directHit");
		if (nextDirectHitThreshold == -1) {
			nextDirectHitThresholdNumLabel.setText("无下一档数据");
			nextDirectHitRateNumLabel.setText("无下一档数据");
		} else {
			nextDirectHitThresholdNumLabel.setText(String.valueOf(nextDirectHitThreshold));
			nextDirectHitRateNumLabel.setText(Attr.getDirectHitRateByThreshold(nextDirectHitThreshold));
		}

		determinationNumLabel.setText(String.valueOf(attr.getDetermination()));
		int determinationThreshold = EquipmentModel.getInstance().getCurrentThreshold(attr.getDetermination(),
				"determination");
		determinationThresholdNumLabel.setText(String.valueOf(determinationThreshold));
		determinationRateNumLabel.setText(Attr.getDeterminationRateByThreshold(determinationThreshold));
		int nextDeterminationThreshold = EquipmentModel.getInstance().getNextThreshold(attr.getDetermination(),
				"determination");
		if (nextDeterminationThreshold == -1) {
			nextDeterminationThresholdNumLabel.setText("无下一档数据");
			nextDeterminationRateNumLabel.setText("无下一档数据");
		} else {
			nextDeterminationThresholdNumLabel.setText(String.valueOf(nextDeterminationThreshold));
			nextDeterminationRateNumLabel.setText(Attr.getDeterminationRateByThreshold(nextDeterminationThreshold));
		}

		speedNumLabel.setText(String.valueOf(currentJob.getSpeed()));
		int speedThreshold = EquipmentModel.getInstance().getCurrentThreshold(currentJob.getSpeed(), "speed");
		speedThresholdNumLabel.setText(String.valueOf(speedThreshold));
		gcdNumLabel.setText(Attr.getGcdByThreshold(speedThreshold));
		int nextSpeedThreshold = EquipmentModel.getInstance().getNextThreshold(currentJob.getSpeed(), "speed");
		if (nextSpeedThreshold == -1) {
			nextSpeedThresholdNumLabel.setText("无下一档数据");
			nextGcdNumLabel.setText("无下一档数据");
		} else {
			nextSpeedThresholdNumLabel.setText(String.valueOf(nextSpeedThreshold));
			nextGcdNumLabel.setText(Attr.getGcdByThreshold(nextSpeedThreshold));
		}

		if (extraPanel.isVisible()) {
			extraNumLabel.setText(String.valueOf(currentJob.getExtraAttr()));
			int extraAttrType = currentJob.getExtraAttrType();
			if (extraAttrType == Job.EXTRA_ATTR_TYPE_FAITH) {
				int faithThreshold = EquipmentModel.getInstance().getCurrentThreshold(currentJob.getExtraAttr(),
						"faith");
				extraThresholdNumLabel.setText(String.valueOf(faithThreshold));
				extraRateNumLabel.setText(Attr.getRecoverByThreshold(faithThreshold));
				int nextfaithThreshold = EquipmentModel.getInstance().getNextThreshold(currentJob.getExtraAttr(),
						"faith");
				if (nextfaithThreshold == -1) {
					nextExtraThresholdNumLabel.setText("无下一档数据");
					nextExtraRateNumLabel.setText("无下一档数据");
				} else {
					nextExtraThresholdNumLabel.setText(String.valueOf(nextfaithThreshold));
					nextExtraRateNumLabel.setText(Attr.getRecoverByThreshold(nextfaithThreshold));
				}
			} else {
				int fortitudeThreshold = EquipmentModel.getInstance().getCurrentThreshold(currentJob.getExtraAttr(),
						"fortitude");
				extraThresholdNumLabel.setText(String.valueOf(fortitudeThreshold));
				extraRateNumLabel.setText(Attr.getFortitudeRateByThreshold(fortitudeThreshold));
				int nextFortitudeThreshold = EquipmentModel.getInstance().getNextThreshold(currentJob.getExtraAttr(),
						"fortitude");
				if (nextFortitudeThreshold == -1) {
					nextExtraThresholdNumLabel.setText("无下一档数据");
					nextExtraRateNumLabel.setText("无下一档数据");
				} else {
					nextExtraThresholdNumLabel.setText(String.valueOf(nextFortitudeThreshold));
					nextExtraRateNumLabel.setText(Attr.getFortitudeRateByThreshold(nextFortitudeThreshold));
				}
			}
		}
	}

	private void initEquipment() {
		for (int i = 1; i < Equipment.POS_LIMIT; i++) {
			List<Equipment> list = equipmentMap.get(i);
			if (list == null) {
				list = new ArrayList<>();
				equipmentMap.put(i, list);
			} else {
				list.clear();
			}
		}
	}

	private void layoutEquipmentPanel() {
		equipmentPanel.removeAll();
		offsetY = 0;
		layoutSinglePart("武器", 1);

		if (currentJob.isEnableSecondary()) {
			layoutSinglePart("副手", 2);
		}

		layoutSinglePart("头部", 3);

		layoutSinglePart("身体", 4);

		layoutSinglePart("手部", 5);

		layoutSinglePart("腰部", 6);

		layoutSinglePart("腿部", 7);

		layoutSinglePart("脚部", 8);

		layoutSinglePart("耳部", 9);

		layoutSinglePart("颈部", 10);

		layoutSinglePart("腕部", 11);

		layoutSinglePart("戒指1", 12);

		layoutSinglePart("戒指2", 12);

		equipmentPanel.setPreferredSize(new Dimension(equipmentPanel.getWidth(), offsetY));
		equipmentPanel.repaint();
		equipmentPanel.revalidate();
	}

	private void layoutSinglePart(String title, int position) {
		JLabel titleLabel = new BorderLabel(title);
		titleLabel.setBounds(0, offsetY, equipmentPanel.getWidth(), SINGLE_PART_HEIGHT);
		titleLabel.setFont(new Font("alias", Font.PLAIN, 16));
		equipmentPanel.add(titleLabel);
		offsetY += SINGLE_PART_HEIGHT;

		ButtonGroup group = new ButtonGroup();
		List<Equipment> list = equipmentMap.get(position);
		for (Equipment equipment : list) {
			EquipmentPanel ePanel = new EquipmentPanel(equipment, currentJob.getDamageType(),
					currentJob.getExtraAttrType());
			ePanel.setLocation(0, offsetY);
			equipmentPanel.add(ePanel);
			offsetY += ePanel.getHeight();

			JRadioButton radioBtn = ePanel.getNameBtn();
			radioBtn.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					if (radioBtn.isSelected()) {
						String name = ((JRadioButton) e.getSource()).getText();
						name = name.substring(name.indexOf(']') + 1);
						Equipment currentEquipment = EquipmentModel.getInstance().getEquipmentByName(name);
						String msg = currentJob.equip(currentEquipment);
						if (msg != null) {
							JOptionPane.showMessageDialog(null, msg, "装备时发生错误", JOptionPane.ERROR_MESSAGE);
							group.clearSelection();
							return;
						}
						calAttr();
					}
				}
			});

			JButton materiaBtn = ePanel.getMateriaBtn();
			materiaBtn.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					MateriaView materiaView = new MateriaView(MainWindow.this.frame, equipment);
					materiaView.addWindowListener(new WindowAdapter() {

						@Override
						public void windowClosed(WindowEvent e) {
							super.windowClosed(e);
							ePanel.refreshAttr();
							calAttr();
						}

					});
				}

			});

			group.add(radioBtn);
		}
	}
}
