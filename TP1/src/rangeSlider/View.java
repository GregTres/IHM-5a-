package rangeSlider;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class View extends JFrame{

	public View(){
		/*JPanel root = new JPanel(new BorderLayout());
		RangeSliderUI slider = new RangeSliderUI(10, 310, 10, 20);
		root.add(slider);
		add(root);*/
		build();
		add(new RangeSliderUI(20), BorderLayout.NORTH);
		add(new RangeSliderUI(50), BorderLayout.SOUTH);
	}
	
	private void build(){
		setTitle("Range Slider"); 
		setLayout(new BorderLayout());
		setSize(320,240); 
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setVisible(true);
	}
}
