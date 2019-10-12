package rangeSlider;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class View extends JFrame{

	View(){
		/*JPanel root = new JPanel(new BorderLayout());
		RangeSliderUI slider = new RangeSliderUI(10, 310, 10, 20);
		root.add(slider);
		add(root);*/
		add(new RangeSliderUI(20));
	}
}
