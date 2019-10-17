package homeFinder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import rangeSlider.View;

public class Map extends JPanel{

	private final static int SIZEX = 650;
	private final static int SIZEY = 650;
	private final static int N = 10;
	
	List<Home> listHome = new ArrayList<Home>();
	View roomSlider, priceSlider, aSlider, bSlider;
	int aX = -10, aY = -10, bX = -10, bY = -10, clickX, clickY;
	Point a=new Point(-10, -10), b=new Point(-10, -10);
	public Map(View roomSlider, View priceSlider, View aSlider, View bSlider) {
		this.roomSlider = roomSlider;
		this.priceSlider = priceSlider;
		this.aSlider = aSlider;
		this.bSlider = bSlider;
		for (int i=0 ; i<N ; i++) {
			listHome.add(new Home(new Point((int) (Math.random()*(SIZEX-15)),(int) (Math.random()*(SIZEY-40))), (int) (Math.random()*(10)), (int) (Math.random()*(100))));
		}
		setPreferredSize(new Dimension(SIZEX-15, SIZEY-40));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickX = e.getX();
				clickY = e.getY();
				addPoint(clickX, clickY);
			}
		});
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(0, 0, 640, 610, 10, 10);
		g.setColor(Color.BLACK);
		for(int i = 0 ; i<N ; i++) {
			Home home = this.listHome.get(i);
			if(home.numberOfRoom>=this.roomSlider.model.firstValue && home.numberOfRoom<=this.roomSlider.model.secondValue && home.price>=this.priceSlider.model.firstValue && home.price<=this.priceSlider.model.secondValue && (a.x < 0 || a.x >= 0 && distance(a, home.position) > this.aSlider.model.firstValue && distance(a, home.position) < this.aSlider.model.secondValue) && (b.x < 0 || b.x >= 0 && distance(b, home.position) > this.bSlider.model.firstValue && distance(b, home.position) < this.bSlider.model.secondValue))
				g.fillOval(home.position.x, home.position.y, 10, 10);
		}
		g.setColor(Color.blue);
		g.drawString("A", aX-5, aY-15);
		g.fillRect(aX-5, aY-5, 10, 10);
		g.drawString("B", bX-5, bY-15);
		g.fillRect(bX-5, bY-5, 10, 10);
		if(a.x >= 0) {
			g.drawOval(aX-aSlider.model.secondValue, aY-aSlider.model.secondValue, aSlider.model.secondValue*2, aSlider.model.secondValue*2);
			g.drawOval(aX-aSlider.model.firstValue, aY-aSlider.model.firstValue, aSlider.model.firstValue*2, aSlider.model.firstValue*2);
		}
		if(b.x >= 0) {
			g.drawOval(bX-bSlider.model.secondValue, bY-bSlider.model.secondValue, bSlider.model.secondValue*2, bSlider.model.secondValue*2);
			g.drawOval(bX-bSlider.model.firstValue, bY-bSlider.model.firstValue, bSlider.model.firstValue*2, bSlider.model.firstValue*2);
		}
			
	}
	
	public void addPoint(int x, int y) {
		if (aX == -10) {
			aX = x;
			aY = y;
			a.x = aX;
			a.y = aY;
			this.repaint();
		}
		else if (aX != -10 && bX == -10) {
			bX = x;
			bY = y;
			b.x = bX;
			b.y = bY;
			this.repaint();
		}
	}
	
	public double distance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p2.getY() - p1.getY(), 2) + Math.pow(p2.getX() - p1.getX(), 2));
	}
}
