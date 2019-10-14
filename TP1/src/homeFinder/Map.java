package homeFinder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import rangeSlider.RangeSliderUI;

public class Map extends JPanel{

	Home first, second, third;
	RangeSliderUI roomSlider, priceSlider;
	public Map(RangeSliderUI roomSlider, RangeSliderUI priceSlider) {
		this.roomSlider = roomSlider;
		this.priceSlider = priceSlider;
		this.first = new Home(new Point(100,400), 4, 800);
		this.second = new Home(new Point(100,100), 2, 300);
		this.third = new Home(new Point(500,200), 3, 450);
		setPreferredSize(new Dimension(800, 800));
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 10, 10);
		g.setColor(Color.BLACK);
		if(this.first.numberOfRoom>=this.roomSlider.firstValue && this.first.numberOfRoom<=this.roomSlider.secondValue && this.first.price>=this.priceSlider.firstValue && this.first.price<=this.priceSlider.secondValue)
			g.fillOval(this.first.position.x, this.first.position.y, 15, 15);
		if(this.second.numberOfRoom>=this.roomSlider.firstValue && this.second.numberOfRoom<=this.roomSlider.secondValue && this.second.price>=this.priceSlider.firstValue && this.second.price<=this.priceSlider.secondValue)
			g.fillOval(this.second.position.x, this.second.position.y, 15, 15);
		if(this.third.numberOfRoom>=this.roomSlider.firstValue && this.third.numberOfRoom<=this.roomSlider.secondValue && this.third.price>=this.priceSlider.firstValue && this.third.price<=this.priceSlider.secondValue)
			g.fillOval(this.third.position.x, this.third.position.y, 15, 15);
	}
}
