package markingMenu;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Main {
	public static void createFrame() {
		JFrame frame = new JFrame();
		frame.setTitle("Marking menu");
		frame.setSize(1000, 1000); 
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		List<String> listMarking = new ArrayList<String>();
		listMarking.add("Outils");
		listMarking.add("Couleur");
		listMarking.add("Annuler");
		frame.add(new View(listMarking));
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		createFrame();
	}
}
