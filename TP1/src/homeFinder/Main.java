package homeFinder;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import homeFinder.Map;
import rangeSlider.RangeSliderUI;

public class Main{
	static RangeSliderUI roomSlider, priceSlider;
	static Map map;
	public static void createFrame() {
		roomSlider = new RangeSliderUI(10);
		priceSlider = new RangeSliderUI(1000);
		map = new Map(roomSlider, priceSlider);
		JFrame frame = new JFrame();
		JPanel north = new JPanel();
		JPanel south = new JPanel();
		frame.setTitle("Home Finder"); 
		frame.setLayout(new BorderLayout());
		frame.setSize(800, 1000); 
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setVisible(true);
		frame.add(north, BorderLayout.NORTH);
		frame.add(south, BorderLayout.SOUTH);
		south.add(roomSlider);
		south.add(priceSlider);
		north.add(map);
		roomSlider.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                map.repaint();
            }
		});
		priceSlider.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                map.repaint();
            }
		});
		roomSlider.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                map.repaint();
            }
		});
		priceSlider.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                map.repaint();
            }
		});
	}
	
	public static void main(String[] args) {
		createFrame();
	}
}
