package homeFinder;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import homeFinder.Map;
import rangeSlider.View;

public class Main{
	static View roomSlider, priceSlider, aSlider, bSlider;
	static Map map;
	public static void createFrame() {
		roomSlider = new View(10, "Number of rooms");
		priceSlider = new View(1000, "Price");
		aSlider = new View(300, "Distance to A");
		bSlider = new View(300, "Distance to B");
		map = new Map(roomSlider, priceSlider, aSlider, bSlider);
		JFrame frame = new JFrame();
		JPanel east = new JPanel();
		JPanel west = new JPanel();
		JPanel northEast = new JPanel();
		frame.setTitle("Home Finder"); 
		frame.setSize(970, 650); 
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		frame.add(east, BorderLayout.EAST);
		frame.add(west, BorderLayout.WEST);
		east.setLayout(new BorderLayout());
		east.add(northEast, BorderLayout.NORTH);
		northEast.setLayout(new BoxLayout(northEast, BoxLayout.Y_AXIS));
		northEast.add(roomSlider);
		northEast.add(priceSlider);
		northEast.add(aSlider);
		northEast.add(bSlider);
		west.add(map);
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
		aSlider.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                map.repaint();
            }
		});
		bSlider.addMouseListener(new MouseAdapter() {
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
		aSlider.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                map.repaint();
            }
		});
		bSlider.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                map.repaint();
            }
		});
	}
	
	public static void main(String[] args) {
		createFrame();
	}
}
