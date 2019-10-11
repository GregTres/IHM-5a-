package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.geom.Point2D;


import fc.RangeSlider;

public class RangeSliderUI extends JPanel{
	public int xmin;
	public int xmax;
	public int ymin;
	public int ymax;
	
	public Interaction interaction;
	
	public Rectangle rectPrincipal;	//rectangle correspondant au range slider
	public Rectangle rectg;	//rectangle correspondant au curseur gauche
	public Rectangle rectd;	//rectangle correspondant au curseur droite
	public Rectangle rectm;	//rectangle correspondant à la zone entre les deux curseurs
	
	
	RangeSliderUI(){
		xmin = 10;
		xmax = 310;
		ymin = 10;
		ymax = 20;
		
		rectPrincipal = new Rectangle(10,10, 300, 10);
		rectg = new Rectangle(10,10, 10, 10);
		rectd = new Rectangle(300,10, 10, 10);
		rectm = new Rectangle(20,10, 280, 10);
		setPreferredSize(new Dimension(320, 40));
		
		this.addMouseMotionListener(interaction);
	}
	
	public void paint(Graphics g) {
		g.fillRect(rectPrincipal.x, rectPrincipal.y, rectPrincipal.width, rectPrincipal.height);
		
		
		g.setColor(Color.GRAY);
		g.fillRect(rectg.x, rectg.y, rectg.width, rectg.height);
		g.fillRect(rectd.x, rectd.y, rectd.width, rectd.height);
		
		g.setColor(Color.cyan);
		g.fillRect(rectm.x, rectm.y, rectm.width, rectm.height);
		
		
	}
	
	public void translate(Point2D p) {
		
	}
	
	
}
