package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import model.EquipmemtModel;
import javax.swing.JScrollPane;

public class MainWindow {

	private static final int TITLE_HEIGHT = 35;

	private JFrame frame;
	private JTextField floorInput;
	private JTextField ceilInput;

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

		frame = new JFrame();
		frame.setTitle("配装模拟器");
		frame.setBounds(100, 100, 1280, 960);
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

		JLabel criticalHitNumLabel = new JLabel("暴击（值）");
		criticalHitPanel.add(criticalHitNumLabel);

		JLabel criticalHitThresholdLabel = new JLabel("当前阈值");
		criticalHitPanel.add(criticalHitThresholdLabel);

		JLabel criticalHitThresholdNumLabel = new JLabel("当前阈值（值）");
		criticalHitPanel.add(criticalHitThresholdNumLabel);

		JLabel criticalHitRateLabel = new JLabel("暴击率");
		criticalHitPanel.add(criticalHitRateLabel);

		JLabel criticalHitRateNumLabel = new JLabel("暴击率（值）");
		criticalHitPanel.add(criticalHitRateNumLabel);

		JLabel nextCriticalHitThresholdLabel = new JLabel("下一档阈值");
		criticalHitPanel.add(nextCriticalHitThresholdLabel);

		JLabel nextCriticalHitThresholdNumLabel = new JLabel("下一档阈值（值）");
		criticalHitPanel.add(nextCriticalHitThresholdNumLabel);

		JLabel nextCriticalHitRateLabel = new JLabel("暴击率");
		criticalHitPanel.add(nextCriticalHitRateLabel);

		JLabel nextCriticalHitRateNumLabel = new JLabel("暴击率（值）");
		criticalHitPanel.add(nextCriticalHitRateNumLabel);

		JPanel directHitPanel = new BorderPanel();
		attrPanel.add(directHitPanel);
		directHitPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel directHitLabel = new JLabel("直击");
		directHitPanel.add(directHitLabel);

		JLabel directHitNumLabel = new JLabel("直击（值）");
		directHitPanel.add(directHitNumLabel);

		JLabel directHitThresholdLabel = new JLabel("当前阈值");
		directHitPanel.add(directHitThresholdLabel);

		JLabel directHitThresholdNumLabel = new JLabel("当前阈值（值）");
		directHitPanel.add(directHitThresholdNumLabel);

		JLabel directHitRateLabel = new JLabel("直击率");
		directHitPanel.add(directHitRateLabel);

		JLabel directHitRateNumLabel = new JLabel("直击率（值）");
		directHitPanel.add(directHitRateNumLabel);

		JLabel nextDirectHitThresholdLabel = new JLabel("下一档阈值");
		directHitPanel.add(nextDirectHitThresholdLabel);

		JLabel nextDirectHitThresholdNumLabel = new JLabel("下一档阈值（值）");
		directHitPanel.add(nextDirectHitThresholdNumLabel);

		JLabel nextDirectHitRateLabel = new JLabel("直击率");
		directHitPanel.add(nextDirectHitRateLabel);

		JLabel nextDirectHitRateNumLabel = new JLabel("直击率（值）");
		directHitPanel.add(nextDirectHitRateNumLabel);

		JPanel determinationPanel = new BorderPanel();
		attrPanel.add(determinationPanel);
		determinationPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel determinationLabel = new JLabel("信念");
		determinationPanel.add(determinationLabel);

		JLabel determinationNumLabel = new JLabel("信念（值）");
		determinationPanel.add(determinationNumLabel);

		JLabel determinationThresholdLabel = new JLabel("当前阈值");
		determinationPanel.add(determinationThresholdLabel);

		JLabel determinationThresholdNumLabel = new JLabel("当前阈值（值）");
		determinationPanel.add(determinationThresholdNumLabel);

		JLabel determinationRateLabel = new JLabel("增伤比");
		determinationPanel.add(determinationRateLabel);

		JLabel determinationRateNumLabel = new JLabel("增伤比（值）");
		determinationPanel.add(determinationRateNumLabel);

		JLabel nextDeterminationThresholdLabel = new JLabel("下一档阈值");
		determinationPanel.add(nextDeterminationThresholdLabel);

		JLabel nextDeterminationThresholdNumLabel = new JLabel("下一档阈值（值）");
		determinationPanel.add(nextDeterminationThresholdNumLabel);

		JLabel nextDeterminationHitRateLabel = new JLabel("增伤比");
		determinationPanel.add(nextDeterminationHitRateLabel);

		JLabel nextDeterminationRateNumLabel = new JLabel("增伤比（值）");
		determinationPanel.add(nextDeterminationRateNumLabel);

		JPanel speedPanel = new BorderPanel();
		attrPanel.add(speedPanel);
		speedPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel speedLabel = new JLabel("技能速度");
		speedPanel.add(speedLabel);

		JLabel speedNumLabel = new JLabel("技能速度（值）");
		speedPanel.add(speedNumLabel);

		JLabel speedThresholdLabel = new JLabel("当前阈值");
		speedPanel.add(speedThresholdLabel);

		JLabel speedThresholdNumLabel = new JLabel("当前阈值（值）");
		speedPanel.add(speedThresholdNumLabel);

		JLabel gcdLabel = new JLabel("复唱时间");
		speedPanel.add(gcdLabel);

		JLabel gcdNumLabel = new JLabel("复唱时间（值）");
		speedPanel.add(gcdNumLabel);

		JLabel nextSpeedThresholdLabel = new JLabel("下一档阈值");
		speedPanel.add(nextSpeedThresholdLabel);

		JLabel nextSpeedThresholdNumLabel = new JLabel("下一档阈值（值）");
		speedPanel.add(nextSpeedThresholdNumLabel);

		JLabel nextGcdLabel = new JLabel("复唱时间");
		speedPanel.add(nextGcdLabel);

		JLabel nextGcdNumLabel = new JLabel("复唱时间（值）");
		speedPanel.add(nextGcdNumLabel);

		JPanel extraPanel = new BorderPanel();
		attrPanel.add(extraPanel);
		extraPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel extraLabel = new JLabel("信仰");
		extraPanel.add(extraLabel);

		JLabel extraNumLabel = new JLabel("信仰（值）");
		extraPanel.add(extraNumLabel);

		JLabel extraThresholdLabel = new JLabel("当前阈值");
		extraPanel.add(extraThresholdLabel);

		JLabel extraThresholdNumLabel = new JLabel("当前阈值（值）");
		extraPanel.add(extraThresholdNumLabel);

		JLabel extraRateLabel = new JLabel("每3秒回蓝");
		extraPanel.add(extraRateLabel);

		JLabel extraRateNumLabel = new JLabel("每3秒回蓝（值）");
		extraPanel.add(extraRateNumLabel);

		JLabel nextExtraThresholdLabel = new JLabel("下一档阈值");
		extraPanel.add(nextExtraThresholdLabel);

		JLabel nextExtraThresholdNumLabel = new JLabel("下一档阈值（值）");
		extraPanel.add(nextExtraThresholdNumLabel);

		JLabel nextExtraRateLabel = new JLabel("每3秒回蓝");
		extraPanel.add(nextExtraRateLabel);

		JLabel nextExtraRateNumLabel = new JLabel("每3秒回蓝（值）");
		extraPanel.add(nextExtraRateNumLabel);

		JPanel filterPanel = new JPanel();
		filterPanel.setBounds(10, 10, frame.getWidth() - 300 - 10, 50);
		frame.getContentPane().add(filterPanel);
		filterPanel.setLayout(null);

		JComboBox<String> jobComboBox = new JComboBox<>();
		for (String job : EquipmemtModel.Job_LIST) {
			jobComboBox.addItem(job);
		}
		jobComboBox.setBounds(59, 13, 107, 24);
		filterPanel.add(jobComboBox);

		JLabel jobLabel = new JLabel("职业：");
		jobLabel.setBounds(14, 16, 72, 18);
		filterPanel.add(jobLabel);

		floorInput = new JTextField();
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
			}
		});
		filterBtn.setBounds(379, 12, 113, 27);
		filterPanel.add(filterBtn);

		JPanel equipmentPanel = new JPanel();
		equipmentPanel.setBounds(10, 60, frame.getWidth() - 300 - 10, frame.getHeight() - 60 - TITLE_HEIGHT);
		frame.getContentPane().add(equipmentPanel);
		equipmentPanel.setLayout(null);

		JPanel headPanel = new JPanel();
		headPanel.setBounds(0, 0, equipmentPanel.getWidth(), 30);
		equipmentPanel.add(headPanel);
		headPanel.setLayout(null);

		int offsetX = 0;
		JLabel headNameLabel = new BorderLabel("名称");//最长的名字：レプリカ・スカイラット・ストライカーフィンガレスグローブ
		headNameLabel.setHorizontalAlignment(SwingConstants.CENTER);//具体的装备界面需要更改为left
		headNameLabel.setBounds(0, 0, 500, 30);
		headPanel.add(headNameLabel);
		offsetX += headNameLabel.getWidth();

//		JLabel headItemLevelLabel = new JLabel("level.505");
//		headItemLevelLabel.setBounds(headNameLabel.getWidth() - 80, 0, 80, 30);
//		headPanel.add(headItemLevelLabel);

		final int remainWidth = headPanel.getWidth() - offsetX - 10;
		final int remainBlock = 6;
		
		JLabel HeadMateriaLabel = new BorderLabel("魔晶石");
		HeadMateriaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HeadMateriaLabel.setBounds(offsetX, 0, remainWidth / remainBlock, 30);
		headPanel.add(HeadMateriaLabel);
		offsetX += remainWidth / remainBlock;
		
		JLabel HeadCriticalHitLabel = new BorderLabel("暴击");
		HeadCriticalHitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HeadCriticalHitLabel.setBounds(offsetX, 0, remainWidth / remainBlock, 30);
		headPanel.add(HeadCriticalHitLabel);
		offsetX += remainWidth / remainBlock;

		JLabel headDirectHitLabel = new BorderLabel("直击");
		headDirectHitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headDirectHitLabel.setBounds(offsetX, 0, remainWidth / remainBlock, 30);
		headPanel.add(headDirectHitLabel);
		offsetX += remainWidth / remainBlock;

		JLabel headDeterminationLabel = new BorderLabel("信念");
		headDeterminationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headDeterminationLabel.setBounds(offsetX, 0, remainWidth / remainBlock, 30);
		headPanel.add(headDeterminationLabel);
		offsetX += remainWidth / remainBlock;

		JLabel headSpeedLabel = new BorderLabel("咏唱速度");
		headSpeedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headSpeedLabel.setBounds(offsetX, 0, remainWidth / remainBlock, 30);
		headPanel.add(headSpeedLabel);
		offsetX += remainWidth / remainBlock;

		JLabel headExtraLabel = new BorderLabel("信仰");
		headExtraLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headExtraLabel.setBounds(offsetX, 0, remainWidth / remainBlock, 30);
		headPanel.add(headExtraLabel);
		offsetX += remainWidth / remainBlock;
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
}
