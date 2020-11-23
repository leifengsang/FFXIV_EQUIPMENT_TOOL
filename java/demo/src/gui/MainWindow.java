package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;

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
		frame.setBounds(100, 100, 1500, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel attrPanel = new JPanel();
		attrPanel.setBounds(1161, 0, 333, 965);
		frame.getContentPane().add(attrPanel);
		attrPanel.setLayout(new GridLayout(5, 1, 0, 0));
		
		JPanel criticalHitPanel_1 = new JPanel();
		attrPanel.add(criticalHitPanel_1);
		criticalHitPanel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel criticalHitLabel_1 = new JLabel("暴击  ");
		criticalHitPanel_1.add(criticalHitLabel_1);
		
		JLabel criticalHitNumLabel_1 = new JLabel("暴击（值）");
		criticalHitPanel_1.add(criticalHitNumLabel_1);
		
		JLabel criticalHitThresholdLabel_1 = new JLabel("当前阈值");
		criticalHitPanel_1.add(criticalHitThresholdLabel_1);
		
		JLabel criticalHitThresholdLabelNum_1 = new JLabel("当前阈值（值）");
		criticalHitPanel_1.add(criticalHitThresholdLabelNum_1);
		
		JLabel criticalHitRate_1 = new JLabel("暴击率");
		criticalHitPanel_1.add(criticalHitRate_1);
		
		JLabel criticalHitRateNum_1 = new JLabel("暴击率（值）");
		criticalHitPanel_1.add(criticalHitRateNum_1);
		
		JLabel nextCriticalHitThresholdLabel_1 = new JLabel("下一档阈值");
		criticalHitPanel_1.add(nextCriticalHitThresholdLabel_1);
		
		JLabel nextCriticalHitThresholdLabelNum_1 = new JLabel("下一档阈值（值）");
		criticalHitPanel_1.add(nextCriticalHitThresholdLabelNum_1);
		
		JLabel nextCriticalHitRate_1 = new JLabel("暴击率");
		criticalHitPanel_1.add(nextCriticalHitRate_1);
		
		JLabel nextCriticalHitRateNum_1 = new JLabel("暴击率（值）");
		criticalHitPanel_1.add(nextCriticalHitRateNum_1);
		
		JPanel criticalHitPanel_2 = new JPanel();
		attrPanel.add(criticalHitPanel_2);
		criticalHitPanel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel criticalHitLabel_2 = new JLabel("暴击  ");
		criticalHitPanel_2.add(criticalHitLabel_2);
		
		JLabel criticalHitNumLabel_2 = new JLabel("暴击（值）");
		criticalHitPanel_2.add(criticalHitNumLabel_2);
		
		JLabel criticalHitThresholdLabel_2 = new JLabel("当前阈值");
		criticalHitPanel_2.add(criticalHitThresholdLabel_2);
		
		JLabel criticalHitThresholdLabelNum_2 = new JLabel("当前阈值（值）");
		criticalHitPanel_2.add(criticalHitThresholdLabelNum_2);
		
		JLabel criticalHitRate_2 = new JLabel("暴击率");
		criticalHitPanel_2.add(criticalHitRate_2);
		
		JLabel criticalHitRateNum_2 = new JLabel("暴击率（值）");
		criticalHitPanel_2.add(criticalHitRateNum_2);
		
		JLabel nextCriticalHitThresholdLabel_2 = new JLabel("下一档阈值");
		criticalHitPanel_2.add(nextCriticalHitThresholdLabel_2);
		
		JLabel nextCriticalHitThresholdLabelNum_2 = new JLabel("下一档阈值（值）");
		criticalHitPanel_2.add(nextCriticalHitThresholdLabelNum_2);
		
		JLabel nextCriticalHitRate_2 = new JLabel("暴击率");
		criticalHitPanel_2.add(nextCriticalHitRate_2);
		
		JLabel nextCriticalHitRateNum_2 = new JLabel("暴击率（值）");
		criticalHitPanel_2.add(nextCriticalHitRateNum_2);
		
		JPanel criticalHitPanel_3 = new JPanel();
		attrPanel.add(criticalHitPanel_3);
		criticalHitPanel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel criticalHitLabel_3 = new JLabel("暴击  ");
		criticalHitPanel_3.add(criticalHitLabel_3);
		
		JLabel criticalHitNumLabel_3 = new JLabel("暴击（值）");
		criticalHitPanel_3.add(criticalHitNumLabel_3);
		
		JLabel criticalHitThresholdLabel_3 = new JLabel("当前阈值");
		criticalHitPanel_3.add(criticalHitThresholdLabel_3);
		
		JLabel criticalHitThresholdLabelNum_3 = new JLabel("当前阈值（值）");
		criticalHitPanel_3.add(criticalHitThresholdLabelNum_3);
		
		JLabel criticalHitRate_3 = new JLabel("暴击率");
		criticalHitPanel_3.add(criticalHitRate_3);
		
		JLabel criticalHitRateNum_3 = new JLabel("暴击率（值）");
		criticalHitPanel_3.add(criticalHitRateNum_3);
		
		JLabel nextCriticalHitThresholdLabel_3 = new JLabel("下一档阈值");
		criticalHitPanel_3.add(nextCriticalHitThresholdLabel_3);
		
		JLabel nextCriticalHitThresholdLabelNum_3 = new JLabel("下一档阈值（值）");
		criticalHitPanel_3.add(nextCriticalHitThresholdLabelNum_3);
		
		JLabel nextCriticalHitRate_3 = new JLabel("暴击率");
		criticalHitPanel_3.add(nextCriticalHitRate_3);
		
		JLabel nextCriticalHitRateNum_3 = new JLabel("暴击率（值）");
		criticalHitPanel_3.add(nextCriticalHitRateNum_3);
		
		JPanel criticalHitPanel = new JPanel();
		attrPanel.add(criticalHitPanel);
		criticalHitPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel criticalHitLabel = new JLabel("暴击  ");
		criticalHitPanel.add(criticalHitLabel);
		
		JLabel criticalHitNumLabel = new JLabel("暴击（值）");
		criticalHitPanel.add(criticalHitNumLabel);
		
		JLabel criticalHitThresholdLabel = new JLabel("当前阈值");
		criticalHitPanel.add(criticalHitThresholdLabel);
		
		JLabel criticalHitThresholdLabelNum = new JLabel("当前阈值（值）");
		criticalHitPanel.add(criticalHitThresholdLabelNum);
		
		JLabel criticalHitRate = new JLabel("暴击率");
		criticalHitPanel.add(criticalHitRate);
		
		JLabel criticalHitRateNum = new JLabel("暴击率（值）");
		criticalHitPanel.add(criticalHitRateNum);
		
		JLabel nextCriticalHitThresholdLabel = new JLabel("下一档阈值");
		criticalHitPanel.add(nextCriticalHitThresholdLabel);
		
		JLabel nextCriticalHitThresholdLabelNum = new JLabel("下一档阈值（值）");
		criticalHitPanel.add(nextCriticalHitThresholdLabelNum);
		
		JLabel nextCriticalHitRate = new JLabel("暴击率");
		criticalHitPanel.add(nextCriticalHitRate);
		
		JLabel nextCriticalHitRateNum = new JLabel("暴击率（值）");
		criticalHitPanel.add(nextCriticalHitRateNum);
	}
}
