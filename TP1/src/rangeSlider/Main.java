package rangeSlider;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Main{
	
	public static void createFrame() {
		JFrame frame = new JFrame();
		frame.setTitle("Range Slider"); 
		frame.setLayout(new BorderLayout());
		frame.setSize(320,240); 
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setVisible(true);
		frame.add(new View(20, "test"), BorderLayout.NORTH);
		frame.add(new View(50, "test2"), BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		createFrame();
	}
}
