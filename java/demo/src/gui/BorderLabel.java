package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class BorderLabel extends JLabel {
	
	public BorderLabel(String text){
		super(text);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	public BorderLabel(){
		this("");
	}
}
