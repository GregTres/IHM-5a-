package markingMenu;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {
	public static void createFrame() {
		JFrame frame = new JFrame();
		frame.setTitle("Marking menu"); 
		frame.setLayout(new BorderLayout());
		frame.setSize(300, 300); 
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		createFrame();
	}
}
