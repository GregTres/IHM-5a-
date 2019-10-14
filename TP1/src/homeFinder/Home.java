package homeFinder;

import java.awt.Point;

public class Home {
	
	public int numberOfRoom, price;
	public Point position;
	public Home(Point position, int numberOfRoom, int price) {
		this.position = position;
		this.numberOfRoom = numberOfRoom;
		this.price = price;
	}
}
