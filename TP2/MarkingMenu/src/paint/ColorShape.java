package paint;

import java.awt.Color;
import java.awt.Shape;


public class ColorShape {

	public Color color;
	public Shape shape;
	public Boolean fill;
	
	public ColorShape(Color c, Shape s, Boolean f){
		color = c;
		shape = s;
		fill = f;
	}
}
