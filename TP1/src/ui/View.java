package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class View extends JFrame{

	View(){
		JPanel root = new JPanel(new BorderLayout());
		RangeSliderUI slider = new RangeSliderUI();
		
		root.add(slider);
		add(root);
		setPreferredSize(new Dimension(350, 70));
	}
}
