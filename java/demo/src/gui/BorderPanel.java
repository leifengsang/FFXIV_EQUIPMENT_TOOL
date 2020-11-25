package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BorderPanel extends JPanel {
	
	public BorderPanel(){
		super();
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
