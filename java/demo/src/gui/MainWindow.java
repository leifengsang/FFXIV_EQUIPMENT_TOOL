package gui;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow {

	private JFrame frame;

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
		frame = new JFrame();
		frame.setTitle("配装模拟器");
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel attrPanel = new JPanel();
		attrPanel.setBounds(708, 10, 300, 718);
		frame.getContentPane().add(attrPanel);
		attrPanel.setLayout(new GridLayout(5, 1, 0, 0));
		
		JPanel criticalHitPanel = new JPanel();
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
		
		JPanel directHitPanel = new JPanel();
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
		
		JPanel determinationPanel = new JPanel();
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
		
		JPanel speedPanel = new JPanel();
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
		
		JPanel extraPanel = new JPanel();
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
		
		JLabel extraRateLabel = new JLabel("每3秒的自然恢复MP量");
		extraPanel.add(extraRateLabel);
		
		JLabel extraRateNumLabel = new JLabel("中每3秒的自然恢复MP量（值）");
		extraPanel.add(extraRateNumLabel);
		
		JLabel nextExtraThresholdLabel = new JLabel("下一档阈值");
		extraPanel.add(nextExtraThresholdLabel);
		
		JLabel nextExtraThresholdNumLabel = new JLabel("下一档阈值（值）");
		extraPanel.add(nextExtraThresholdNumLabel);
		
		JLabel nextExtraRateLabel = new JLabel("每3秒的自然恢复MP量");
		extraPanel.add(nextExtraRateLabel);
		
		JLabel nextExtraRateNumLabel = new JLabel("中每3秒的自然恢复MP量（值）");
		extraPanel.add(nextExtraRateNumLabel);
		
		JPanel filterPanel = new JPanel();
		filterPanel.setBounds(10, 10, 699, 53);
		frame.getContentPane().add(filterPanel);
		
		JPanel equipmentPanel = new JPanel();
		equipmentPanel.setBounds(10, 63, 699, 665);
		frame.getContentPane().add(equipmentPanel);
	}
}
